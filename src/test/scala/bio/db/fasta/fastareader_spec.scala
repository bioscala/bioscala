import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class FastaReaderSpec extends FlatSpec with ShouldMatchers {
    "FastaReader" should "reads from file" in {
      f = new FastaReader[DNA.Nucleotide]("../../../../../test/data/nt.fa")
      @@
    }
  }
}
