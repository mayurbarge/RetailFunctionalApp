package currency

import Money.Amount
import scalaz._
import scalaz.Scalaz._
import scalaz.syntax.MonoidOps

import scala.math.BigDecimal

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

case class Money[C <: Currency](value: Amount)(implicit currency: C) {
  override def toString: String = s"${currency.symbol} $value"
}

object Money {
  type Amount = BigDecimal
  class MoneyMonoid[T<:Currency](implicit currency: T) extends Monoid[Money[T]] {
    override def zero: Money[T] = Money[T](BigDecimal(0))
    override def append(f1: Money[T], f2: => Money[T]): Money[T] = Money[T](f1.value + f2.value)
    def minus(f1: Money[T], f2: => Money[T]): Money[T] = Money[T](f1.value - f2.value)
  }
  implicit def usdMonoid = new MoneyMonoid[USD]()(Currency.usd)
}