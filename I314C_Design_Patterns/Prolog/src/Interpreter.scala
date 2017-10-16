abstract class Interpreter {

  // Abstract methods.
  val prompt: String;
  val name: String;
  val commentDelim: String;
  def switchDebug: Unit;
  def process(code: String): Unit;
  def load(code: String): Unit;
  def unloadLast: Unit;  

  private var debug: Boolean = false;
  private var loadedFilenames: List[String] = Nil;

  private def read(filename: String): String = {
    import java.io._;
    try {
      val stream = new BufferedReader(new FileReader(filename));
      val buffer = new StringBuffer();
      def loop: String = {
        val line = stream.readLine();
        if (line == null) {
          buffer.toString();
        } else {
          val inx = line.indexOf(commentDelim); // comment
          if (inx < 0) buffer.append(line)
          else if (inx > 0) buffer.append(line.substring(0, inx));
          loop
        }
      }
      loop
    } catch {
      case (exception: FileNotFoundException) =>
        println("not found: " + filename);
        ""
      case (exception: IOException) =>
        println("can't read: " + filename);
        ""
    }
  }

  private def help: Unit = {
    Console.println(":?       display this message");
    Console.println(":l<file> load file with name <file>");
    Console.println(":r       reload the last loaded file");
    Console.println(":d       switch to/from debug mode (# in prompt)");
    Console.println(":q       quit the interpreter");
  }
  
  def run: Unit = {
    var filename = "";
    var iprompt = prompt;
    var continue = true;
    val in = new java.io.DataInputStream(System.in);
    Console.println(name + " (type \":?\" for help)");
    while (continue) {
      System.out.print(iprompt);
      val line = in.readLine();
      if (line.startsWith(":?"))
        help
      else if (line startsWith ":d") {
        switchDebug;
        debug = !debug;
        iprompt = if (debug) "#" + prompt else prompt
      }
      else if (line.startsWith(":q")) {
        continue = false
      } else if (line.startsWith(":l")) {
        filename = line.substring(2).trim();
        if (loadedFilenames.contains(filename))
          Console.println("warning: already loaded file (" + filename + ") can lead to inconstency.")
        else
          loadedFilenames = filename :: loadedFilenames;
        load(read(filename))
      } else if (line.startsWith(":r")) {
        if (loadedFilenames.isEmpty)
          Console.println("warning: no file loaded for now.")
        else {
          unloadLast;
          load(read(filename))
        }
      } else {
        process(line)
      }
    }
  }
  
}