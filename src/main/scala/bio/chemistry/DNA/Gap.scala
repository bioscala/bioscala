package bio.chemistry.DNA

import bio.nucleotide.DNA.NTSymbol

abstract class Gap private[DNA] extends NTSymbol

case object Gap extends Gap {
  override def toString = "-"
}
