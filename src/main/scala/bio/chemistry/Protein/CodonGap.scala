package bio.chemistry.Protein

import bio.nucleotide.DNA.NTSymbol
import bio.chemistry.{DNA, Protein}

private[Protein] abstract class CodonGap extends CodonSymbol

case object CodonGap extends CodonGap {
  val getAminoAcid: AASymbol = Protein.Gap
  val getCodon: List[NTSymbol] = List(DNA.Gap, DNA.Gap, DNA.Gap)

  override def toString = "-"
}
