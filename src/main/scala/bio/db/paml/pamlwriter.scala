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

      def this(f: File) = this(new FileOutputStream(f))
      def this(filen: String) = this(new FileOutputStream(filen) )
    
    /**
     * Write PAML Paml format. The difference with the standard Paml 
     * implementation is that it writes longer ID's and makes sure there are two 
     * spaces between ID and sequence, as PAML wants it.
     */
    def write[T <: AbstractSequence](list: List[T]) : Unit = {
      val size = list.head.length
      val writer = new OutputStreamWriter(fout)
      writer.write(list.length.toString+"   "+size.toString+"\n")
      list.foreach { seq =>
        if (seq.length != size)
          throw new PamlWriterException("Sequence not same size: "+seq.id)
        writer.write(seq.id+"  "+seq.toString+"\n")
      }
      writer.close
    }
  } // PamlWriter

} // bio

