package runner

import customer.{Customer, Premium, ShoppingCart}
import discount.DiscountCalculator

object Main extends App {

  val d = DiscountCalculator.calculate(Customer[Premium](ShoppingCart(BigDecimal(4000))))
 // val e = DiscountCalculator.calculate(Customer[Premium.type](ShoppingCart(BigDecimal(20000))))
  println(d)
 // println(e)

}