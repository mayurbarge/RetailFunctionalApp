package discountstrategies

import currency.Money.Amount
import customer.{Customer, CustomerType, Premium, Regular}
import discount.{AmountRange, PercentageDiscount}

trait DiscountStrategy {
  def run[A](amount:Amount): Amount
}

trait DiscountStrategies[T <: CustomerType] {
  def strategies: List[DiscountStrategy]
}
case class DefaultDiscountStrategy(amountRange: AmountRange, discount: PercentageDiscount) extends DiscountStrategy {
  override def run[A](amount: Amount) = amountRange match {
    case AmountRange(start, None) if start < amount => (amount-start)*discount.rate
    case AmountRange(start, Some(end)) if start < amount && end <= amount => {
      println("########")
      println(amountRange + " " + discount + " " + amount)
      (end-start)*discount.rate
    }
    case _=> 0
  }
}

object DiscountStrategies {
  implicit val regularDiscountStrategies = new DiscountStrategies[Regular] {
    val discountRates = Map(AmountRange(BigDecimal(5000), Some(BigDecimal(10000))) -> PercentageDiscount(0.1),
      AmountRange(BigDecimal(10000), None) -> PercentageDiscount(0.2)).toList

    override def strategies: List[DiscountStrategy] = discountRates.map(e => {
      val (amountRange, discount) = e
      DefaultDiscountStrategy(amountRange, discount)
    })
  }

  implicit val premiumDiscountStrategies = new DiscountStrategies[Premium] {
    val discountRates = Map(AmountRange(BigDecimal(0), Some(BigDecimal(4000))) -> PercentageDiscount(0.1),
      AmountRange(BigDecimal(4000), Some(BigDecimal(8000))) -> PercentageDiscount(0.15),
      AmountRange(BigDecimal(8000), Some(BigDecimal(12000))) -> PercentageDiscount(0.2),
      AmountRange(BigDecimal(12000), None) -> PercentageDiscount(0.3)).toList

    override def strategies: List[DiscountStrategy] = discountRates.map(e => {
      val (amountRange, discount) = e
      DefaultDiscountStrategy(amountRange, discount)
    })
  }

  trait CustomerDiscounts[T <: CustomerType] {
    val discountStrategies: DiscountStrategies[T]
  }
  object CustomerDiscounts {
    implicit class PremiumDiscounts(implicit customer: Customer[Premium]) extends CustomerDiscounts[Premium] {
      override val discountStrategies: DiscountStrategies[Premium] = premiumDiscountStrategies
    }
    implicit class RegularDiscounts(customer: Customer[Regular]) extends CustomerDiscounts[Regular] {
      override val discountStrategies: DiscountStrategies[Regular] = regularDiscountStrategies
    }
  }

}