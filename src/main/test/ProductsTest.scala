import org.scalatest.funsuite.AnyFunSuite

class ProductsTest extends AnyFunSuite {

  test("Products.removeProduct") {
    Products.removeProduct("Apples")
    assert(!Products.products.contains("Apples"))
  }

  test("Products.addProduct") {
    Products.addProduct("Apples", 100)
    assert(Products.products.contains("Apples") && Products.products("Apples") == 100)
  }
}
