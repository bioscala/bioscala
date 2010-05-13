/**
 * A gapped Sequence represents a Sequence with stretches of 'emptiness', like
 * used in an alignment.  GappedSequence contains a list of mixed Nucleotides +
 * Gaps.  <p>
 * When a part of a Sequence has unknown nucleotides/amino acids use
 * DegenerateSequence.
 *
 * @see DegenerateSequence
 */

import bio._
import bio.attribute._

package bio {

  class GappedSequence (seqlist: List[Symbol], attributelist: List[Attribute]) extends bio.Sequence[Symbol](seqlist,attributelist) {
  }

  package DNA {
    class GappedSequence (seqlist: List[Symbol], attributelist: List[Attribute]) extends bio.GappedSequence(seqlist,attributelist) {
      def this(str: String) = this(GappedNucleotideConvert.fromString(str),Nil)
    }
  }
}

