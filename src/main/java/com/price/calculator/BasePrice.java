package com.price.calculator;

import java.util.Optional;

public class BasePrice {

  private final PriceData data;

  public BasePrice(final PriceData data) {
    this.data = data;
  }

  Optional<String> get() {
    return Optional.ofNullable(data.getBase())
        .map(
            price ->
                String.format(
                    "%s %s",
                    data.getBase().getNumber().doubleValue(),
                    data.getBase().getCurrency().getCurrencyCode()));
  }
}
