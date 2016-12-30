/**
 * Sequence translation is a trait.
 */

package bio
import org.biojava.bio.symbol._
import org.biojava.bio.seq._

class SequenceTranslation[T] {
  /**
   * Translate RNA to amino acids using the BioJAVA library.
   *
   * When ambiguous symbols are used (IUPAC) the codon
   * translates to X. Which it apparently does with BioJava3.
   *
   * Also for BioJava3 the sequence needs to consist of true
   * codons. In this function the tail is chopped to resize,
   * unlike the EMBOSS version, which will even translate
   * a partial codon, when possible.
   *
   * With translation triple gaps are translated to a single AA
   * gap. In fact, anything with a gap gets to be a gap. When
   * an unknown character is introduced the AA becomes X. So:
   *
   *   "agc---a-n" translates to "S--"
   *   "agc---agn" translates to "S-X"
   */
  def translate(nucleotides: List[T]): List[Protein.AASymbol] = {
    val remove = nucleotides.size % 3
    val rna = RNATools.createRNA(nucleotides.dropRight(remove).mkString);
    val aa = RNATools.translate(rna);
    Protein.IUPACGappedConvert.fromString(aa.seqString)
  }
}

package DNA {

  object SequenceTranslation extends SequenceTranslation[RNA.Nucleotide]
  object SymbolSequenceTranslation extends SequenceTranslation[RNA.NTSymbol]
}
package RNA {

  object SequenceTranslation extends SequenceTranslation[RNA.Nucleotide]
  object SymbolSequenceTranslation extends SequenceTranslation[RNA.NTSymbol]
}
