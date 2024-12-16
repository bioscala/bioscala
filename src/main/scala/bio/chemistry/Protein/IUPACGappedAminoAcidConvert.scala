package bio.chemistry.Protein

import bio.chemistry.Protein

object IUPACGappedAminoAcidConvert extends StringConverter[AASymbol] {
  /** Create a IUPAC object from its character representation.
   */
  def fromChar(c: Char): AASymbol = {
    c.toUpper match {
      case '-' => Protein.Gap
      case 'B' => B
      case 'Z' => Z
      case 'X' => X
      case _ =>
        AminoAcidConvert.fromChar(c)
    }
  }

  def fromItem(i: AASymbol): AASymbol = {
    i match {
      case Protein.Gap => Protein.Gap
      case B           => B
      case Z           => Z
      case X           => X
      case _ =>
        throw new IllegalArgumentException("Unexpected type " + i + " type " + i.getClass.getName)
    }
  }
}
