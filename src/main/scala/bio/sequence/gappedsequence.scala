/**
 * A gapped Sequence represents a Sequence with stretches of 'emptiness', like
 * used in an alignment.  GappedSequence contains a list of mixed Nucleotides +
 * Gaps.  
 * <p>
 * When a part of a Sequence has unknown nucleotides/amino acids use
 * IUPACSequence.
 */

import bio._
import bio.attribute._

package bio {

  package DNA {
    class GappedSequence (seqlist: List[NTSymbol], attributelist: List[Attribute]) extends bio.Sequence[NTSymbol](seqlist,attributelist) {

      type SequenceType = GappedSequence
      def create(seqlist: List[NTSymbol], attributelist: List[Attribute]) = new GappedSequence(seqlist, attributelist)

      def this(list: List[NTSymbol]) = this(GappedConvert.fromList(list),Nil)
      def this(str: String) = this(GappedConvert.fromString(str),Nil)
      def this(id: String, str: String) = this(GappedConvert.fromString(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(GappedConvert.fromString(str),List(Id(id),Description(descr)))
      def this(sequence: Sequence) = this(sequence.seq, Nil)

    }
  }
  package Protein {
    class GappedSequence (seqlist: List[AASymbol], attributelist: List[Attribute]) extends bio.Sequence[AASymbol](seqlist,attributelist) {

      type SequenceType = GappedSequence
      def create(seqlist: List[AASymbol], attributelist: List[Attribute]) = new GappedSequence(seqlist, attributelist)

      def this(list: List[AASymbol]) = this(GappedConvert.fromList(list),Nil)
      def this(str: String) = this(GappedConvert.fromString(str),Nil)
      def this(id: String, str: String) = this(GappedConvert.fromString(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(GappedConvert.fromString(str),List(Id(id),Description(descr)))
      def this(sequence: Sequence) = this(sequence.seq, Nil)
    }
  }
}

