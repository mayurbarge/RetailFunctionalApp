package discount.rules

import discount.rules.Money.Amount
import scalaz.Scalaz._
import scalaz._

trait Type {}
case object Regular extends Type
case object Premium extends Type

case class ShoppingCart(cartTotal: BigDecimal)

case class Customer[T <: Type](cart:ShoppingCart) {
  def getCartTotal = cart.cartTotal
}

case class PercentageDiscount(discount: Double)

trait Currency {
  def symbol: String
}
case class USD() extends Currency {
  def symbol: String = "$"
}
case class INR() extends Currency {
  def symbol: String = "INR"
}
object Currency {
  implicit val usd = USD()
  implicit val inr = INR()
}

case class Money[C <: Currency](amount: Amount)(implicit currency: C) {
  override def toString: String = s"${currency.symbol} $amount"
}

object Money {
  type Amount = BigDecimal

 /* implicit object AmountMonoid extends Monoid[Amount] {
    override def zero: Amount = BigDecimal(0)
    override def append(first: Amount, second: => Amount): Amount = first + second
  }

  implicit object AmountOrdering extends Order[Amount] {
    override def order(first: Amount, second: Amount): Ordering = first ?|? second
  }*/

  trait MoneyOps[F] {
    val F: Money[_] = Money[USD](BigDecimal(0))
    def plus(first: F, second: F): F
  }

  implicit object USDOps extends MoneyOps[Money[USD]] {
    override val F: Money[USD] = Money[USD](BigDecimal(0))
    implicit override def plus(first: Money[USD], second: Money[USD]): Money[USD] = Money(first.amount + second.amount)
  }
}



case class Rule[T <: Regular.type]() {

}
