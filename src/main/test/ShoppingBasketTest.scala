import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.Map

class ShoppingBasketTest extends AnyFunSuite {
  test("ShoppingBasket.calculateSubTotal") {
    val basket: Map[String, Int] = Map.apply("Soup" -> 2, "Bread" -> 1, "Milk" -> 1, "Apples" -> 1)
    assert(ShoppingBasket.calculateSubTotal(basket) == 440)
  }

  test("ShoppingBasket.calculateOffers") {
    val basket1: Map[String, Int] = Map.apply("Soup" -> 2, "Bread" -> 1, "Milk" -> 1, "Apples" -> 1)
    assert(ShoppingBasket.calculateOffers(basket1) == 50)

    val basket2: Map[String, Int] = Map.apply("Soup" -> 1, "Bread" -> 1, "Milk" -> 1)
    assert(ShoppingBasket.calculateOffers(basket2) == 0)
  }

  test("ShoppingBasket.intToGBP") {
    assert(ShoppingBasket.intToGBP(350).equals("$3.50"))
    assert(ShoppingBasket.intToGBP(55).equals("55p"))
  }
}
