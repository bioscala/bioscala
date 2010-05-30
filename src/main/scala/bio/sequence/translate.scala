/**
 * Sequence translation is a trait.
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  class SequenceTranslation[T] {
    /** 
     * Translate nucleotides to amino acids
     *
     * When ambiguous symbols are used (IUPAC) the codon should
     * translate to X. Which it apparently does with BioJava3.
     *
     * Also for BioJava the sequence needs consist of true
     * codons. In this function the tail is chopped to resize, 
     * unlike the EMBOSS version, which will even translate 
     * a partial codon, when possible.
     */
    def translate(nucleotides: List[T]): List[Protein.AminoAcid] = {
      val remove = nucleotides.size % 3
      val rna = RNATools.createRNA(nucleotides.dropRight(remove).mkString);
      val aa = RNATools.translate(rna);
      Protein.IUPACAminoAcidConvert.fromString(aa.seqString)
    }
  }

  package DNA {

    object SequenceTranslation extends SequenceTranslation[Nucleotide]
    object SymbolSequenceTranslation extends SequenceTranslation[NTSymbol]
  }
  package RNA {

    object SequenceTranslation extends SequenceTranslation[Nucleotide]
    object SymbolSequenceTranslation extends SequenceTranslation[NTSymbol]
  }

}
