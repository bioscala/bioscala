import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class FastaReaderSpec extends FlatSpec with ShouldMatchers {
    "FastaReader" should "read from file" in {
      val f = new FastaReader("./test/data/fasta/nt.fa")
      val ids = f.map { res => 
        val (id,tag,dna) = res
        // println(id,tag)
        // println(dna) 
        id
        }.toList

      ids.head should equal ("PUT-157a-Arabidopsis_thaliana-1")
    }
  }
}
