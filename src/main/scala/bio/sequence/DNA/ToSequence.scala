package bio.sequence.DNA

object ToSequence {
  def apply(str: String): IUPACSequence = IUPACSequence(ToDNA(str))
}
