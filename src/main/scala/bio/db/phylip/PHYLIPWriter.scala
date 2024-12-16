package bio.db.phylip

import bio.sequence.AbstractSequence
import org.biojava.bio.alignment.{FlexibleAlignment, SimpleAlignmentElement}
import org.biojava.bio.seq.DNATools
import org.biojava.bio.symbol.RangeLocation
import org.biojavax.bio.phylo.io.phylip.PHYLIPFileFormat

import java.io.File

/** PHYLIPWriter writes a list of sequences to a file in PHYLIP format, using
  * BioJava's PHYLIPFileFormat.writer.
  *
  * There are two problems with this implementation: First the BioJava
  * library shortens the descriptor to 9 chars. The other BioJava problem is
  * that when writing an alignment the sequences can have different sizes -
  * they simply write to file. In contrast, the BioScala implementation
  * throws an exception.
  *
  * See also PAMLWriter for a PAML implementation of PHYLIP output
  */
class PHYLIPWriter(file: File) {
  def write[T <: AbstractSequence](list: List[T]): Unit = {
    val size = list.head.length
    val refList = new java.util.ArrayList[org.biojava.bio.alignment.AlignmentElement](1)

    list.foreach { seq =>
      if (seq.length != size)
        throw new PHYLIPWriterException("Sequence not same size: " + seq.id)
      val dna = DNATools.createDNA(seq.toString)
      val id = seq.id.take(8)
      val ref = new SimpleAlignmentElement(id, dna, new RangeLocation(1, dna.length))
      refList.add(ref)
    }
    val aln = new FlexibleAlignment(refList)

    PHYLIPFileFormat.writeFile(file, aln)
  }
}

object PHYLIPWriter {
  def apply(file: File) = new PHYLIPWriter(file)
}
