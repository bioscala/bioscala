/**
 * Sequence transcription. Delegated by the Sequence object. 
 * Note we take a functional approach (no reference to self).
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  trait SequenceTranscription {
    /** 
     * Transcribe DNA to RNA
     * @return an RNA::Sequence object
     */
    def transcribe(seq: Sequence): RNA.Sequence = {
      new RNA.Sequence(
        seq.nucleotides.map { nt => 
          nt match {
            case DNA.A => RNA.A
            case DNA.C => RNA.C
            case DNA.G => RNA.G
            case DNA.T => RNA.U
            case  _  => throw new IllegalArgumentException
          }
        }.toString
      )
    }
    def toRNA(seq: Sequence): RNA.Sequence = { transcribe(seq) }
  }

}
