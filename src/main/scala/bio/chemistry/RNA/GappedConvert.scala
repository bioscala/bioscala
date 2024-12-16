package bio.chemistry.RNA

import bio.nucleotide.RNA.{NTSymbol, SymbolConvert}
import bio.chemistry.GappedConvert

object GappedConvert extends GappedConvert[NTSymbol](Gap, SymbolConvert)
