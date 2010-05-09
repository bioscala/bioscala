/**
 * Attributes are properties of Nucleotides, Sequences, Alignments etc.
 * For a Sequence there maybe a ID attributes, descriptions, features,
 * annotations, etc.
 *
 * Each attribute implements listeners, to messages they respond to. A
 * message may be to return the primary ID, descriptions, or output to
 * RDF, for example.
 *
 * A message is simply a Tuple of the message type, and paramaters. The
 * return value is also a Tuple with status and object(s).
 */

package bio {

  abstract class Message
  abstract class StatusMessage

  abstract class Attribute {
    def send(msg: Message): Tuple2[StatusMessage,Any]
  }

  package attribute {

    // ==== Message
    case object GetId extends Message
    case object GetDescription extends Message
    case object GetXML extends Message 
    case object GetFeature extends Message // NYI
    case object GetGap extends Message // NYI
    case object GetRDF extends Message // NYI
    // ==== StatusMessage
    case object Ok extends StatusMessage
    case object UnknownMessage extends StatusMessage
    case object Error extends StatusMessage

    // ==== Attributes
    class StringAttribute(str: String, respondTo: Message) extends Attribute {
      lazy val data = str
      lazy val respondMsg = respondTo

      def toXML = {
        val name = (getClass getName).split('.').last
        "<"+name+">"+data+"</"+name+">"
      }

      override def send(msg: Message): Tuple2[StatusMessage,String] = {
        // if (msg == respondMsg) { (Ok,data) }
        // else { (UnknownMessage,"")}
        msg match {
          case `respondMsg` => (Ok, data)
          case GetXML   => (Ok, toXML)
          case _ => (UnknownMessage, msg.getClass.getName)
        }

          // case getDescription => (Ok,data)
          // case _ => (UnknownMessage,"")
      }
    }
    class Id(str: String) extends StringAttribute(str,GetId)
    class Description(str: String) extends StringAttribute(str,GetDescription)
  }
}


