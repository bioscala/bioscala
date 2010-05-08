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

  abstract class Attribute {
    // def send(msg: Tuple2[Message,Unit]): Tuple2[Message,Unit]
  }

  abstract class Message

  package attribute {

    // ====Messages
    case object Id extends Message
    case object Ok extends Message

    // ==== Attributes
    class Id(str: String) extends Attribute {
      lazy val id = str

      def send(msg: Message): Tuple2[Message,Boolean] = {
       val ret = (Ok,true)
       ret
      }
    }
  }
}


