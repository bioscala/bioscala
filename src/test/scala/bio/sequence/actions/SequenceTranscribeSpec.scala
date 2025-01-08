package bio.sequence.actions


import bio.sequence.DNA.DNASequence
import bio.sequence.RNA.RNASequence
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SequenceTranscribeSpec extends AnyFlatSpec with Matchers {

  "Transcribe DNA sequence" should "become RNA" in {
    DNASequence("agctaacga").transcribe.toString should equal(RNASequence("agcuaacga").toString)
  }

  "Transcribe RNA sequence" should "do nothing" in {
    RNASequence("agcuaacga").transcribe.toString should equal(RNASequence("agcuaacga").toString)
  }

  "Complement DNA sequence" should "give complement" in {
    DNASequence("agct").complement.mkString should equal("tcga")
  }

  "Complement RNA sequence" should "give complement" in {
    DNASequence("agct").complement.mkString should equal("tcga")
  }
}

