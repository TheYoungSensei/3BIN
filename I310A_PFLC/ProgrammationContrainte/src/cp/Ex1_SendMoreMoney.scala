package cp

import JaCoP.scala._

// The Send More Money Problem consists in finding distinct digits for the letters
// D, E, M, N, O, R, S, Y such that S and M are different from zero (no leading zeros) 
// and the equation SEND + MORE = MONEY is satisfied.

object Ex1_SendMoreMoney extends jacop {
  def main(args: Array[String]): Unit = {

    // Constraint variables 
    val s = IntVar("S", 1, 9)
    val e = IntVar("E", 0, 9)
    val n = IntVar("N", 0, 9)
    val d = IntVar("D", 0, 9)
    val m = IntVar("M", 1, 9)
    val o = IntVar("O", 0, 9)
    val r = IntVar("R", 0, 9)
    val y = IntVar("Y", 0, 9)

    val vars = List(s, e, n, d, m, o, r, y)

    // Constraints
    alldifferent(vars.toArray)

    (s * 1000) + (e * 100) + (n * 10) + d +
      (m * 1000) + (o * 100) + (r * 10) + e #=
      (m * 10000) + (o * 1000) + (n * 100) + (e * 10) + y

    // Find a solution
    satisfy(search(vars, first_fail, indomain_middle))

    // Print it
    for (v <- vars) println(v.id + " " + v.value)
  }
}
