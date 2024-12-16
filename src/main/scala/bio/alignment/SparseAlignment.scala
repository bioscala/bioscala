package bio.alignment

/**
 * Trait contains methods for modifying sparse alignments
 */
trait SparseAlignment[T] {
  type GapType // Abstract type
  val gap: GapType

  type Alignment = List[List[T]] // where T is a bio.nucleotide.Symbol

  def transpose(m: Alignment): Alignment = {
    if (m == Nil) Nil
    else if (m.head.isEmpty) Nil
    else m.map(_.head) :: transpose(m.map(_.tail))
  }

  /**
   * Remove sparse rows, i.e. rows that contain less than minSymbols
   */
  def removeSparseRows(in: Alignment, minSymbols: Int): (Alignment, List[Int]) = {
    // add an index to the list
    val indexed = in.zipWithIndex
    // filter on symbols
    val filtered = indexed filter {
      case (list, _) => list.count(_ != gap) >= minSymbols
    }
    // and put it all back together again
    val (ret, idx) = filtered.unzip
    val origIdx = in.indices.toList
    (ret, origIdx.diff(idx))
  }

  /**
   * Remove columns, i.e. columns that contain less than minSymbols
   */
  def removeSparseColumns(in: Alignment, min_symbols: Int): (Alignment, List[Int]) = {
    // transpose matrix for optimal list performance
    val (tm, log) = removeSparseRows(transpose(in), min_symbols)
    (transpose(tm), log)
  }
}
