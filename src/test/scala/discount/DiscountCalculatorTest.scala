package discount

import currency.{INR, Money}
import customer.{BilledShoppingCart, Cart, UnbilledShoppingCart}
import org.scalatest.{FunSpec, Matchers}

class DiscountCalculatorTest extends FunSpec with Matchers {
  describe("Discount Calculator") {
    import currency.USD
    import customer.{CustomerProfile, Regular}
    val customerProfile = CustomerProfile[Regular, INR](UnbilledShoppingCart[INR](Money[INR](BigDecimal(10000))))
    val result = DiscountCalculator.calculate(customerProfile)

    it("should calculate discounts and return billed shopping cart with same currency as customer profile") {
      val cartAfterDiscount = result.cart
      cartAfterDiscount.isInstanceOf[BilledShoppingCart[INR]] shouldBe(true)
    }

    it("should return billed cart with discount applied") {
      val cartAfterDiscount: BilledShoppingCart[INR] = result.cart.asInstanceOf[BilledShoppingCart[INR]]
      cartAfterDiscount.billAmount shouldBe Money[INR](BigDecimal(9500))
    }

    it("should return the customer profile with same type and currency as the input customer profile") {
      result.isInstanceOf[CustomerProfile[Regular, INR]] shouldBe true
    }
  }

}
