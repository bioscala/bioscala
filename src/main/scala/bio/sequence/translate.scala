/**
 * Sequence translation is a trait.
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  object SequenceTranslation {
    def translate(nucleotides: List[Nucleotide]): String = {
      val rna = RNATools.createRNA(nucleotides.mkString);
      // println(rna.seqString)
      val aa = RNATools.translate(rna);
      // println(aa.seqString)
      aa.seqString
    }
  }

}
