class Tokenizer(s: String, delimiters: String) extends Iterator[String] {
  
  private var i = 0;
  private val n = s.length();

  def isDelimiter(ch: Char) = delimiters.indexOf(ch) >= 0;

  def isNeck(ch: Char) = ch == ':' && i < n && s.charAt(i) == '-';

  def hasNext: Boolean = {
    while (i < n && s.charAt(i) <= ' ') { i = i + 1 }
    i < n
  }

  def next: String =
    if (hasNext) {
      val start = i;
      var ch = s.charAt(i); i = i + 1;
      if (isDelimiter(ch)) ch.toString()
      else if (isNeck(ch)) { i = i + 1; ":-" }
      else {
        while (i < n &&
	       s.charAt(i) > ' ' &&
	       !isDelimiter(s.charAt(i))){ i = i + 1 }
        s.substring(start, i)
      }
    } else "";

}