package bio.sequence.DNA

import bio.chemistry.DNA.IUPACGappedConvert
import bio.nucleotide.DNA.{NTSymbol, T}

/**
 * Take a DNA or RNA string and convert it to a DNA nucleotide list -
 * allowing for ambiguous codes (IUPAC) and gaps
 */
object ToGappedDNA {
  def apply(str: String): List[NTSymbol] = {
    str.toList map { c =>
      c.toLower match {
        case 'u' => T
        case _ => IUPACGappedConvert.fromChar(c)
      }
    }
  }
}
