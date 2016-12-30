/**
 * Codon is an AminoAcid with its Codon information (DNA sequence)
 * Both the AminoAcid and the DNA sequence can be amibiguous (IUPAC)
 *
 * Note: the implementation may change to an Attribute version
 */

package bio

package Protein {

  sealed abstract class CodonSymbol {
    def getAminoAcid: AASymbol
    def getCodon: List[DNA.NTSymbol]
  }
  sealed abstract class CodonGap extends CodonSymbol
  case object CodonGap extends CodonGap {
    val getAminoAcid = Protein.Gap
    val getCodon = List(DNA.Gap, DNA.Gap, DNA.Gap)
    override def toString = "-"
  }

  /**
   *  The Codon stores an (ambiguous) AminoAcid with the matching DNA
   *  sequence (also ambiguous, and including gaps). Note that this is the
   *  most open implementation with the purpose of storing codons and their
   *  amino acids in matching alignments.
   */
  case class Codon(val aa: AminoAcid, val codon: List[DNA.NTSymbol]) extends CodonSymbol {
    val getAminoAcid = aa
    val getCodon = codon
  }
}
