package bio.chemistry.Protein

/** Codon is an AminoAcid with its Codon information (DNA sequence)
  * Both the AminoAcid and the DNA sequence can be ambiguous (IUPAC)
  *
  * Note: the implementation may change to an Attribute version
  */
abstract class CodonSymbol private[Protein] {
  def getAminoAcid: AASymbol

  def getCodon: List[bio.nucleotide.DNA.NTSymbol]
}
