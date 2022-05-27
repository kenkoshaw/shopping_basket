import org.scalatest.funsuite.AnyFunSuite

class OffersTest extends AnyFunSuite {

  test("Offers.removeOffer") {
    Offers.removeOffer("Apples")
    assert(!Offers.offers.contains("Apples"))
  }

  test("Offers.addOffer") {
    Offers.addOffer("Apples", "Apples", 0, 0.1)
    assert(Offers.offers.contains("Apples") && Offers.offers("Apples") == ("Apples",0,0.1))
  }
}
