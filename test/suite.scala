
import org.scalatest.SuperSuite
import bio.test._

package bio.test {

class FullBioTestSuite extends SuperSuite(
  List(
    new DNANucleotideSpec,
    new RNANucleotideSpec,
    new DNASequenceSpec,
    new RNASequenceSpec,
    new BioRubySpec
  )
)

}


