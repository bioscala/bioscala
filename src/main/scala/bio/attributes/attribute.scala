/**
 * Attributes are properties of Nucleotides, Sequences, Alignments etc.
 * For a Sequence there maybe a ID attributes, descriptions, features,
 * annotations, etc.
 *
 * Each attribute implements listeners, to messages they respond to. A
 * message may be to return the primary ID, descriptions, or output to
 * RDF, for example.
 *
 * A message is simply a Tuple of the message type, and parameters. The
 * return value is also a Tuple with status and object(s).
 */
package bio

import scala.language.postfixOps

abstract class Message

abstract class StatusMessage

/** Base class for all attributes */
abstract class Attribute {
  def send(msg: Message): (StatusMessage, Any)
}

package attribute {

  // ==== Message
  case object GetId extends Message

  case object GetDescription extends Message

  case object GetXML extends Message

  case object GetCodon extends Message

  case object GetDNA extends Message // NYI

  case object GetFeature extends Message // NYI

  case object GetGap extends Message // NYI

  case object GetRDF extends Message // NYI

  // ==== StatusMessage
  case object Ok extends StatusMessage

  case object UnknownMessage extends StatusMessage

  case object Error extends StatusMessage

  // ==== Attributes

  /** StringAttribute stores a typical String value */
  class StringAttribute(str: String, respondTo: Message) extends Attribute {
    lazy val data = str
    lazy val respondMsg = respondTo

    override def toString = data

    def toXML = {
      val name = (getClass getName).split('.').last
      "<" + name + ">" + data + "</" + name + ">"
    }

    override def send(msg: Message): (StatusMessage, String) = {
      msg match {
        case `respondMsg` => (Ok, data)
        case GetXML => (Ok, toXML)
        case _ => (UnknownMessage, msg.getClass.getName)
      }
    }
  }

  /** Id responds to the GetId message */
  case class Id(str: String) extends StringAttribute(str, GetId)

  /** Description responds to the GetDescription message */
  case class Description(str: String) extends StringAttribute(str, GetDescription)

  /** Codon responds to the GetCodon message */
  case class Codon(seq: List[DNA.NTSymbol]) extends Attribute {

    override def toString: String = seq.mkString

    override def send(msg: Message): (StatusMessage, List[DNA.NTSymbol]) = {
      msg match {
        case `GetCodon` => (Ok, seq)
        case _ => (UnknownMessage, seq)
      }
    }
  }
}
