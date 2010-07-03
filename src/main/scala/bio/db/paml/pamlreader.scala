/**
 * PamlReader writes a list of sequences to a file in Paml format.
 *
 */

import java.io._
// import org.biojava.bio.alignment._
// import org.biojava.bio.symbol._
// import org.biojava.bio.seq._
import org.biojava.bio.seq.io._
import org.biojavax.bio.phylo.io.phylip._

package bio {

  /** 
   * PamlReader opens a file and parses the PAML Phylip CODON contents using 
   * an iterator. Rather than using BioJava's PHYLIPReader, which only allows
   * 9 char tags, we roll our own. Essentially it is a simple format, where you
   * know the size of the sequences - just remove the spaces.
   */
  class PamlReader(val filename: String) extends Iterator[Tuple2[String,String]] {
    private val reader = new BufferedReader(new FileReader(filename))

    class PamlReadException(string: String) extends Exception(string)
    def header() = {
      val firstline = reader.readLine

      val Match = """^(\d+)\s+(\d+)""".r 
      val (num,size) = firstline match {
        case Match(num1,size1) => (num1.toInt,size1.toInt)
      }
      (num,size)
    }

    val (seq_num,seq_size) = header()

    def hasNext() = reader.ready

    def next(): Tuple2[String,String] = {
      // parse ID
      val line = reader.readLine.toList
      val (id,rest) = line.span { c => c != ' ' }
      // keep reading lines until we have the right size
      var seq = rest
      do {
        seq = seq.remove{ c => c == ' ' || c == '\t' }
        if (seq.length == seq_size) {
          println("Returning: ",id,seq)
          return (id.mkString,seq.mkString)
        }
        if (seq.length > seq_size)
          throw new PamlReadException("Input file problem in "+filename+" with "+id.mkString+" <"+seq.mkString+">")
        if (!reader.ready)
          throw new PamlReadException("EOF problem in "+filename)
        seq :::= reader.readLine.toList
      } while (true)
      ("","")
    }
  } // PamlReader

} // bio

