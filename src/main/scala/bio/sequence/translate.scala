/**
 * Sequence translation is a trait.
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  class SequenceTranslation[T] {
    /** 
     * Translate nucleotides to amino acids (will change to returning List)
     */
    def translate(nucleotides: List[T]): String = {
      val rna = RNATools.createRNA(nucleotides.mkString);
      val aa = RNATools.translate(rna);
      aa.seqString
    }
  }

  package DNA {

    object SequenceTranslation extends SequenceTranslation[Nucleotide]
    object SymbolSequenceTranslation extends SequenceTranslation[Symbol]
  }
  package RNA {

    object SequenceTranslation extends SequenceTranslation[Nucleotide]
  }


}
