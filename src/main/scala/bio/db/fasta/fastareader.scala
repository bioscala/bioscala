/**
 * FastaReader iterates over a FASTA file, returning Sequence objects, without
 * storing the whole database in RAM (unlike many other implementations).
 */

import java.io._

package bio {

  class FastaReadError(string: String) extends Exception(string)

  class FastaReader(filename: String) extends Iterator[String] {
    private lazy val reader = new BufferedReader(new FileReader(filename))

    def hasNext() = reader.ready
    def next(): String = {
      // Read the tag line
      val tag = reader.readLine
      if (tag(0) != '>')
        throw new FastaReadError("record start expected")
      var sequencelist = List("")
      do {
        reader.mark(512)
        val line = reader.readLine
        println(line)
        if (line(0) == '>') {
          reader.reset
          return(new DNA.Sequence(tag,sequencelist.mkString).toString)
        }
        sequencelist += line
      } while (reader.ready)
      ""
    }
    /*
    def foreach(f:String => Unit) = {
       try {
         while (reader.ready) {
           val s = reader.readLine
           return s
         }
       }
       finally reader.close
    }
    */
  }

}

