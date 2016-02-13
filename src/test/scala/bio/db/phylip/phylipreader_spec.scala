import bio._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Matchers

package bio.test {

  class PhylipReaderSpec extends FlatSpec with Matchers {
    import bio._
    import bio.DNA._

    "PhylipReader" should "read Phylip file" in {
      val f = new PhylipReader("test/data/phylip/phylip-aln1.phy")
      val ids = f.map { res =>
        val (id,dna) = res
        id
        }.toList
      ids.head should equal ("PITG_04081")
    }
  } // Spec class
} // bio.test
