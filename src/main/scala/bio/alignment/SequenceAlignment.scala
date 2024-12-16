package bio.alignment

/**
 * Alignment container for Sequences. Each sequence has an
 * ID that can not be lost. The reason for this class are
 * the functions that update columns. (NYI)
 */
class SequenceAlignment[Sequence](sequenceList: List[Sequence]) {
  val list: List[Sequence] = sequenceList

  type Aln = List[Sequence]

  def toList: Aln = list
}
