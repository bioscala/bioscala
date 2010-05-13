/**
 * Sequence[T] is a generic sequence container with attributes. T is
 * strongly typed.
 * 
 * @see DNA.Sequence
 */
import bio._
import bio.attribute._

package bio {

  class Sequence[T](seqlist: List[T], attributelist: List[Attribute]) extends AttributeAccess {
    lazy val seq = seqlist
    lazy val attributes  = attributelist

    /** @return Nucleotide List as a String */
    override def toString = seq mkString
    /** @return Nucleotide List */
    def toList = seq
    /**
     * @return first (primary) ID in attribute list
     */
    def idList = attribList(GetId, attributes)
    def descriptionList = attribList(GetDescription, attributes)
    def id = attribFirst(GetId, attributes).toString
    /**
     * @return first (primary) Description in attribute list
     */
    def description = attribFirst(GetDescription, attributes).toString
  }

}
