
import org.scalatest.SuperSuite
import bio.test._

// import bio.test.DNANucleotideSpec
// import bio.test.RNANucleotideSpec
// import bio.test.DNASequenceSpec
// import bio.test.RNASequenceSpec
//

package bio.test {

class FullBioTestSuite extends SuperSuite(
  List(
    new DNANucleotideSpec,
    new RNANucleotideSpec,
    new DNASequenceSpec,
    new RNASequenceSpec
  )
)

}


