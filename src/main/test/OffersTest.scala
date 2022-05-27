import org.scalatest.funsuite.AnyFunSuite

class OffersTest extends AnyFunSuite {
  test("Offers.addOffer") {
    Offers.addOffer("Pear", "Apples", 2, 0.5)
    assert(Offers.offers.contains("Pear") && Offers.offers("Pear") == ("Apples",2,0.5))
  }

  test("Offers.removeOffer") {
    Offers.removeOffer("Apples")
    assert(!Offers.offers.contains("Apples"))
  }
}
