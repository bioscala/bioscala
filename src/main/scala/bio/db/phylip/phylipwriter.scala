/**
 * PhylipWriter writes a file in PHYLIP format. It is defined as a singleton
 * object, so you don't need to instantiate it.
 */

import java.io._
import org.biojava.bio.alignment._
import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import org.biojavax.bio.phylo.io.phylip._


package bio {

  object PhylipWriter {
    def writeFile(file: File, list: List[Any]) : Unit = {
      println(file)
      val seq = list.head
      val dna = DNATools.createDNA(seq.toString);

      val ref = new SimpleAlignmentElement("reference", dna, new RangeLocation(1, dna.length))
      val ref2 = new SimpleAlignmentElement("seq", dna, new RangeLocation(1, dna.length))
      val reflist = new java.util.ArrayList[org.biojava.bio.alignment.AlignmentElement](1)
      reflist.add(ref)
      reflist.add(ref2)
      val aln = new FlexibleAlignment(reflist)
      // aln.addSequence(ref2) - you can add sequences later

      PHYLIPFileFormat.writeFile(file,aln)

      // val out = new FileWriter(file)
      // out.write("hello file!")
      // out.close
    }
  } // PhilipWriter
} // bio

