import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class PamlReaderSpec extends FlatSpec with ShouldMatchers {
    import bio._
    import bio.DNA._

    "PamlReader" should "read PAML Phylip file" in {
      false should equal ("PITG_04081T0")
    }
  } // Spec class
} // bio.test
