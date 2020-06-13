package com.price.calculator;

import java.util.Optional;

public class BaseUnitPrice {

  private final PriceData data;

  public BaseUnitPrice(final PriceData data) {
    this.data = data;
  }

  Optional<String> get() {
    return Optional.ofNullable(data.getUnitPrice())
        .map(
            price ->
                String.format(
                    "%s %s per %s",
                    price.getNumber().doubleValue(),
                    price.getCurrency().getCurrencyCode(),
                    data.getUnit()));
  }
}
