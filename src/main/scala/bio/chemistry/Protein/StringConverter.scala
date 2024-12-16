package bio.chemistry.Protein

abstract class StringConverter[T] {

  /** Create an object from its character representation.
    */
  def fromChar(c: Char): T

  /** Create an object from an object - fails on Unknown type
    */
  def fromItem(i: T): T

  def fromString(s: String): List[T] = s.toList.map {
    fromChar
  }

  def fromList(list: List[T]): List[T] = list.map {
    fromItem
  }
}
