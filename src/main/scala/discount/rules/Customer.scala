package discount.rules

import discount.rules.Money.Amount
import scalaz._
import scalaz.Scalaz._
import scalaz.syntax.MonoidOps

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

object Money extends App {
  type Amount = BigDecimal

  class MoneyMonoid[T<:Currency](implicit currency: T) extends Monoid[Money[T]] {
    override def zero = Money[T](BigDecimal(0))
    override def append(f1: Money[T], f2: => Money[T]): Money[T] = Money[T](f1.amount + f2.amount)
  }
  import Currency._
  implicit def usdMonoid = new MoneyMonoid[USD]()(Currency.usd)

  /*class monoid[C:Money]()(implicit currency: C) {
        type T = C
        val monoid = Monoid.instance((a:Money[T], b: Money[T]) => Money[C](BigDecimal(10)), Money[C](BigDecimal(0))
    )
  }*/

  /*implicit class MoneyOps[C:Money](implicit currency:C) extends Monoid[Money[C]] {
    type T = C
    override def zero: Money[C] = Money[T](BigDecimal(0))
    override def append(f1: Money[C], f2: => Money[C]): Money[C] = Money[T](f1.amount + f2.amount)
  }

  implicit def toMonoidOp[A: Money](a: A)(implicit currency:A) = new MoneyOps[A]*/
  import Currency._
}



