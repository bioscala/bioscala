package bio.sequence.DNA

import bio.DNA.Gap
import bio.DNA.NTSymbol
import bio.sequence.actions.{SegmentizeGappedSequence => SGS}

object SegmentizeGappedSequence extends SGS[NTSymbol](Gap)
