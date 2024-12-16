package bio.db.paml

import bio.sequence.Protein
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PamlReaderSpec extends AnyFlatSpec with Matchers {
  "PamlReader" should "read PAML Phylip file" in {
    val pamlreader = new PamlReader("src/test/resources/paml/paml-aln1.phy")
    val (id, _) = pamlreader.next()
    id should equal("PITG_04081T0")
  }

  "PamlReader" should "read PAML Phylip file2" in {
    val pamlreader = new PamlReader("src/test/resources/paml/paml-aln2.phy")
    val (id1, _) = pamlreader.next()
    val (id2, _) = pamlreader.next()
    (id1, id2) should equal("PITG_04081", "PITG_18670")
  }

  "PamlReader" should "read PAML Phylip file3 and turn it into a large sequence" in {
    val list = new PamlReader("src/test/resources/paml/paml-aln3.phy").toList
    val seqList = list.map { case (id, seq) =>
      Protein.GappedCodonSequence(id, seq)
    }
    val seq = seqList.head
    seq.id should equal("PITG_08167T0")
  }
}
