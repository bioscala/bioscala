import bio.test._

package bio.test {

import org.scalatest.Suites

/*
 * class ReducedBioTestSuite extends SuperSuite(
  List(
    new DNASequenceSpec,
    new RNASequenceSpec,
    new SequenceTranslateSpec
  )
)
 */

class FullBioTestSuite extends Suites(

    new DNANucleotideSpec,
    new RNANucleotideSpec,
    new DNASequenceSpec,
    new RNASequenceSpec,
    // new BioRubySpec,
    new SequenceTranslateSpec

)

}


