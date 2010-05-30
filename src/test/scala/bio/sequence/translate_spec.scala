import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class SequenceTranslateSpec extends FlatSpec with ShouldMatchers {
    
    "Translate DNA sequence" should "translate to protein" in {
      new DNA.Sequence("agctaacga").translate.mkString should equal ("S*R")
    }

    "Translate RNA sequence" should "translate to protein" in {
      new RNA.Sequence("agcuaacga").translate.mkString should equal ("S*R")
    }
    "Translate ambiguous DNA" should "translate to AA with X" in {
      new DNA.IUPACSequence("agctaacgn").translate.mkString should equal ("S*R")
    }
    "Translate ambiguous RNA" should "translate to AA with X" in {
      new RNA.IUPACSequence("ancuaacgn").translate.mkString should equal ("X*R")
    }
    "Translate ambiguous DNA sequences from nt.fa" should "succeed" in {
      val f = new FastaReader("./test/data/fasta/nt.fa")
      val seqs = f.map { res => 
        val (id,tag,dna) = res
        // println(dna)
        new DNA.IUPACSequence(id,tag,dna).translate
        }.toList
      // println(seqs)
      seqs.head.take(14).mkString should equal ("RFXRSSXXVLXIVI")
    }

  }    
}
