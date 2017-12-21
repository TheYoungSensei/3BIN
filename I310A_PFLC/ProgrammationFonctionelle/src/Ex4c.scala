object Ex4c {
  def main(args: Array[String]): Unit = {

    def isMult(m: Int)(e: Int): Boolean = e % m == 0

    val tab1 = List(4, 3, 5, 2, 1);
    val tab2 = tab1 filter isMult(2)

    for (i <- List.range(0,tab2.length))
      print(tab2(i) + " ");
  }
}
