/*
 * The Nucleotide sequence class represents a list of DNA or RNA 
 * nucleotides. Each sequence can carry a list of attributes, 
 * including an id and description.
 */

import bio._

package bio {

  abstract class Sequence {
  }

  package DNA {
    class Sequence(str: String) extends bio.Sequence {
      val nucleotides = str.toList.map { nt => NucleotideConvert.fromChar(nt) }
      override def toString() = { nucleotides mkString }
    }
  }

  package RNA {
    class Sequence(str: String) extends bio.Sequence {
      val nucleotides = str.toList.map { nt => NucleotideConvert.fromChar(nt) }
      override def toString() = { nucleotides mkString }
    }
    // class RNASequence(str: String) extends Sequence(str) {
    //   val nucleotides = str.toList.map { nt => NucleotideConvert.fromChar(nt) }
    //   override def toString() = { nucleotides mkString }
    // }
  }
}
