package customer

import currency.{Currency, Money}
import currency.Money.Amount
import discount.DiscountCalculator

import scala.math.BigDecimal

trait CustomerType {}
case class Regular() extends CustomerType
case class Premium() extends CustomerType

trait Cart[C <: Currency] {
  def purchaseAmount: Money[C]
}
case class UnbilledShoppingCart[T <: Currency](purchaseAmount: Money[T]) extends Cart[T]
case class BilledShoppingCart[T <: Currency](purchaseAmount: Money[T], billAmount: Money[T]) extends Cart[T]

case class CustomerProfile[T <: CustomerType, C <: Currency](cart:Cart[C])
