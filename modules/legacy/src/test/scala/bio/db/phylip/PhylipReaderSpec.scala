package bio.db.phylip

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio._

class PhylipReaderSpec extends AnyFlatSpec with Matchers {
    "PhylipReader" should "read Phylip file" in {
      val f = new PhylipReader("modules/legacy/src/test/resources/phylip/phylip-aln1.phy")
      val ids = f.map { res =>
        val (id, _) = res
        id
        }.toList
      ids.head should equal ("PITG_04081")
    }
  }
