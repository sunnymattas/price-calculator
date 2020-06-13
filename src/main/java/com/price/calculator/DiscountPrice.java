package com.price.calculator;

import java.util.Optional;

public class DiscountPrice {
  private final PriceData data;

  public DiscountPrice(final PriceData data) {
    this.data = data;
  }

  Optional<String> get() {
    return Optional.ofNullable(data.getDiscount())
        .map(
            price ->
                String.format(
                    "%s %s",
                    price.getNumber().doubleValue(), price.getCurrency().getCurrencyCode()));
  }
}
