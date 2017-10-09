object Ex4a {
  def main(args: Array[String]): Unit = {

    def isOdd(e: Int): Boolean = e % 2 == 0

    val tab1 = List(4, 3, 5, 2, 1);
    val tab2 = tab1.filter((e) => e % 2 == 0)
    val tab3 = tab1.filter(isOdd)
    val tab4 = tab1 filter isOdd

    for (i <- List.range(0,tab2.length))
      print(tab2(i) + " ");
    println();

    for (i <- List.range(0,tab3.length))
      print(tab3(i) + " ");
    println();

    for (i <- List.range(0,tab4.length))
      print(tab4(i) + " ");

  }
}
