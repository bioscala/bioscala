package bio.db.phylip

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PHYLIPReaderSpec extends AnyFlatSpec with Matchers {
  "PHYLIPReader" should "read PHYLIP file" in {
    val file = new PHYLIPReader("src/test/resources/phylip/phylip-aln1.phy")
    val ids = file.map(_._1).toList
    ids.head should equal("PITG_04081")
  }
}
