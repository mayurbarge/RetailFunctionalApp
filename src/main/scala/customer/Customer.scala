package customer

import scala.math.BigDecimal

trait CustomerType {}
case class Regular() extends CustomerType
case class Premium() extends CustomerType

case class ShoppingCart(cartTotal: BigDecimal)

case class Customer[T <: CustomerType](cart:ShoppingCart)
