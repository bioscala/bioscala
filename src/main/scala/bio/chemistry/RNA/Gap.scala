package bio.chemistry.RNA

import bio.nucleotide.RNA.NTSymbol

sealed abstract class Gap extends NTSymbol

case object Gap extends Gap {
  override def toString = "-"
}
