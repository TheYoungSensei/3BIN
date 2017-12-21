object Ex5 {
  def main(args: Array[String]): Unit = {

    type Imaginary = (Double, Double)

    def imPlus(i1: Imaginary, i2: Imaginary): Imaginary = {
      (i1._1 + i2._1, i1._2 + i2._2)
    }

    def imMult(i1: Imaginary, i2: Imaginary): Imaginary = {
      (i1._1 * i2._1 - i1._2 * i2._2, i1._1 * i2._2 + i1._2 * i2._1)
    }

    val im1 = (5.0, 2.0);
    val im2 = (3.5, 2.4);

    println(imPlus(im1, im2));
    println(imMult(im1, im2));
  }
}
