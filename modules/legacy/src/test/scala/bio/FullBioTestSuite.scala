package bio

import bio.chemistry.{DNANucleotideSpec, RNANucleotideSpec}
import bio.sequence.{DNASequenceSpec, RNASequenceSpec}
import bio.sequence.actions.SequenceTranslateSpec
import org.scalatest.Suites

class FullBioTestSuite extends Suites(
    new DNANucleotideSpec,
    new RNANucleotideSpec,
    new DNASequenceSpec,
    new RNASequenceSpec,
    new SequenceTranslateSpec
)
