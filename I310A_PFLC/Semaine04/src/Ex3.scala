
object Ex3 {

  def main(args: Array[String]): Unit = {
    // borrowed from http://blog.tmorris.net/scala-exercises-for-beginners/

    // output should be :
    //
    // 5+3=8
    // sum(1 :: 3 :: 5 :: Nil)=9
    // length(1 :: 3 :: 5 :: Nil)=3
    // filter(1 :: 2 :: 3 :: 5 :: Nil, isEven)=List(2)
    // append(1 :: 2 :: Nil, 3 :: 4 :: Nil)=List(1, 2, 3, 4)
    // concat(List(List(1, 2), List(3)))=List(1, 2, 3)
    // maximum(List(1, 7, 3, 9, 10, 5))=10
    // reverse(List(1, 2, 3, 4, 5))=List(5, 4, 3, 2, 1)

    def succ(n: Int) = n + 1
    def pred(n: Int) = n - 1

    def add(x: Int, y: Int): Int = {
      // todo: Assume x and y are 0 or positive. Do not use + or - on Int. Only permitted to use succ/pred (above).
      if(x==0){y} else { add(pred(x), succ(y))}
    }

    println("5+3=" + add(5, 3))

    def sum(x: List[Int]): Int = {
      //with case
      /*x match {
        case Nil => 0;
        case y:: ys => add(y, sum(ys));       
      }*/
      x.foldRight(0)(add);
    }

    println("sum(1 :: 3 :: 5 :: Nil)=" + sum(1 :: 3 :: 5 :: Nil))

    def length[A](x: List[A]): Int = {
      /*x match {
        case Nil => 0;
        case y::ys => succ(length(ys));
      }*/
      x.foldLeft(0)((a,_) => a+1)
    }

    println("length(1 :: 3 :: 5 :: Nil)=" + length(1 :: 3 :: 5 :: Nil))

    def filter[A](x: List[A], f: A => Boolean): List[A] = {
      /*x match {
        case Nil => Nil;
        case e::es => if(f(e)) e::filter(es, f) else filter(es, f);
      }*/
      for(e <- x if f(e)) yield e
    }

    println("filter(1 :: 2 :: 3 :: 5 :: Nil, isEven)=" + filter(1 :: 2 :: 3 :: 5 :: Nil, (x:Int) => (x % 2) == 0))

    def append[A](x: List[A], y: List[A]): List[A] = {
      /*x match {
        case Nil => y
        case x::xs => x::append(xs, y)
      }*/
      for(e <- List(x,y); ee<-e) yield ee
    }

    println("append(1 :: 2 :: Nil, 3 :: 4 :: Nil)=" + append(1 :: 2 :: Nil, 3 :: 4 :: Nil))

    def concat(x: List[List[Int]]): List[Int] = {
      /*x match {
        case Nil => Nil
        case xs::xss => append(xs, concat(xss))
      }*/
      for(e <- x; ee<-e) yield ee;
    }

    println("concat(List(List(1, 2), List(3)))=" + concat(List(List(1, 2), List(3))))

    def maximum(x: List[Int]): Int = {
      x.foldLeft(Int.MinValue)(Math.max)
    }

    println("maximum(List(1, 7, 3, 9, 10, 5))=" + maximum(List(1, 7, 3, 9, 10, 5)))

    /*def reverse[A](x: List[A]): List[A] = {
      def reverseInner(x : List[Int], list : List[Int]): List[Int] = {
        list match {
          case Nil => x
          case xs::xss => reverseInner(xs :: x, xss)
        }
      }
      reverseInner(Nil, x)

    }

    println("reverse(List(1, 2, 3, 4, 5))=" + reverse(List(1, 2, 3, 4, 5)))*/

    def map[A](x : List[A], f:A => A) : List[A] = {
      for (e <- x) yield f(e)
    }

    def multiplication(m: Int)(e : Int): Int = m * e
    println("map(List(1, 2, 3, 4, 5), multiplication(2))=" + map(List(1,2,3,4,5), multiplication(2)))

    def concatMap[A](x : List[A], f: A => A):List[A] = {
      System.out.println("On ne doit pas le faire")
      Nil
    }

    println("concatMap(List(1,2,3,4,5), multiplication(2))=" + concatMap(List(1,2,3,4,5), multiplication(2)))
  }

}