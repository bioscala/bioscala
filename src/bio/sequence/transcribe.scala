/**
 * Sequence transcription. Delegated by the Sequence object. 
 * Note we take a functional approach (no reference to self).
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  object SequenceTranscription {
    /** 
     * Transcribe DNA to RNA
     */
    def transcribe(nucleotides: List[Nucleotide]): List[Nucleotide] = {
      nucleotides.map { nt => 
        nt match {
          case DNA.A => RNA.A
          case DNA.C => RNA.C
          case DNA.G => RNA.G
          case DNA.T => RNA.U
          case  _  => throw new IllegalArgumentException("non DNA nucleotide: "+nt)
        }
      }
    }
    def toRNA = { transcribe(_) }
  }

}
