package bio.attribute

import bio.nucleotide.DNA

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
