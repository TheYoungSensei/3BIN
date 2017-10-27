import Terms._;
import Programs._;

class Parser(s: String) {
  val it = new Tokenizer(s, "(),.?[]|")

  var token: String = it.next

  private def syntaxError(msg: String) = println(msg + ", but " + token + " found")

  def rep[a](p: => a): List[a] = {
    val t = p
    if (token == ",") { token = it.next; t :: rep(p) } else List(t)
  }

  def constructor: Term = {
    def intToTerm(n: Int): Term =
      if (n == 0) Con("zero", List())
      else Con("succ", List(intToTerm(n - 1)))
    val a = token
    token = it.next
    try {
      intToTerm(Integer.parseInt(a))
    } catch {
      case _: Throwable => // NumberFormatException
        Con(a,
	    if (token equals "(") {
	      token = it.next
	      val ts: List[Term] = if (token equals ")") List() else rep(term)
	      if (token equals ")") token = it.next else syntaxError("`)' expected")
	      ts
	    } else List())
    }
  }

  private def toProlog(l: List[Term], tail: Term): Term = l match {
    case Nil => tail
    case x :: xs => Con("cons", List(x, toProlog(xs, tail)))
  }
  
  val prologNil = Con("nil", List());
  
  def listTerm: Term =
    if (token equals "]") {
      token = it.next;
      prologNil
    }
    else {
      var ts = List(term); 
      while (token equals ",") {
        token = it.next;
        ts = term :: ts;
      }
      val tail =
        if (token equals "|") {
          token = it.next;
          term
        }
        else
          prologNil;
      if (token equals "]") {
        token = it.next;
        toProlog(ts.reverse, tail)
      }
      else
        syntaxError("']' expected")
        throw new Exception
    }

  def term: Term = {
    val ch = token.charAt(0);
    if (ch == '[') { token = it.next; listTerm }
    else if (Character.isUpperCase(ch)) { val a = token; token = it.next; Var(a) }
    else if (it.isDelimiter(ch)) { syntaxError("term expected"); null }
    else constructor
  }

  def line: Clause = {
    val result =
      if (token equals "?") {
        token = it.next;
        Clause(NoTerm, rep(constructor));
      } else {
	Clause(
          constructor,
          if (token equals ":-") { token = it.next; rep(constructor) } else List())
      }
    if (token equals ".") token = it.next else syntaxError("`.' expected");
    if (debug) {
      if (result.lhs == NoTerm) Console.println("query " + result.rhs)
      else Console.println("add " + result)
    }
    result
  }

  def all: List[Clause] = if (token equals "") List() else line :: all;
}