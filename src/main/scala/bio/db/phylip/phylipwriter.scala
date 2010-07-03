/**
 * PhylipWriter writes a list of sequences to a file in PHYLIP format.
 *
 */

import java.io._
import org.biojava.bio.alignment._
import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import org.biojavax.bio.phylo.io.phylip._


package bio {

  object PhylipWriter {
    class PhylipWriterException(string: String) extends Exception(string)

    /**
     * PhylipWriter writes a list of sequences to a file in PHYLIP format, using
     * BioJava's PHYLIPFileFormat.writer. 
     *
     * There are two problems with this implementation: First the BioJava
     * library shortens the descriptor to 9 chars. The other BioJava problem is
     * that when writing an alignment the sequences can have different sizes -
     * they simply write to file. In contrast, the BioScala implementation
     * throws an exception.
     */
    def writeFile[T <: AbstractSequence](file: File, list: List[T]) : Unit = {
      val size = list.head.length
      val reflist = new java.util.ArrayList[org.biojava.bio.alignment.AlignmentElement](1)
      
      list.foreach { seq => 
        if (seq.length != size)
          throw new PhylipWriterException("Sequence not same size: "+seq.id)
        val dna = DNATools.createDNA(seq.toString);
        val id = seq.id.take(8).toString
        val ref = new SimpleAlignmentElement(id, dna, new RangeLocation(1, dna.length))
        reflist.add(ref)
      }
      val aln = new FlexibleAlignment(reflist)

      PHYLIPFileFormat.writeFile(file,aln)
    }
    /**
     * Write PAML PHYLIP format. The difference with the standard PHYLIP 
     * implementation is that it writes longer ID's and makes sure there are two 
     * spaces between ID and sequence, as PAML wants it.
     */
    def writePAMLFile[T <: AbstractSequence](file: File, list: List[T]) : Unit = {
      val size = list.head.length
      val fout = new FileWriter(file)
      fout.write(list.length.toString+"   "+size.toString+"\n")
      list.foreach { seq =>
        if (seq.length != size)
          throw new PhylipWriterException("Sequence not same size: "+seq.id)
        fout.write(seq.id+"  "+seq.toString+"\n")
      }
      fout.close
    }
  } // PhilipWriter

} // bio

