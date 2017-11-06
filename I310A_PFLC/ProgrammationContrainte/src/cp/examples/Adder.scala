package cp.examples

import JaCoP.scala._

object Adder extends jacop {
  def main(args: Array[String]) {
    val a = BoolVar("a")
    val b = BoolVar("b")
    val c = BoolVar("c")
    val summa = BoolVar("summa")
    val carry = BoolVar("carry")

    // summa part
    summa #= (a xor b xor c)

    // carry part
    carry #= ((c /\ (a xor b)) \/ (a /\ b))

    recordSolutions = true

    println("a | b | c || s | c")
    println("--+---+---++---+--")

    def printTableRow() {
      println(a.value + " | " + b.value + " | " + c.value + " || " + summa.value + " | " + carry.value)
    }

    val result = satisfyAll(search(List(a, b, c, summa, carry), input_order, indomain_min), printTableRow)

  }

}
