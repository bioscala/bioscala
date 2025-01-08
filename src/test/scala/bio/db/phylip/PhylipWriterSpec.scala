package bio.db.phylip

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.{FastaReader, PhylipWriter}
import bio.sequence.DNA.GappedSequence

import java.io._

class PhylipWriterSpec extends AnyFlatSpec with Matchers {
  "PhylipWriter" should "write Phylip file" in {
    val f = new FastaReader("./test/data/fasta/nt_aln.fa")
    val seqlist = f.map { res =>
      val (id, tag, dna) = res
      val seq = GappedSequence(id, tag, dna)
      seq
    }.toList
    val tmpfn = File.createTempFile("BioScala-", ".phy")
    PhylipWriter.writeFile(tmpfn, seqlist)
    seqlist.head.id should equal("PITG_04081T0")
  }
}
