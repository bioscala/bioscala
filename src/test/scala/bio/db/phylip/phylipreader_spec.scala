import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class PhylipReaderSpec extends FlatSpec with ShouldMatchers {
    import bio._
    import bio.DNA._

    "PhylipReader" should "read Phylip file" in {
      val f = new PhylipReader("test/data/phylip/phylip-aln1.phy")
      val ids = f.map { res => 
        val (id,tag,dna) = res
        id
        }.toList
      ids.head should equal ("PITG_04081T0")
    }
  } // Spec class
} // bio.test
