import scala.collection.mutable.Map

object Products {
  val products: Map[String, Int] = Map.apply("Soup" -> 65, "Bread" -> 80, "Milk" -> 130, "Apples" -> 100)

  def addProduct(product:String, price:Int){
    products(product) = price
    println(products)
  }

  def removeProduct(product:String){
    products -= product
    println(products)
  }
}
