package bio

/**
 * An Alignment has an (immutable) list of GappedSequences. A Sequence can
 * consist of nucleotides, amino acids and/or gaps. Each of these can have
 * attributes, sequences can have attributes and the Alignment can have
 * attributes.
 */

class Alignment[T](sequencelist: List[List[T]]) {
  val list = sequencelist
  // val attributes = attributelist

  type Aln = List[List[T]]
  // def this(list: Ali) = this(list,Nil)
  def toList: Aln = list

  def transpose(m: Aln): Aln = {
    if (m == Nil)
      Nil
    else if (m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
  }

  def getColumns(m: Aln) = transpose(m)
}

/**
 * Alignment container for Sequences. Each sequence has an
 * ID that can not be lost. The reason for this class are
 * the functions that update columns. (NYI)
 */
class SequenceAlignment[Sequence](sequencelist: List[Sequence]) {
  val list = sequencelist

  type Aln = List[Sequence]
  def toList: Aln = list

  // def blankColumnIf(f : (_) => _ ) = { f(_) }

}

package DNA {
  object SparseAlignment extends SparseAlignment[NTSymbol] {
    type GapType = Gap
    val gap = Gap
  }
} // DNA

/**
 * Trait contains methods for modifying sparse alignments
 */
abstract trait SparseAlignment[T] {
  type GapType // Abstract type
  val gap: GapType

  type Alignment = List[List[T]] // where T is a bio.Symbol

  def transpose(m: Alignment): Alignment = {
    if (m == Nil)
      Nil
    else if (m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
  }

  /**
   * Remove sparse rows, i.e. rows that contain less than min_symbols
   */
  def removeSparseRows(in: Alignment, min_symbols: Int): (Alignment, List[Int]) = {
    // add an index to the list
    val indexed = in.zipWithIndex
    // filter on symbols
    val filtered = indexed.filter {
      case (list, index) =>
        val numgaps = list.count { i => i == gap }
        val numsymbols = list.length - numgaps
        numsymbols >= min_symbols
    }
    // and put it all back together again
    val (ret, idx) = filtered.unzip
    val origidx = (0 to in.length - 1).toList
    (ret, origidx.diff(idx))
  }

  /**
   * Remove columns, i.e. columns that contain less than min_symbols
   */
  def removeSparseColumns(in: Alignment, min_symbols: Int): (Alignment, List[Int]) = {
    // transpose matrix for optimal list performance
    val (tm, log) = removeSparseRows(transpose(in), min_symbols)
    (transpose(tm), log)
  }

} // SparseAlignment
