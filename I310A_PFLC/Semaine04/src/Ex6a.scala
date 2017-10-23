object Ex6a {
  val colors = Map("red" -> 0xFF0000,
    "turquoise" -> 0x00FFFF,
    "black" -> 0x000000,
    "orange" -> 0xFF8040,
    "brown" -> 0x804000)
  def main(args: Array[String]) {
    val c = List("red", "orange", "blue")
    for (name <- colors) println(
      if (c.contains(name._1))
        name + " has code: " + name._2
      else
        name + " is absent")
  }
}
