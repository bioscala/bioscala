/**
 * Sequence translation is a trait.
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  object SequenceTranslation {
    /** 
     * Translate nucleotides to amini acids (will change to returning List)
     */
    def translate(nucleotides: List[Nucleotide]): String = {
      val rna = RNATools.createRNA(nucleotides.mkString);
      val aa = RNATools.translate(rna);
      aa.seqString
    }
  }

}
