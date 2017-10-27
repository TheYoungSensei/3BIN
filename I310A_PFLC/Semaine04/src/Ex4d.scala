object Ex4d {
  def main(args: Array[String]): Unit = {
    def length[A](x: List[A]): Int = {
      x match {
        case Nil => 0
        case y :: ys => 1 + length(ys)
      }
    }

    println(length(List("a", "b")));
    println(length(List(1, 2, 3, 4)));
    println(length(List(List(0), Nil, List(1, 2))));

  }
}
