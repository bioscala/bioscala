package bio.chemistry.Protein

/** A gap represents a gap in a GappedSequence, as used in an alignment. Gaps
  * can contain state - e.g. quality - through the attribute list.
  */
sealed abstract class Gap extends AASymbol

case object Gap extends Gap {
  override def toString = "-"
}
