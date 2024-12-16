package bio.chemistry.Protein

import bio.chemistry.GappedConvert

object IUPACGappedConvert extends GappedConvert[AASymbol](Gap, IUPACGappedAminoAcidConvert)
