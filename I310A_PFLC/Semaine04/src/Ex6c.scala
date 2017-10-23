object Ex6c {

  def main(args: Array[String]): Unit = {
    try {
      throw new Exception()
    } catch {
        case e:ArithmeticException => println("Erreur arithm�tique")
        case e:RuntimeException => println("Runtime exception "+e.getMessage())
        case e => e.printStackTrace()
        case _ => println("erreur")
    }
  }
  
}