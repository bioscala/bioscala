/**
 * FastaReader iterates over a FASTA file, returning Sequence objects, without
 * storing the whole database in RAM (unlike many other implementations).
 */

import java.io._

package bio {

  class FastaReader[T](filename: String) {
    val f = new FileWriter(filename)

    def foreach = {}
 
  }

}

