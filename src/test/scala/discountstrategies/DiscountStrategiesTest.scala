package discountstrategies

import customer.{Premium, Regular}
import org.scalatest.{FunSpec, Matchers}

class DiscountStrategiesTest extends FunSpec with Matchers {
  describe("DiscountStrategies - For") {
    describe("Regular Customer Profile") {
      it("should return list of applicable discount strategies") {
        implicitly[DiscountStrategies[Regular]].strategies.map(_.run(BigDecimal(15000))).toList shouldBe(List(500.0, 1000.0))
      }

      it("should return no discount when purchase amount is less than 5000") {
        implicitly[DiscountStrategies[Regular]].strategies.map(_.run(BigDecimal(4000))).toList shouldBe(List(0, 0))
      }
    }

    describe("Premium Customer Profile") {
      it("should return list of applicable discount strategies") {
        implicitly[DiscountStrategies[Premium]].strategies.map(_.run(BigDecimal(15000))).toList shouldBe(List(400.0, 600.0, 800.0, 900.0))
      }

      it("should return no discount when purchase amount is less than 3000") {
        implicitly[DiscountStrategies[Premium]].strategies.map(_.run(BigDecimal(2000))).toList shouldBe(List(0, 0,0, 0))
      }
    }
  }

}
