/**
 * Sequence translation is a trait.
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  trait SequenceTranslation {
    def translate = {
      val nt = toString
      println(nt)
      val dna = DNATools.createDNA(nt);
      println(dna.seqString)
      val rna = DNATools.toRNA(dna);
      println(rna.seqString)
      val aa = RNATools.translate(rna);
      println(aa.seqString)
      aa.seqString
    }
  }

}
