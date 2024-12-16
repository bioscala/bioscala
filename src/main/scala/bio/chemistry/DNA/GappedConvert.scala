package bio.chemistry.DNA

import bio.nucleotide.DNA.{NTSymbol, SymbolConvert}
import bio.chemistry.GappedConvert

object GappedConvert extends GappedConvert[NTSymbol](Gap, SymbolConvert)
