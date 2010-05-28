/**
 * The (immutable) Amino acid sequence class represents a list of Protiein
 * amino acids. Each sequence can carry a list of attributes, including the
 * ID(s) and description(s).
 * <p>
 * Contrasting with most Bio* implementations, the Sequence class represents
 * the contained amino acids as a List, not as a String. Also a Sequence may be
 * represented by multiple ID's and descriptions in the form of flexible
 * attributes.
 * <p>
 * As each amino acid is a real object, it may contain additional information.
 * <p>
 * For gapped and ambiguous sequences see the relevant traits.
 *
 * @see GappedSequence 
 * @see IUPACSequence
 */

import bio._
import bio.attribute._

package bio {

  package Protein {

    class Sequence(aalist: List[AminoAcid], attributelist: List[Attribute]) extends bio.Sequence[AminoAcid](aalist, attributelist) {
      def this(list: List[AminoAcid]) = this(AminoAcidConvert.fromList(list),Nil)
      def this(str: String) = this(AminoAcidConvert.fromString(str),Nil)
      def this(id: String, str: String) = this(AminoAcidConvert.fromString(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(AminoAcidConvert.fromString(str),List(Id(id),Description(descr)))
      def this(sequence: Sequence) = this(sequence.seq, Nil)

      def translate() = { SequenceTranslation.translate(
                            transcribe seq) }

      /**
       * Add an attribute to Sequence and return a new Sequence object
       */
      def attrAdd(attribute: Attribute) = {
        val attrlist = attribute :: attributes 
        new Sequence(seq, attrlist)
      }
      /**
       * Add a list of attributes to Sequence and return a new Sequence object
       */
      def attrAdd(attributelist: List[Attribute]) = {
        val attrlist = attributes ::: attributelist
        new Sequence(seq, attrlist)
      }
    } // Sequence

  } // Protein
} // bio
