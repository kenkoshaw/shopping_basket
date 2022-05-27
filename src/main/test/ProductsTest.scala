import org.scalatest.funsuite.AnyFunSuite

class ProductsTest extends AnyFunSuite {
  test("Products.addProduct") {
    Products.addProduct("Pear", 30)
    assert(Products.products.contains("Pear") && Products.products("Pear") == 30)
  }

  test("Products.removeProduct") {
    Products.removeProduct("Apples")
    assert(!Products.products.contains("Apples"))
  }
}
