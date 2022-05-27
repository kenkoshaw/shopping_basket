import Offers.offers
import scala.collection.mutable.Map

import scala.io.StdIn.readLine
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    var input = ""

    while (!input.equalsIgnoreCase("exit")) {
        print("Please place your shopping basket: ")
        input = readLine()
        var words = input.split(' ')
        val cmd = words(0)
        words = words.drop(1)

        cmd match {
          case "PriceBasket" => priceBasket(words)
          case "addProduct" => Products.addProduct(words(0), words(1).toInt)
          case "removeProduct" => Products.removeProduct(words.head)
//          case "addOffer" => Offers.addOffer(words(0), words(1).toInt, words(2).toDouble)
          case "removeOffer" => Offers.removeOffer(words.head)
          case badInput => println("not a valid command: " + badInput)
        }
      }
  }

  def priceBasket(basket:Array[String]) {
    val countBasket: Map[String, Int] = Map.apply()
    for (item <- basket) {
      if (countBasket.contains(item)) {countBasket(item) += 1} else {countBasket(item) = 1}
    }

    val subTotals = calculateSubTotal(countBasket)
    val subTotalBasket = subTotals._1
    val subTotalAmt = subTotals._2

    val totalDiscount = calculateOffers(subTotalBasket)
    val totalAmt = subTotalAmt - totalDiscount
    println("Total price: " + intToGBP(totalAmt))
  }

  def calculateSubTotal(basket: Map[String, Int]): (Map[String, (Int,Int)], Int) ={
    val subTotalBasket: Map[String, (Int,Int)] = Map.apply()
    var subTotalAmt = 0
    for (item <- basket) {
      val product = item._1
      val prodCnt = item._2
      val prodPrice = Products.products(product)
      val accProdPrice = prodPrice * prodCnt
      subTotalAmt += accProdPrice
      subTotalBasket(product) = (prodCnt,accProdPrice)
    }
    println("Subtotal: " + intToGBP(subTotalAmt))
    (subTotalBasket,subTotalAmt)
  }

  def calculateOffers(basket: Map[String, (Int,Int)]): Int ={
    var totalDiscount = 0
    for (item <- basket) {
      val product = item._1
      val prodCnt = item._2._1
      val accProdPrice = item._2._2

      if (offers.contains(product)){
        val offer = offers(product)
        val coProduct = offer._1
        val coProdCnt = offer._2
        val percent = (offer._3 * 100).toInt

        if (coProduct.isBlank) {
          val discountedAmt = accProdPrice * offer._3
          totalDiscount += discountedAmt.toInt
          println(product + " " + percent + "% off: " + intToGBP(discountedAmt.toInt))
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