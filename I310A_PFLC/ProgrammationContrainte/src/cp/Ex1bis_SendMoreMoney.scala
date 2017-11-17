package cp

import JaCoP.scala._

// In this version, we allow the digits to be the same.

object Ex1bis_SendMoreMoney extends jacop {
  def main(args: Array[String]): Unit = {

    val s = IntVar("S", 1, 9)
    val e = IntVar("E", 0, 9)
    val n = IntVar("N", 0, 9)
    val d = IntVar("D", 0, 9)
    val m = IntVar("M", 1, 9)
    val o = IntVar("O", 0, 9)
    val r = IntVar("R", 0, 9)
    val y = IntVar("Y", 0, 9)

    val vars = List(s, e, n, d, m, o, r, y)

    //    alldifferent(vars.toArray)
    //    
    (s * 1000) + (e * 100) + (n * 10) + d +
      (m * 1000) + (o * 100) + (r * 10) + e #=
      (m * 10000) + (o * 1000) + (n * 100) + (e * 10) + y

    def printSol(): Unit = {
      for (v <- vars) print(v.id + " " + v.value + " ")
      println()
    }
    val result = satisfyAll(search(vars, first_fail, indomain_middle), printSol);

  }
}
