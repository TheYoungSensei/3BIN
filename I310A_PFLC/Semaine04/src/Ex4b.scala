object Ex4b {
  def main(args: Array[String]): Unit = {

    def isOdd(e: Int): Boolean = e % 2 == 0

    val tab1 = List(4, 3, 5, 2, 1);

    for (e <- tab1) print(e + " ")
    println

    for (e <- tab1 if isOdd(e)) print(e + " ")
    println

    val tab2 = for (e <- tab1 if isOdd(e)) yield e

    for (e <- tab2) print(e + " ")
    println

  }
}
