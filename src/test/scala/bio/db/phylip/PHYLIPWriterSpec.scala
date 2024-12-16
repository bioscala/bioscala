package bio.db.phylip

import bio.db.fasta.FastaReader
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.sequence.DNA.GappedSequence

import java.io._

class PHYLIPWriterSpec extends AnyFlatSpec with Matchers {
  "PHYLIPWriter" should "write PHYLIP file" in {
    val f = new FastaReader("src/test/resources/fasta/nt_aln.fa")
    val seqList = f.map { res =>
      val (id, tag, dna) = res
      val seq = GappedSequence(id, tag, dna)
      seq
    }.toList
    val tmpFile = File.createTempFile("BioScala-", ".phy")
    PHYLIPWriter(tmpFile).write(seqList)
    seqList.head.id should equal("PITG_04081T0")
  }
}
