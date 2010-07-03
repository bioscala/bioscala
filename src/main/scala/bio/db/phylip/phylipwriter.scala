/**
 * PhylipWriter writes a list of sequences to a file in PHYLIP format, using
 * BioJava's PHYLIPFileFormat.writer. 
 *
 * There are two problems with this implementation: First the JAVA library
 * shortens the descriptor to 9 chars. I shorten them here to 8 since I use
 * this format for PAML, and it wants two spaces. The other problem is that
 * when writing an alignment the sequences can have different sizes - they
 * simply write to file.
 */

import java.io._
import org.biojava.bio.alignment._
import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import org.biojavax.bio.phylo.io.phylip._


package bio {

  object PhylipWriter {
    def writeFile[T <: AbstractSequence](file: File, list: List[T]) : Unit = {
      val reflist = new java.util.ArrayList[org.biojava.bio.alignment.AlignmentElement](1)
      
      list.foreach { seq => 
        println(seq.id)
        println(seq)
        val dna = DNATools.createDNA(seq.toString);
        val id = seq.id.take(8).toString
        val ref = new SimpleAlignmentElement(id, dna, new RangeLocation(1, dna.length))
        reflist.add(ref)
      }
      val aln = new FlexibleAlignment(reflist)

      PHYLIPFileFormat.writeFile(file,aln)
    }
  } // PhilipWriter

} // bio

