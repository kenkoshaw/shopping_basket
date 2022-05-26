object Products {
  val products: scala.collection.mutable.Map[String, Int] = scala.collection.mutable.Map.apply("Soup" -> 65, "Bread" -> 80, "Milk" -> 130, "Apples" -> 100)

  def addProduct(product:String, price:Int){
    products(product) = price
    println(products)
  }

  def removeProduct(product:String){
    products -= product
    println(products)
  }
}
