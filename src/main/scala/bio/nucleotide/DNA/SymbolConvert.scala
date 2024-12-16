package bio.nucleotide.DNA

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
      case 't' => T
      case 'n' => bio.chemistry.DNA.N
      case '-' => bio.chemistry.DNA.Gap
      case _   => throw new IllegalArgumentException("Unexpected value for nucleotide " + c)
    }
  }

  def fromItem(i: NTSymbol): NTSymbol = i
}
