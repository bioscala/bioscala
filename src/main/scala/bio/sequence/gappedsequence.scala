/**
 * A gapped Sequence represents 'emptiness', like used in an alignment. 
 * GappedSequence contains a list of mixed Nucleotides + Gaps.
 * <p>
 * When a part of a Sequence has unknown nucleotides/amino acids use
 * DegenerateSequence.
 *
 * @see DegenerateSequence
 */

import bio._
import bio.attribute._

package bio {

  class GappedSequence (nucleotidelist: List[Symbol], attributelist: List[Attribute]) extends AttributeAccess {
    lazy val nucleotides = nucleotidelist
    lazy val attributes  = attributelist
    // def this(str: String) = this(GappedConvert.fromString(str),Nil)
  }
}

