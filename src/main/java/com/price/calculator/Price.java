package com.price.calculator;

public class Price {

  private final PriceRepository priceRepository;

  public Price(final PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  public String calculate() {
    return priceRepository
        .fetch()
        .map(this::format)
        .orElseThrow(() -> new PriceException("Price not found"));
  }

  private String format(final PriceData data) {
    return new DiscountUnitPrice(data)
        .get()
        .or(() -> new DiscountPrice(data).get())
        .or(() -> new BaseUnitPrice(data).get())
        .or(() -> new BasePrice(data).get())
        .orElseThrow(() -> new PriceException("Price Values missing"));
  }
}
