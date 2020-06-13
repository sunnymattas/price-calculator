package com.price.calculator;

import javax.money.MonetaryAmount;

public class PriceData {
  private MonetaryAmount base;
  private MonetaryAmount discount;
  private MonetaryAmount discountUnitPrice;
  private MonetaryAmount unitPrice;
  private String unit;

  public MonetaryAmount getBase() {
    return base;
  }

  public void setBase(final MonetaryAmount base) {
    this.base = base;
  }

  public MonetaryAmount getDiscount() {
    return discount;
  }

  public void setDiscount(final MonetaryAmount discount) {
    this.discount = discount;
  }

  public MonetaryAmount getDiscountUnitPrice() {
    return discountUnitPrice;
  }

  public void setDiscountUnitPrice(final MonetaryAmount discountUnitPrice) {
    this.discountUnitPrice = discountUnitPrice;
  }

  public MonetaryAmount getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(final MonetaryAmount unitPrice) {
    this.unitPrice = unitPrice;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(final String unit) {
    this.unit = unit;
  }
}
