package customer

import currency.{INR, Money, USD}
import org.scalatest.{FunSpec, Matchers}

class CustomerProfileTest extends FunSpec with Matchers {
  describe("Customer") {
    it("should create regular customer profile with type Regular") {
      CustomerProfile[Regular, USD](UnbilledShoppingCart(Money[USD](BigDecimal(100))))
    }

    it("should create premium customer profile with type Regular") {
      CustomerProfile[Premium, INR](BilledShoppingCart(Money[INR](BigDecimal(100)), Money[INR](50)))
    }
  }

}
