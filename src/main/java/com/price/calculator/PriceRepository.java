package com.price.calculator;

import java.util.Optional;

public interface PriceRepository {
  Optional<PriceData> fetch();
}
