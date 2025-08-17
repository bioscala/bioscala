/**
 * A gap represents a gap in a GappedSequence, as used in an alignment. Gaps
 * can contain state - e.g. quality - through the attributes list.
 */

package bio

class GappedConvert[T](Gap: T, base: StringConverter[T]) extends StringConverter[T] {
  /**
   * Create a Gap object from its character representation.
   */
  def fromChar(c: Char): T = {
    c.toLower match {
      case '-' => Gap
      case _ =>
        base.fromChar(c)
    }
  }

  def fromItem(i: T): T = {
    i match {
      case Gap => Gap
      case _ =>
        base.fromItem(i)
    }
  }
}

package DNA {

  sealed abstract class Gap extends NTSymbol

  case object Gap extends Gap {
    override def toString = "-"
  }

  object GappedConvert extends bio.GappedConvert[NTSymbol](Gap,
    SymbolConvert)

  object IUPACGappedConvert extends bio.GappedConvert[NTSymbol](Gap,
    IUPACNucleotideConvert)
}

package RNA {
  sealed abstract class Gap extends NTSymbol

  case object Gap extends Gap {
    override def toString = "-"
  }

  object GappedConvert extends bio.GappedConvert[NTSymbol](Gap,
    SymbolConvert)

  object IUPACGappedConvert extends bio.GappedConvert[NTSymbol](Gap,
    IUPACNucleotideConvert)
}

package Protein {
  sealed abstract class Gap extends AASymbol

  case object Gap extends Gap {
    override def toString = "-"
  }

  object GappedConvert extends bio.GappedConvert[AASymbol](Gap,
    SymbolConvert)

  object IUPACGappedConvert extends bio.GappedConvert[AASymbol](Gap,
    IUPACGappedAminoAcidConvert)
}
