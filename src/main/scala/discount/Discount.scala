package discount

import currency.Money.Amount

trait Discount
case class PercentageDiscount(rate: Double) extends Discount
case class AmountRange(start: Amount, end: Option[Amount])

//{//  def discountedAmount(purchaseAmount: Amount): Amount = ???}//case class TotalDiscount(discounts: List[Discount]) extends Discount
