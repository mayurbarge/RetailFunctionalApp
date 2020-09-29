package discount

import customer.{Premium, Regular}
import discountstrategies.DiscountStrategies
import org.scalatest.{FunSpec, Matchers}

class CustomerDiscountsTest extends FunSpec with Matchers {
  import CustomerDiscounts._
    describe("CustomerDiscounts") {
    it("should return regular customer discount strategies") {
      implicitly[CustomerDiscounts[Regular]].discountStrategies.isInstanceOf[DiscountStrategies[Regular]] shouldBe(true)
    }

    it("should return premium customer discount strategies") {
      implicitly[CustomerDiscounts[Premium]].discountStrategies.isInstanceOf[DiscountStrategies[Premium]] shouldBe(true)
    }
  }

}
