package bio.alignment

/**
 * An Alignment has an (immutable) list of GappedSequences. A Sequence can
 * consist of nucleotides, amino acids and/or gaps. Each of these can have
 * attribute, sequences can have attribute and the Alignment can have
 * attribute.
 */
class Alignment[T](sequenceList: List[List[T]]) {
  type Aln = List[List[T]]

  val list: List[List[T]] = sequenceList

  def toList: Aln = list

  def transpose(m: Aln): Aln = {
    if (m == Nil)
      Nil
    else if (m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
  }

  def getColumns(m: Aln): Aln = transpose(m)
}
