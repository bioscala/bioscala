package bio.sequence

import bio.attribute._

import scala.language.postfixOps

/** Sequence[T] is a generic sequence container with attribute. T is
  * strongly typed.
  *
  * @see DNASequence and RNASequence
  */
abstract class Sequence[T](seqList: List[T], attributeList: List[Attribute])
    extends AbstractSequence
    with AttributeAccess {
  type SequenceType <: Sequence[T] // Abstract type

  lazy val seq: List[T] = seqList
  lazy val attributes: List[Attribute] = attributeList

  /** Every derived class should have a factory, so methods like 'delete',
    * defined in the abstract class, work in a type safe way.
    */
  def create(seqList: List[T], attributeList: List[Attribute]): SequenceType

  /** @return the n-th element of this Sequence. The first element is at position 0. */
  def apply(n: Int): T = seq(n)

  /** @return Nucleotide List as a String */
  override def toString: String = seq mkString

  /** @return Nucleotide List */
  def toList: List[T] = seq

  def idList: List[Attribute] = attribList(GetId, attributes)

  def descriptionList: List[Attribute] = attribList(GetDescription, attributes)

  def id: String = {
    attribFirst(GetId, attributes) match {
      case Some(s) => s.toString
      case _       => "No ID"
    }
  }

  def length: Integer = seq.length

  /** Delete part of the sequence */
  def delete(pos: Int, num: Int): SequenceType = {
    create(seq.take(pos) ::: seq.takeRight(seq.size - pos - num), attributes)
  }

  def hasDescription: Boolean = attribFirst(GetDescription, attributes) match {
    case Some(_: String) => true
    case _               => false
  }

  /** @return first (primary) Description in attribute list
    */
  def description: String = {
    attribFirst(GetDescription, attributes) match {
      case Some(s: String) => s
      case _               => "No description"
    }
  }

  /** Add an attribute to Sequence and return a new Sequence object
    */
  def attrAdd(attribute: Attribute): SequenceType =
    create(seq, attribute :: attributes)

  /** Add a list of attribute to Sequence and return a new Sequence object
    */
  def attrAdd(attributeList: List[Attribute]): SequenceType =
    create(seq, attributes ::: attributeList)
}
