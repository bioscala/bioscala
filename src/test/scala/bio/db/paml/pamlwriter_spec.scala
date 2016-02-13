import bio._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Matchers

package bio.test {

  class PamlWriterSpec extends FlatSpec with Matchers {
    import bio._
    import bio.DNA._
    import java.io._

    "PamlWriter" should "write PAML file" in {
      val f = new FastaReader("./test/data/fasta/nt_aln.fa")
      val seqlist = f.map { res =>
        val (id,tag,dna) = res
        val seq = new GappedSequence(id,tag,dna)
        seq
        }.toList
      val tmpfn = File.createTempFile("BioScala-PAML-",".phy")
      import bio.io.Control._
      using(new FileOutputStream(tmpfn)) { stream =>
        // 2x write
        new PamlWriter(stream).write(seqlist)
        new PamlWriter(stream).write(seqlist)
      }
      seqlist.head.id should equal ("PITG_04081T0")
    }
  } // Spec class
} // bio.test
