/**
 * FastaReader iterates over a FASTA file, returning Sequence objects, without
 * storing the whole database in RAM (unlike many other implementations).
 *
 * Sequences are expected to be represented in the standard IUB/IUPAC amino
 * acid and nucleic acid codes, with these exceptions: lower-case letters
 * are accepted and are mapped into upper-case; a single hyphen or dash can
 * be used to represent a gap of indeterminate length; and in amino acid
 * sequences, U and * are acceptable letters (see below). Before submitting
 * a request, any numerical digits in the query sequence should either be
 * removed or replaced by appropriate letter codes (e.g., N for unknown
 * nucleic acid residue or X for unknown amino acid residue).
 */

import java.io._

package bio {

  class FastaReadException(string: String) extends Exception(string)

  class FastaReader(filename: String) extends Iterator[Tuple3[String,String,String]] {
    private lazy val reader = new BufferedReader(new FileReader(filename))

    def hasNext() = reader.ready
    def next(): Tuple3[String,String,String] = {
      // Read the tag line
      val tag = reader.readLine
      if (tag(0) != '>')
        throw new FastaReadException("record start expected")
      var sequencelist = ""
      do {
        reader.mark(512)
        val line = reader.readLine
        if (line(0) == '>') {
          reader.reset
          // Remove prepending '>'
          val tag2 = tag.drop(1).trim
          val id = tag2.split(Array(' ','\t'))(0)
          return (id,tag2,sequencelist)
        }
        sequencelist += line
      } while (reader.ready)
      ("","","")
    }
  }

}

