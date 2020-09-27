package discount

import customer.{Customer, CustomerType, Premium}

object DiscountCalculator {
  def calculate[T <: CustomerType](customer: Customer[T]) = {
    import discountstrategies.DiscountStrategies._
    import discountstrategies.DiscountStrategies.CustomerDiscounts._

    /*customerDiscounts(customer).strategies.map(_.run(customer.cart.cartTotal)).foldLeft(BigDecimal(0))((acc, e) => {
    //  println(e)
      acc + e
    })
  }*/
    CustomerDiscounts[Customer[Premium]].

}
