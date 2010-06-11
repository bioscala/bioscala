/**
 * Sequence[T] is a generic sequence container with attributes. T is
 * strongly typed.
 * 
 * @see DNA.Sequence
 */
import bio._
import bio.attribute._

package bio {

  abstract class Sequence[T](seqlist: List[T], attributelist: List[Attribute]) extends AttributeAccess {
    type SequenceType <: Sequence[T]  // Abstract type 

    lazy val seq = seqlist
    lazy val attributes  = attributelist

    /** 
     * Every derived class should have a factory, so methods like 'delete', 
     * defined in the abstract class, work in a type safe way.
     */
    def create(seqlist: List[T], attributelist: List[Attribute]): SequenceType

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
    /** Delete part of the sequence */
    def delete(pos: Int, num: Int) = {
        create(seq.take(pos) ::: seq.takeRight(seq.size-pos-num), attributes)
      }
    /**
     * @return first (primary) Description in attribute list
     */
    def description = { attribFirst(GetDescription, attributes) match {
          case None => "No description"
          case Some(s) => s
      }
    }

    /**
     * Add an attribute to Sequence and return a new Sequence object
     */
    def attrAdd(attribute: Attribute) = {
      val attrlist = attribute :: attributes 
      create(seq, attrlist)
    }
    /**
     * Add a list of attributes to Sequence and return a new Sequence object
     */
    def attrAdd(attributelist: List[Attribute]) = {
      val attrlist = attributes ::: attributelist
      create(seq, attrlist)
    }
  }

} // bio

