package cp.examples

/**
 *
 * It shows the capabilities and usage of Knapsack constraint.
 *
 * @author Krzysztof Kuchcinski & Radoslaw Szymanek
 * @version 3.0
 *
 * It models and solves a simple knapsack problem. There
 * are two different models. The first one uses quantity
 * from 0 to n, where the second model is allowed to use
 * only binary variables.
 *
 * Each item is specified by its weight and profit. Find
 * what objects should be put in the knapsack to maximize
 * the profit without exceeding the knapsack capacity.
 *
 */

import scala.collection.mutable.ListBuffer
import JaCoP.scala._

object KnapsackExample extends jacop {

  def main(args: Array[String]) {

    var noItems = 3;
    var volume = 9;
    var weights = List(4, 3, 2)
    var profits = List(15, 10, 7)
    var names = List("whisky", "perfumes", "cigarets")

    var maxs = for (i <- List.range(0, noItems)) yield volume / weights(i)

    // I-th variable represents if i-th item is taken
    // Each quantity variable has a domain from 0 to max value
    val quantity = for (i <- List.range(0, noItems)) yield IntVar("Quantity_" + names(i), 0, maxs(i))

    val profit = IntVar("Profit", 0, 1000000);
    val weight = IntVar("Weight", 0, 1000000);

    knapsack(profits, weights, quantity, weight, profit)

    weight #<= volume

    val result = maximize(search(quantity, first_fail, indomain_min), profit)
    for (v <- quantity) println(v.id + " " + v.value)

    statistics
  }

}
