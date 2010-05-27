import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class FastaReaderSpec extends FlatSpec with ShouldMatchers {
    "FastaReader" should "read from file" in {
      val f = new FastaReader("./test/data/fasta/nt.fa")
      val ids = f.map { res => 
        val (id,tag,dna) = res
        id
        }.toList
      ids.head should equal ("PUT-157a-Arabidopsis_thaliana-1")
    }
    "FastaReader" should "balk on nucleotide N with standard Sequence" in {
      val f = new FastaReader("./test/data/fasta/nt.fa")
      evaluating {
        val seqs = f.map { res => 
          val (id,tag,dna) = res
          new DNA.Sequence(id,tag,dna)
          }.toList
        true
      } should produce [IllegalArgumentException]
      true
    }
    "FastaReader" should "convert to IUPACSequence" in {
      val f = new FastaReader("./test/data/fasta/nt.fa")
      val seqs = f.map { res => 
        val (id,tag,dna) = res
        new DNA.IUPACSequence(id,tag,dna)
        }.toList
      seqs.head.id.toString should equal ("PUT-157a-Arabidopsis_thaliana-1")
    }
  }
}
