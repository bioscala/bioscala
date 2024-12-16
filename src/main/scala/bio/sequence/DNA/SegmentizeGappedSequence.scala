package bio.sequence.DNA

import bio.chemistry.DNA.Gap
import bio.nucleotide.DNA.NTSymbol
import bio.sequence.actions.{SegmentizeGappedSequence => SGS}

object SegmentizeGappedSequence extends SGS[NTSymbol](Gap)
