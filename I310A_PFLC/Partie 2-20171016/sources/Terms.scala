package lp

object Terms {

  var debug = false;

  trait Term {
  
    def map(s: Subst): Term = this match {
      case Var(a) =>  lookup(s, a) match {
        case Some(b) => b map s
        case None => this
      }
      case Con(a, ts) =>  Con(a, ts map (t => t map s))
    }
    
    def vars: List[String] = this match {
      case Var(a) => List(a)
      case Con(a, ts) => (ts flatMap (t => t.vars)).removeDuplicates
    }

    def tailToString(t: Term): String = t match {
      case Con("nil", List()) => "]"
      case Con("cons", List(fst, snd)) => ", " + fst + tailToString(snd) 
      case _ => "|" + t + "]"
    }
    
    override def toString(): String = termToInt(this) match {
      case Some(n) => n.toString()
      case None => this match {
        case Var(a) => a
        case Con("nil", List()) => "[]"
        case Con("cons", List(fst, snd)) => "[" + fst + tailToString(snd) 
        case Con(a, ts) => a + (if (ts.isEmpty) "" else ts.mkString("(", ",", ")"));
      }
    }
  }
  case class Var(a: String) extends Term;
  case class Con(a: String, ts: List[Term]) extends Term;
  
  def termToInt(t: Term): Option[Int] = t match {
    case Con("zero", List()) => Some(0)
    case Con("succ", List(u)) => termToInt(u) match {
      case None => None
      case Some(n) => Some(n + 1)
    }
    case _ => None
  }
  
  case class Binding(name: String, term: Term) {
    term match { case Var(n) if (name == n) => error("bad binding") case _ => () }
    override def toString() = name + " = " + term;
  }

  type Subst = List[Binding];
  
  def lookup(s: Subst, name: String): Option[Term] = s match {
    case List() => None
    case b :: s1 => if (name == b.name) Some(b.term) else lookup(s1, name)
  }

  private var count = 0;
  def newVar(prefix: String) = { count = count + 1; Var(prefix + count) }

  val NoTerm = Con("<none>", List());

  def unify1(x: Term, y: Term, s: Subst): Option[Subst] = Pair(x, y) match {
    case Pair(Var(a), Var(b)) if (a == b) =>
      Some(s)
    case Pair(Var(a), _) => lookup(s, a) match {
      case Some(x1) => unify(x1, y, s)
      case None => if ((y map s).vars contains a) None else Some(Binding(a, y) :: s)
    }
    case Pair(_, Var(b)) =>
      unify(y, x, s)
    case Pair(Con(a, xs), Con(b, ys)) if (a == b) =>
      unify(xs, ys, s)
    case _ => None
  }

  def unify(x: Term, y: Term, s: Subst): Option[Subst] = {
    val ss = unify1(x, y, s);
    if (debug) Console.println("unify " + x + " with " + y + " = " + ss);
    ss
  }

  def unify(xs: List[Term], ys: List[Term], s: Subst): Option[Subst] = Pair(xs, ys) match {
    case Pair(List(), List()) => Some(s)
    case Pair(x :: xs1, y :: ys1) =>
      unify(x, y, s) match {
	case Some(s1) => unify(xs1, ys1, s1)
	case None => None
      }
    case _ => None
  }
}
