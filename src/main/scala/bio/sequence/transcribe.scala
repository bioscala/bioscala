/**
 * Sequence transcription. Delegated by the Sequence object.
 * Note we take a functional approach (no reference to self).
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  package DNA {

    object SequenceTranscription {
      /**
       * Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
       * is maintained
       */
      def toRNA(nucleotides: List[Symbol]): List[Symbol] = {
        nucleotides.map { nt =>
          nt match {
            case A => RNA.A
            case C => RNA.C
            case G => RNA.G
            case T => RNA.U
            // Ambiguous code:
            case M => RNA.M
            case R => RNA.R
            case W => RNA.W
            case S => RNA.S
            case Y => RNA.Y
            case K => RNA.K
            case V => RNA.V
            case H => RNA.H
            case D => RNA.D
            case B => RNA.B
            case N => RNA.N
            case  _  => throw new IllegalArgumentException("non DNA nucleotide "+nt+" type "+nt.getClass.getName)
          }
        }
      }
      /**
       * Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
       */
      // def transcribe(nucleotides: List[Nucleotide]): List[Nucleotide] = toRNA(nucleotides)
      def transcribe(nucleotides: List[Symbol]): List[Symbol] = toRNA(nucleotides)

      def complement(nucleotides: List[Nucleotide]) = {
        nucleotides.map { nt =>
          nt match {
              case A => T
              case T => A
              case C => G
              case G => C
              case  _  => throw new IllegalArgumentException("non DNA nucleotide "+nt+" type "+nt.getClass.getName)
          }
        }
      }
    }
  }
}
