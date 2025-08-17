package bio.db.phylip

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.DNA._
import bio._

import java.io._

class PhylipWriterSpec extends AnyFlatSpec with Matchers {
  "PhylipWriter" should "write Phylip file" in {
    val f = new FastaReader("modules/legacy/src/test/resources/fasta/nt_aln.fa")
    val seqlist = f.map { res =>
      val (id, tag, dna) = res
      val seq = new GappedSequence(id, tag, dna)
      seq
    }.toList
    val tmpfn = File.createTempFile("BioScala-", ".phy")
    PhylipWriter.writeFile(tmpfn, seqlist)
    seqlist.head.id should equal("PITG_04081T0")
  }
}
