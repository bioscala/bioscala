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

    /** @return the n-th element of this Sequence. The first element is at position 0. */
    def apply(n: Int): T = seq(n)

    /** @return Nucleotide List as a String */
    override def toString = seq mkString
    /** @return Nucleotide List */
    def toList = seq
    def idList = attribList(GetId, attributes)
    def descriptionList = attribList(GetDescription, attributes)
    def id = { attribFirst(GetId, attributes) match {
        case None => "No ID"
        case Some(s) => s
      }
    }
    /**
     * @return first (primary) Description in attribute list
     */
    def description = { attribFirst(GetDescription, attributes) match {
        case None => "No description"
        case Some(s) => s
      }
    }
  }

}
