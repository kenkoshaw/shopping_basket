object Offers {
  val offers: scala.collection.mutable.Map[String, (String,Int,Double)] = scala.collection.mutable.Map.apply("Apple" -> ("",0,0.1),"Bread" -> ("Soup",2,0.5))

  def addOffer(product:String, coProduct:String, coProdCnt:Int, discount:Double){
    offers(product) = (coProduct,coProdCnt,discount)
    println(offers)
  }

  def removeOffer(product:String){
    offers -= product
    println(offers)
  }
}
