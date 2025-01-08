package bio.sequence.DNA

object ToGappedSequence {
  def apply(str: String): IUPACGappedSequence = IUPACGappedSequence(ToGappedDNA(str))
}
