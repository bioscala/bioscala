import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class PhylipWriterSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._

    "PhylipWriter" should "read write Phylip file" in {
      val f = new FastaReader("./test/data/fasta/nt_aln.fa")
      val ids = f.map { res => 
        println(res)
        val (id,tag,dna) = res
        val seq = new GappedSequence(id,tag,dna)
        seq
        }.toList
      ids.head.id should equal ("PITG_04081T0")
    }
  } // Spec class
} // bio.test
