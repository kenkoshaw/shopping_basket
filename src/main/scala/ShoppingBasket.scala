import Offers.offers
import Products.products
import scala.collection.mutable.Map
import scala.io.StdIn.readLine

object ShoppingBasket {
  def main(args: Array[String]): Unit = {
    var input = ""

    while (true) {
        print("Please place your shopping basket: ")
        input = readLine()
        var words = input.split(' ')
        val cmd = words(0)
        words = words.drop(1)

        cmd.toLowerCase match {
          case "pricebasket" => priceBasket(words)
          case "addproduct" => Products.addProduct(words(0), words(1).toInt)
          case "removeproduct" => Products.removeProduct(words.head)
          case "addoffer" => Offers.addOffer(words(0), words(1), words(2).toInt, words(3).toDouble)
          case "removeoffer" => Offers.removeOffer(words.head)
          case "exit" => return
          case badInput => println("not a valid command: " + badInput)
        }
      }
  }

  def priceBasket(basket:Array[String]) {
    val countBasket: Map[String, Int] = Map.apply()
    for (item <- basket) {
      if (!products.contains(item)) {
        println("sorry we don't have '" + item + "' in stock...")
        return
      }
      if (countBasket.contains(item)) {countBasket(item) += 1} else {countBasket(item) = 1}
    }

    val subTotalAmt = calculateSubTotal(countBasket)
    println("Subtotal: " + intToGBP(subTotalAmt))
    val totalDiscount = calculateOffers(countBasket)
    val totalAmt = subTotalAmt - totalDiscount
    println("Total price: " + intToGBP(totalAmt))
  }

  def calculateSubTotal(basket: Map[String, Int]): Int ={
    var subTotalAmt = 0
    for (item <- basket) {
      val product = item._1
      val prodCnt = item._2
      val prodPrice = products(product)
      val accProdPrice = prodPrice * prodCnt
      subTotalAmt += accProdPrice
    }
    subTotalAmt
  }

  def calculateOffers(basket: Map[String, Int]): Int ={
    var totalDiscount = 0
    var noOffers = true
    for (item <- basket) {
      val product = item._1
      val prodCnt = item._2

      if (offers.contains(product)){
        val offer = offers(product)
        val coProduct = offer._1
        val coProdCnt = offer._2

        val coProdInBasketCnt =  if (basket.contains(coProduct)) basket(coProduct) else 0
        val maxDiscItemCnt = if (coProdCnt != 0) coProdInBasketCnt/coProdCnt else prodCnt

        val discItemCnt = prodCnt.min(maxDiscItemCnt)
        val discountedAmt = discItemCnt * products(product) * offer._3
        totalDiscount += discountedAmt.toInt

        if (discountedAmt.toInt != 0){
          noOffers = false
          val percent = (offer._3 * 100).toInt
          if (coProdCnt != 0) {
            println("Buy " + coProdCnt + " " + coProduct + " get " + percent + "% off 1 " + product + ": "  + intToGBP(discountedAmt.toInt))
          }
          else {
            println(product + " " + percent + "% off: " + intToGBP(discountedAmt.toInt))
          }
        }
        if (noOffers) {
          println("(No offers available)")
        }
      }
    }
    totalDiscount
  }

  def intToGBP(amt:Int): String ={
    if (amt >= 100){
      "$" + amt.toString.dropRight(2) + "." + amt.toString.takeRight(2)   //using '$' instead of '£' as '£' doesn't show up correctly in terminal
    } else {
      amt + "p"
    }
  }
}