package bio.sequence

trait AbstractSequence {
  def id: java.lang.String

  def hasDescription: Boolean

  def description: String

  def length: java.lang.Integer

  def toList: List[Any]
}
