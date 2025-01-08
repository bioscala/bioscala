package bio.sequence.DNA

import bio.DNA.{NTSymbol, T}
import bio.DNA.IUPACNucleotideConvert

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
