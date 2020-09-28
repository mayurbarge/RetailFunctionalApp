package discount

import currency.Money.Amount
import customer.{CustomerType, Premium, Regular}
import discountstrategies.DiscountStrategies
import discountstrategies.DiscountStrategies.{premiumDiscountStrategies, regularDiscountStrategies}

trait Discount
case class PercentageDiscount(rate: Double) extends Discount
case class AmountRange(start: Amount, end: Option[Amount])

//{//  def discountedAmount(purchaseAmount: Amount): Amount = ???}//case class TotalDiscount(discounts: List[Discount]) extends Discount

trait CustomerDiscounts[T <: CustomerType] {
  val discountStrategies: DiscountStrategies[T]
}

object CustomerDiscounts {
  implicit val premiumDiscounts: CustomerDiscounts[Premium] = new CustomerDiscounts[Premium] {
    override val discountStrategies: DiscountStrategies[Premium] = premiumDiscountStrategies
  }
  implicit val regularDiscounts: CustomerDiscounts[Regular] = new CustomerDiscounts[Regular] {
    override val discountStrategies: DiscountStrategies[Regular] = regularDiscountStrategies
  }
}