package bio.alignment.DNA

import bio.nucleotide.DNA.NTSymbol
import bio.alignment.SparseAlignment
import bio.chemistry.DNA.Gap

object SparseAlignment extends SparseAlignment[NTSymbol] {
  type GapType = Gap
  val gap: Gap = Gap
}
