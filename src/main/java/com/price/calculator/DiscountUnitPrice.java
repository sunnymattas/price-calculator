package com.price.calculator;

import java.util.Optional;

public class DiscountUnitPrice {

  private final PriceData data;

  public DiscountUnitPrice(final PriceData data) {
    this.data = data;
  }

  Optional<String> get() {
    return Optional.ofNullable(data.getDiscountUnitPrice())
        .map(
            price ->
                String.format(
                    "%s %s per %s",
                    price.getNumber().doubleValue(),
                    price.getCurrency().getCurrencyCode(),
                    data.getUnit()));
  }
}
