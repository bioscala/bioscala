/**
 * PamlWriter writes a list of sequences to a file in Paml format.
 *
 */

import java.io._
import org.biojava.bio.alignment._
import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import org.biojavax.bio.phylo.io.phylip._


package bio {

  class PamlWriter(val fout: FileOutputStream) {
    class PamlWriterException(string: String) extends Exception(string)
      var sequence_width = 50

      def this(f: File) = this(new FileOutputStream(f))
      def this(filen: String) = this(new FileOutputStream(filen) )
    
    /**
     * Write PAML Paml format. The difference with the standard Paml 
     * implementation is that it writes longer ID's and makes sure there are two 
     * spaces between ID and sequence, as PAML wants it.
     *
     * Example:
     *
     *   import bio.io.Control._
     *   val tmpfn = File.createTempFile("BioScala-PAML-",".phy")
     *   using(new FileOutputStream(tmpfn)) { stream =>
     *     new PamlWriter(stream).write(seqlist)
     *   }
     */
    def write[T <: AbstractSequence](list: List[T]) : Unit = {
      val size = list.head.length
      val writer = new OutputStreamWriter(fout)
      writer.write(list.length.toString+"   "+size.toString+"\n")
      list.foreach { seq =>
        if (seq.length != size)
          throw new PamlWriterException("Sequence not same size: "+seq.id)
        def write_seq(list: List[Any]) : Unit = {
          val (w,rest) = list.splitAt(sequence_width)  
          writer.write(w.mkString+"\n")
          rest match { 
            case Nil => 
            case _ => 
              writer.write(" "*(seq.id.length+2))
              write_seq(rest)
          }
        }
        if (sequence_width > 0) {
          writer.write(seq.id+"  ")
          write_seq(seq.toList)
        }
        else {
          writer.write(seq.id+"  "+seq.toString+"\n")
        }
      }
      writer.flush // expected before close(!)
    }
  } // PamlWriter

} // bio

