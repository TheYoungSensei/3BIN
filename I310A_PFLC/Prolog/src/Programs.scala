import Terms._;

object Programs {

  case class Clause(lhs: Term, rhs: List[Term]) {
    def vars =
      (lhs.vars ::: (rhs flatMap (t => t.vars))).distinct
    def newInstance = {
      var s: Subst = List()
      for (a <- vars) { s = Binding(a, newVar(a)) :: s }
      Clause(lhs map s, rhs map (t => t map s))
    }
    override def toString() =
      lhs.toString() + " :- " + rhs.mkString("", ",", "") + "."
  }

  def list2stream[a](xs: List[a]): Stream[a] = xs match {
    case List() => Stream.empty
    case x :: xs1 => Stream.cons(x, list2stream(xs1))
  }
  def option2stream[a](xo: Option[a]): Stream[a] = xo match {
    case None => Stream.empty
    case Some(x) => Stream.cons(x, Stream.empty)
  }

  def solve(query: List[Term], clauses: List[Clause]): Stream[Subst] = {

    def solve2(query: List[Term], s: Subst): Stream[Subst] = query match {
      case List() =>
	Stream.cons(s, Stream.empty)
      case Con("not", qs) :: query1 =>
	if (solve1(qs, s).isEmpty) solve1(query1, s)
	else Stream.empty
      case q :: query1 =>
	for (clause <- list2stream(clauses);
	     s1 <- tryClause(clause.newInstance, q, s);
	     s2 <- solve1(query1, s1)) yield s2
    }

    def solve1(query: List[Term], s: Subst): Stream[Subst] = {
      val ss = solve2(query, s)
      if (debug) Console.println("solved " + query + " = " + ss)
      ss
    }

    def tryClause(c: Clause, q: Term, s: Subst): Stream[Subst] = {
      if (debug) Console.println("trying " + c)
      for (s1 <- option2stream(unify(q, c.lhs, s));
	   s2 <- solve1(c.rhs, s1)) yield s2
    }

    solve1(query, List())
  }
}
