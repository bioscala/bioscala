/*
 * Nucleotides (abreviated nt) are molecules that, when joined together, make
 * up the structural units of RNA and DNA. In addition, nucleotides play
 * central roles in metabolism.
 *
 * The purines are adenine (A) and guanine (G).
 * The (complementary) pyrimidines are thymine (T) and cytosine (C).
 * In RNA uracil (U) is the complementary pyrimidine of adenine.
 *
 */

package bio

abstract class Nucleotide
abstract class Purine extends Nucleotide
abstract class Pyrimidine extends Nucleotide

package DNA {
    object A extends Purine {
      override def toString() = "a"
    }
    object G extends Purine {
      override def toString() = "g"
    }
    object T extends Pyrimidine {
      override def toString() = "t"
    }
    object C extends Pyrimidine {
      override def toString() = "c"
    }
}
package RNA {
    object A extends Purine {
      override def toString() = "a"
    }
    object G extends Purine {
      override def toString() = "g"
    }
    object U extends Pyrimidine {
      override def toString() = "u"
    }
    object C extends Pyrimidine {
      override def toString() = "c"
    }
}


// vim: set ts=4 sw=4 et:
