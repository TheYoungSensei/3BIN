package cp.examples

import JaCoP.scala._

/**
 *
 * It models Travelling Salesman Problem (TSP).
 * http://en.wikipedia.org/wiki/Travelling_salesman_problem
 *
 * @author Krzysztof Kuchcinski
 *
 */

object TSP extends jacop {
  def main(args: Array[String]) {
    val noCities = 10

    // Specifies distance between any two cities
    // 1000 - large value to remove possibility of self loop
    val distance = List(List(1000, 85, 110, 94, 71, 76, 25, 56, 94, 67),
      List(85, 1000, 26, 70, 62, 60, 63, 62, 70, 49),
      List(110, 26, 1000, 71, 87, 89, 88, 87, 93, 73),
      List(94, 70, 71, 1000, 121, 19, 82, 106, 124, 105),
      List(71, 62, 87, 121, 1000, 104, 53, 24, 8, 13),
      List(76, 60, 89, 19, 104, 1000, 65, 89, 108, 93),
      List(25, 63, 88, 82, 53, 65, 1000, 30, 57, 46),
      List(56, 62, 87, 106, 24, 89, 30, 1000, 23, 20),
      List(94, 70, 93, 124, 8, 108, 57, 23, 1000, 20),
      List(67, 49, 73, 105, 13, 93, 46, 20, 20, 1000))

    // Denotes a city to go to from index city
    val cities = for (i <- List.range(0, noCities)) yield IntVar("cities[" + (i + 1) + "]", 1, noCities)

    // Denotes a cost of traveling between index city and next city
    val costs = for (i <- List.range(0, noCities)) yield IntVar("costs[" + (i + 1) + "]", 0, 1000);

    // Impose cuircuit constraint which makes sure
    // that List cities is a hamiltonian circuit
    circuit(cities)

    // Computes a cost of traveling between ith city
    // and city[i]-th city
    for (i <- List.range(0, noCities))
      element(cities(i), distance(i), costs(i))

    // Computes overall cost of traveling
    // simply sum of all costs
    val cost = sum(costs)

    val result = minimize(search(cities, first_fail, indomain_min), cost)
    println("cost=" + cost.value)
    for (v <- cities) println(v.id + " " + v.value)

  }
}

