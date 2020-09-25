package runner

import discount.rules.{Money, USD}
import scalaz.Monoid
import scalaz._, scalaz.Scalaz._
import Money._
import discount.rules.Currency._

object Main extends App {

  //val x: Monoid[Money[USD]] = Money[USD](100)

  //val r = Money[USD](100).plus Money[USD](200)
    //Money[USD](100) |+| Money[USD](100)

  import scalaz.Functor._
  import Scalaz.optionInstance._

  val r = Money[USD](66) |+| Money[USD](4)
  println(r)

}