import scala.collection.mutable.Map

object Offers {
  val offers: Map[String, (String,Int,Double)] = Map.apply("Apples" -> ("Apples",0,0.1),"Bread" -> ("Soup",2,0.5))

  def addOffer(product:String, coProduct:String, coProdCnt:Int, discount:Double){
    offers(product) = (coProduct,coProdCnt,discount)
    println(offers)
  }

  def removeOffer(product:String){
    offers -= product
    println(offers)
  }
}
