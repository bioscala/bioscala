package bio.chemistry.Protein

object IUPACAminoAcidConvert extends StringConverter[AminoAcid] {
  /** Create a IUPAC object from its character representation.
   */
  def fromChar(c: Char): AminoAcid = {
    c.toUpper match {
      case 'B' => B
      case 'Z' => Z
      case 'X' => X
      case _ =>
        AminoAcidConvert.fromChar(c)
    }
  }

  def fromItem(i: AminoAcid): AminoAcid = {
    i match {
      case B => B
      case Z => Z
      case X => X
      case _ =>
        throw new IllegalArgumentException("Unexpected type " + i + " type " + i.getClass.getName)
    }
  }
}