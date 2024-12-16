package bio.chemistry.Protein

import bio.nucleotide.DNA.NTSymbol

/**
 * The Codon stores an (ambiguous) AminoAcid with the matching DNA
 * sequence (also ambiguous, and including gaps). Note that this is the
 * most open implementation with the purpose of storing codons and their
 * amino acids in matching alignments.
 */
case class Codon(aa: AminoAcid, codon: List[NTSymbol]) extends CodonSymbol {
  val getAminoAcid: AASymbol = aa
  val getCodon: List[NTSymbol] = codon
}
