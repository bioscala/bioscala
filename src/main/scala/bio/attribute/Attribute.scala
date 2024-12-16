package bio.attribute

/**
 * Attributes are properties of Nucleotides, Sequences, Alignments etc.
 * For a Sequence there maybe a ID attribute, descriptions, features,
 * annotations, etc.
 *
 * Each attribute implements listeners, to messages they respond to. A
 * message may be to return the primary ID, descriptions, or output to
 * RDF, for example.
 *
 * A message is simply a Tuple of the message type, and parameters. The
 * return value is also a Tuple with status and object(s).
 */
abstract class Attribute {
  def send(msg: Message): (StatusMessage, Any)
}
