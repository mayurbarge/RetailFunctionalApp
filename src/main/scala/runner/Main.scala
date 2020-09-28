package runner

import currency.{Money, USD}
import customer.{Customer, Premium, Regular, UnbilledShoppingCart}
import discount.DiscountCalculator
import currency.Money._

object Main extends App {
  val d = DiscountCalculator.calculate(Customer[Regular,USD](UnbilledShoppingCart(Money[USD](BigDecimal(15000)))))
  println(d)
}