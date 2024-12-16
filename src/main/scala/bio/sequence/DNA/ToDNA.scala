package bio.sequence.DNA

import bio.nucleotide.DNA.{NTSymbol, T}
import bio.chemistry.DNA.IUPACNucleotideConvert

object ToDNA {
  def apply(str: String): List[NTSymbol] = {
    str.toList map { c =>
      c.toLower match {
        case 'u' => T
        case _   => IUPACNucleotideConvert.fromChar(c)
      }
    }
  }
}
