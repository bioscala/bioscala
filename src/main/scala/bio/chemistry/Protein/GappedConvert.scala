package bio.chemistry.Protein

import bio.chemistry.GappedConvert

object GappedConvert extends GappedConvert[AASymbol](Gap, SymbolConvert)
