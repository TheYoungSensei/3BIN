import Terms._;
import Programs._;

object Prolog extends Interpreter {

  private var firstClauses: List[Clause] = List()
  private var lastClauses: List[Clause] = List()
  private def program = firstClauses ::: lastClauses
  private var solutions: Stream[Subst] = Stream.empty
  private var vs: List[String] = List()

  val name = "Mini-Prolog Interpreter"
  val prompt = "prolog> "
  val commentDelim = "%"

  def switchDebug: Unit = Terms.debug = ! Terms.debug

  def load(code: String): Unit = {
    firstClauses = program
    lastClauses = List()
    process(code)
  }

  def unloadLast: Unit = {
    lastClauses = List()
  }

  def process(code: String): Unit =
    try {
      new Parser(code).all foreach { c =>
        if (c.lhs == NoTerm) {
          c.rhs match {
            case List(Con("more", List())) =>
              if (solutions.nonEmpty)
                solutions = solutions.tail
            case _ =>
              solutions = solve(c.rhs, program)
            vs = c.vars;
          }
          if (solutions.isEmpty) {
            Console.println("no")
          } else {
            val s: Subst = solutions.head
            .filter(b => vs contains b.name)
            .map(b => Binding(b.name, b.term map solutions.head))
            .reverse
            if (s.isEmpty) Console.println("yes")
            else Console.println(s)
          }
        } else {
          lastClauses = lastClauses ::: List(c)
        }                                   }
    } catch { case exc: Throwable => Console.println(exc) }

  def main(args: Array[String]): Unit = {
    if (args.length > 0)
      load(args(0))
    run
  }
  
}