package bio.chemistry.Protein

import bio.chemistry.Protein

object SymbolConvert extends StringConverter[AASymbol] {
  def fromChar(c: Char): AASymbol = {
    c.toUpper match {
      case '-' => Protein.Gap
      case _ =>
        AminoAcidConvert.fromChar(c)
    }
  }

  def fromItem(i: AASymbol): AASymbol = {
    i match {
      case Protein.Gap => Protein.Gap
      case _ =>
        throw new IllegalArgumentException("Unexpected type " + i + " type " + i.getClass.getName)
    }
  }
}
