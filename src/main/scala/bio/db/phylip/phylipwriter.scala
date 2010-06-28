/**
 * PhylipWriter writes a file in PHYLIP format. It is defined as a singleton
 * object, so you don't need to instantiate it.
 */

import java.io._
import org.biojava.bio.alignment._
import org.biojava.bio.symbol._

package bio {

  object PhylipWriter {
    def writeFile(file: File, list: List[Any]) : Unit = {
      println(file)
      val refelement = new SimpleAlignmentElement("reference", list.head, new RangeLocation(1, 30))
      val aln = new FlexibleAlignment(List[refelement])

      val out = new FileWriter(file)
      out.write("hello file!")
      out.close
    }
  } // PhilipWriter
} // bio

