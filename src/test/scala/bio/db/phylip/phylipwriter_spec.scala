import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class PhylipWriterSpec extends FlatSpec with ShouldMatchers {
    import bio._
    import bio.DNA._
    import java.io._

    "PhylipWriter" should "read write Phylip file" in {
      val f = new FastaReader("./test/data/fasta/nt_aln.fa")
      val seqlist = f.map { res => 
        println(res)
        val (id,tag,dna) = res
        val seq = new GappedSequence(id,tag,dna)
        seq
        }.toList
      val tmpfn = File.createTempFile("BioScala",".phy")
      val writer = new PhylipWriter
      writer.writeFile(tmpfn,seqlist)
      seqlist.head.id should equal ("PITG_04081T0")
    }
  } // Spec class
} // bio.test
