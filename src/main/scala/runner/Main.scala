package runner

import currency.{Money, USD}
import customer.{CustomerProfile, Premium, Regular, UnbilledShoppingCart}
import discount.DiscountCalculator
import currency.Money._
import scalaz.Semigroup
import scalaz.syntax.ToSemigroupOps

object Main extends App {
  val d = DiscountCalculator.calculate(CustomerProfile[Regular,USD](UnbilledShoppingCart(Money[USD](BigDecimal(15000)))))
  println(d)
}