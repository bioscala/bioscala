

import org.scalatest.SuperSuite
import bio.test._

package bio.test {

/*
 * class ReducedBioTestSuite extends SuperSuite(
  List(
    new DNASequenceSpec,
    new RNASequenceSpec,
    new SequenceTranslateSpec
  )
)
 */

class FullBioTestSuite extends SuperSuite(
  List(
    new DNANucleotideSpec,
    new RNANucleotideSpec,
    new DNASequenceSpec,
    new RNASequenceSpec,
    new BioRubySpec,
    new SequenceTranslateSpec
  )
)

}


