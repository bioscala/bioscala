/*
 * The Nucleotide sequence class represents a list of DNA or RNA 
 * nucleotides. Each sequence can carry a list of attributes, 
 * including an id and description.
 */

import bio._

package bio {

  abstract class Sequence(fromChar: (Char) => Nucleotide,str: String) {
    val nucleotides = str.toList.map { c => fromChar(c) }
    override def toString() = { nucleotides mkString }
  }

  package DNA {
    class Sequence(str: String) extends bio.Sequence(NucleotideConvert.fromChar,str) 
  }

  package RNA {
    class Sequence(str: String) extends bio.Sequence(NucleotideConvert.fromChar,str)
  }
}
