package bio.nucleotide.DNA

import bio.chemistry.Protein.StringConverter

object NucleotideConvert extends StringConverter[Nucleotide] {
  /**
   * Nucleotide factory: create a Nucleotide object from its
   * character representation. For example:
   *
   * <pre>
   * import bio.DNA._
   * val nt = NucleotideConvert.fromChar('a')
   * </pre>
   */
  def fromChar(c: Char): Nucleotide = {
    c.toLower match {
      case 'a' => A
      case 'c' => C
      case 'g' => G
      case 't' => T
      case _ => throw new IllegalArgumentException("Unexpected value for nucleotide " + c)
    }
  }

  def fromItem(i: Nucleotide): Nucleotide = {
    i match {
      case A => A
      case C => C
      case G => G
      case T => T
      case _ => throw new IllegalArgumentException("Can not construct DNA from unknown " + i + " type (should be DNA) " + i.getClass.getName)
    }
  }
}
