/*
 * The Nucleotide sequence class represents a list of DNA or RNA 
 * nucleotides. Each sequence can carry a list of attributes, 
 * including an id and description.
 */

package bio {

  package DNA {
    class Sequence(seq: String) {
      val nucleotides = seq
      override def toString() = nucleotides
    }
  }

  // package RNA {
  //   class Sequence extends List[Nucleotide]
  // }

}
