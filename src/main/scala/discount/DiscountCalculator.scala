package discount

import currency.{Currency, Money}
import currency.Money._
import customer.{BilledShoppingCart, CustomerProfile, CustomerType}
import scalaz.Scalaz._
import scalaz.Scalaz._
import scalaz.syntax.MonoidOps

object DiscountCalculator {
  def calculate[T <: CustomerType, C <: Currency](customer: CustomerProfile[T, C])(implicit d: CustomerDiscounts[T], currency: C) = {
    val discount = implicitly[CustomerDiscounts[T]].discountStrategies.strategies.map(_.run(customer.cart.purchaseAmount.value)).foldLeft(BigDecimal(0))((acc, e) => {
      acc + e
    })
    customer.copy(BilledShoppingCart(customer.cart.purchaseAmount, Money[C](customer.cart.purchaseAmount.value - discount)))
  }
}
