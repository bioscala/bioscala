package bio.nucleotide.RNA

import bio.chemistry.Protein.StringConverter

object SymbolConvert extends StringConverter[NTSymbol] {

  /** Nucleotide factory: create a Nucleotide object from its
   * character representation. For example:
   *
   * <pre>
   * import bio.DNA._
   * val nt = NucleotideConvert.fromChar('a')
   * </pre>
   */
  def fromChar(c: Char): NTSymbol = {
    c.toLower match {
      case 'a' => A
      case 'c' => C
      case 'g' => G
      case 'u' => U
      case 'n' => bio.chemistry.RNA.N
      case '-' => bio.chemistry.RNA.Gap
      case _   => throw new IllegalArgumentException("Unexpected value for nucleotide " + c)
    }
  }

  def fromItem(i: NTSymbol): NTSymbol = i
}
