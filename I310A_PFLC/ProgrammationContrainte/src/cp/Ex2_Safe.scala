package cp

import JaCoP.scala._

/*
 * The code of my safe is composed of 9 different numbers. None of these numbers is 0. 
 * Also the numbers solve these equations:
 * 
 *   c(4) - c(6) == c(7)
 *   c(1) * c(2) * c(3) == c(8) + c(9)
 *   c(2) + c(3) + c(6) < c(8)
 *   c(9) < c(8)
 *   c(1) != 1, ... , c(9) != 9
 * 
 */

object Ex2_Safe extends jacop {
  def main(args : Array[String]) : Unit = {
    
    val d = for(i <- List.range(1,10)) yield IntVar("c"+(i),1,9)
    def c(i:Int) = d(i) // so that my first number is 1 instead of 0
    
    alldifferent(d.toArray)
    
    c(4) - c(6) #= c(7)
    c(1) * c(2) * c(3) #= c(8) + c(9)
    ((c(2) + c(3)) + c(6)) #< c(8)
    c(9) #< c(8)
    
    for(i <- List.range(1,9)) c(i) #\= i
    
    val result = satisfy(search(d, first_fail, indomain_middle))

    if (result) {
      for(v <- d) println(v.toString)
    } else {
      println("no solution")
    }
  }
}
