/**
 * FastaReader iterates over a FASTA file, returning Sequence objects, without
 * storing the whole database in RAM (unlike many other implementations).
 */

import java.io._

package bio {

  class FastaReader(filename: String) extends Iterator[String] {
    private lazy val reader = new BufferedReader(new FileReader(filename))

    def hasNext() = reader.ready
    def next() = reader.readLine
    /*
    def foreach(f:String => Unit) = {
       try {
         while (reader.ready) {
           val s = reader.readLine
           return s
         }
       }
       finally reader.close
    }
    */
  }

}

