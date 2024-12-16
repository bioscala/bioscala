package bio.chemistry

import bio.chemistry.Protein.StringConverter

class GappedConvert[T](Gap: T, base: StringConverter[T]) extends StringConverter[T] {

  /** Create a Gap object from its character representation.
    */
  def fromChar(c: Char): T = {
    c.toLower match {
      case '-' => Gap
      case _ =>
        base.fromChar(c)
    }
  }

  def fromItem(i: T): T = {
    i match {
      case Gap => Gap
      case _ =>
        base.fromItem(i)
    }
  }
}
