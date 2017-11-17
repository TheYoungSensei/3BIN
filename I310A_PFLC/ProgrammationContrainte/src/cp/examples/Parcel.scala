package cp.examples

import scala.collection.mutable.ListBuffer
import JaCoP.scala._

/**
 * It solves a simple parcel shipment problem.
 *
 * @author Radoslaw Szymanek
 *
 */
object Parcel extends jacop {
  def main(args: Array[String]) {
    val noCities = 10;

    // Specifies distance between any two cities
    // 1000 - large value to remove possibility of self loop
    val distance: List[List[Int]] = List(List(1000, 85, 110, 94, 71, 76, 25, 56, 94, 67),
      List(85, 1000, 26, 70, 62, 60, 63, 62, 70, 49),
      List(110, 26, 1000, 71, 87, 89, 88, 87, 93, 73),
      List(94, 70, 71, 1000, 121, 19, 82, 106, 124, 105),
      List(71, 62, 87, 121, 1000, 104, 53, 24, 8, 13),
      List(76, 60, 89, 19, 104, 1000, 65, 89, 108, 93),
      List(25, 63, 88, 82, 53, 65, 1000, 30, 57, 46),
      List(56, 62, 87, 106, 24, 89, 30, 1000, 23, 20),
      List(94, 70, 93, 124, 8, 108, 57, 23, 1000, 20),
      List(67, 49, 73, 105, 13, 93, 46, 20, 20, 1000));

    val maxLoad = 5;
    val minLoad = -6;
    val load_parcels = List(0, 1, 5, -6, 4, 3, -5, 2, 1, -3);

    // Denotes a city to go to from index city
    val cities = for (i <- List.range(0, noCities)) yield IntVar("cities[" + i + "]", 1, noCities)
    // Denotes a cost of traveling between index city and next city
    val costs = for (i <- List.range(0, noCities)) yield IntVar("costs[" + i + "]", 0, 1000)
    // Denotes load of person at each city
    val loads = for (i <- List.range(0, noCities)) yield IntVar("nextLoad[" + i + "]", minLoad, maxLoad)

    // Impose cuircuit constraint which makes sure
    // that List cities is a hamiltonian circuit
    circuit(cities)

    // We start our journey at first city
    var startTown = cities(0)

    // We have to check all steps of the trip to make
    // sure we satisfy load constraints.
    for (i <- List.range(0, cities.length)) {
      // Variable nextTown denotes city which is visited in next move
      val nextTown = IntVar("nextTown[" + i + "]", 1, cities.length);
      // This constraint defines nextTown value
      element(startTown, cities, nextTown)
      // This constraint defines change in the load
      // i denotes here i-th city on the road
      element(startTown, load_parcels, loads(i))
      // This constraint computes cost.
      // i denotes here the number of the city person travels from.
      element(cities(i), distance(i), costs(i))

      // person has moved to the next town, so there is a new
      // startTown
      startTown = nextTown;
    }

    // Constraints below make sure that at no city the load
    // constraint is violated. Load is always between [0..15].
    for (i <- List.range(0, cities.length)) {
      val tripLoads = for (j <- List.range(0, i)) yield loads(j)
      val partialLoad = IntVar("partialLoad[0-" + i + "]", 0, 15);
      partialLoad #= sum(tripLoads)
    }

    // Computes the travel cost.
    val cost = sum(costs)

    def printSol(): Unit = {
      println("\nSolution with cost: " + cost.value + "\n=======================")
      println(cities.toList)
    }

    val result = minimize_seq(List(search(costs, max_regret, indomain_min),
      search(cities.toList, input_order, indomain_min)), cost, printSol)

    statistics

  }

}
