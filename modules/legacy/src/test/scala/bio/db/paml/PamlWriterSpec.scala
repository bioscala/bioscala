package bio.db.paml

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.DNA._
import bio._

import java.io._

class PamlWriterSpec extends AnyFlatSpec with Matchers {

  "PamlWriter" should "write PAML file" in {
    val f = new FastaReader("modules/legacy/src/test/resources/fasta/nt_aln.fa")
    val seqlist = f.map { res =>
      val (id, tag, dna) = res
      val seq = new GappedSequence(id, tag, dna)
      seq
    }.toList
    val tmpfn = File.createTempFile("BioScala-PAML-", ".phy")
    import bio.io.Control._
    using(new FileOutputStream(tmpfn)) { stream =>
      // 2x write
      new PamlWriter(stream).write(seqlist)
      new PamlWriter(stream).write(seqlist)
    }
    seqlist.head.id should equal("PITG_04081T0")
  }
}
