package bio.chemistry.RNA

import bio.nucleotide.RNA.NTSymbol
import bio.chemistry.GappedConvert

object IUPACGappedConvert extends GappedConvert[NTSymbol](Gap, IUPACNucleotideConvert)
