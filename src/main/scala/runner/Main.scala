package runner

import discount.rules.{Money, USD}

object Main extends App {

  import Money.USDOps._

  val r = Money[USD](100).plus Money[USD](200)
  println(r)
}