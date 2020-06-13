package com.price.calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

import java.util.Optional;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceTest {

  private static final double DISCOUNT_UNIT_PRICE = 6.5d;
  private static final String UNIT_LABEL = "Liter";
  private static final double BASE_PRICE = 5.0d;
  private static final double UNIT_PRICE = 3.5d;
  private static final double DISCOUNT_PRICE = 1.5d;

  @Mock private PriceRepository priceRepository;

  @Test
  public void testBasePriceIsReturned_When_DiscountAndUnitPriceNotPresent() {
    when(priceRepository.fetch()).thenReturn(priceData(BASE_PRICE));

    assertThat(new Price(priceRepository).calculate(), is(equalTo("5.0 USD")));
  }

  private Optional<PriceData> priceData(Double basePrice) {
    PriceData priceData = new PriceData();
    priceData.setBase(price(basePrice));
    return Optional.of(priceData);
  }

  @Test
  public void testUnitPriceIsReturned_When_UnitPriceIsAvailable_And_DiscountIsNotPresent() {
    when(priceRepository.fetch()).thenReturn(priceData(BASE_PRICE, UNIT_PRICE, UNIT_LABEL));

    assertThat(new Price(priceRepository).calculate(), is(equalTo("3.5 USD per Liter")));
  }

  private Optional<PriceData> priceData(
      Double basePrice, final Double unitPrice, final String unit) {
    PriceData priceData = new PriceData();
    priceData.setBase(price(basePrice));
    priceData.setUnitPrice(price(unitPrice));
    priceData.setUnit(unit);
    return Optional.of(priceData);
  }

  @Test
  public void testDiscountIsReturned_When_DiscountIsPresent_And_DiscountUnitPriceIsNotPresent() {
    when(priceRepository.fetch()).thenReturn(priceData(BASE_PRICE, UNIT_PRICE, DISCOUNT_PRICE));

    assertThat(new Price(priceRepository).calculate(), is(equalTo("1.5 USD")));
  }

  private Optional<PriceData> priceData(
      Double basePrice, final Double unitPrice, final Double discountPrice) {
    PriceData priceData = new PriceData();
    priceData.setBase(price(basePrice));
    priceData.setUnitPrice(price(unitPrice));
    priceData.setDiscount(price(discountPrice));
    return Optional.of(priceData);
  }

  @Test
  public void testDiscountUnitPriceIsReturned_When_Present() {
    when(priceRepository.fetch())
        .thenReturn(
            priceData(BASE_PRICE, UNIT_PRICE, DISCOUNT_PRICE, DISCOUNT_UNIT_PRICE, UNIT_LABEL));

    assertThat(new Price(priceRepository).calculate(), is(equalTo("6.5 USD per Liter")));
  }

  private Optional<PriceData> priceData(
      Double basePrice,
      final Double unitPrice,
      final Double discountPrice,
      final Double discountUnitPrice,
      final String unit) {
    PriceData priceData = new PriceData();
    priceData.setBase(price(basePrice));
    priceData.setUnitPrice(price(unitPrice));
    priceData.setDiscount(price(discountPrice));
    priceData.setDiscountUnitPrice(price(discountUnitPrice));
    priceData.setUnit(unit);
    return Optional.of(priceData);
  }

  @Test(expected = PriceException.class)
  public void testPriceExceptionIsThrown_When_PriceNotPresent() {
    when(priceRepository.fetch()).thenReturn(Optional.empty());

    new Price(priceRepository).calculate();
  }

  @Test(expected = PriceException.class)
  public void testPriceExceptionIsThrown_When_PriceValuesNotPresent() {
    when(priceRepository.fetch()).thenReturn(Optional.of(new PriceData()));

    new Price(priceRepository).calculate();
  }

  private MonetaryAmount price(final double price) {
    return Monetary.getDefaultAmountFactory().setCurrency("USD").setNumber(price).create();
  }
}
