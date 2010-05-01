/*
 * The Nucleotide sequence class represents a list of DNA or RNA 
 * nucleotides. Each sequence can carry a list of attributes, 
 * including an id and description.
 */

import bio._

package bio {

  package DNA {

    object NucleotideConvert {
      def fromChar(c: Char): Nucleotide = { 
        c.toLowerCase match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 't' => T
          case  _  => throw new IllegalArgumentException
        }
      }
    }

    class Sequence(str: String) {
      val a = A
      val nucleotides = str.toList.map { nt => NucleotideConvert.fromChar(nt) }
      override def toString() = { nucleotides mkString }
      def mkString() = { toString() }
    }
  }

  // package RNA {
  //   class Sequence extends List[Nucleotide]
  // }

}
