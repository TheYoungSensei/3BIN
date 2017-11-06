package cp

import JaCoP.core.Switches
import JaCoP.scala._
/* A kid goes into a grocery store and buys three items. The cashier charges $5.49, 
 * the kid pays and is about to leave when the cashier calls the kid back, and says 
 * ``Hold on, I multiplied the three items instead of adding them; I'll try again; 
 * Hah, with adding them the price still comes to $5.49''. What were the prices of the 
 * three items?
 */
object Ex3_Grocery extends jacop {

  def main(args: Array[String]): Unit = {
    val a = IntVar("a", 0, 549)
    val b = IntVar("b", 0, 549)
    val c = IntVar("c", 0, 549)

    a + b + c #= 549
    a * b * c #= 5490000

    a #< b
    b #< c

    def printSol(): Unit = {
      println("------")
      println(a)
      println(b)
      println(c)
    }

    val result = satisfyAll(search(List(a, b, c), first_fail, indomain_middle), printSol)
  }
}
