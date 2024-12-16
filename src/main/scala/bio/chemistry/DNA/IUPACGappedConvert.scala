package bio.chemistry.DNA

import bio.nucleotide.DNA.NTSymbol
import bio.chemistry.GappedConvert

object IUPACGappedConvert extends GappedConvert[NTSymbol](Gap, IUPACNucleotideConvert)
