/**
 * PhylipReader writes a list of sequences to a file in Phylip format.
 *
 */

import java.io._
// import org.biojava.bio.alignment._
// import org.biojava.bio.symbol._
// import org.biojava.bio.seq._
import org.biojava.bio.seq.io._
import org.biojavax.bio.phylo.io.phylip._

package bio {

  /** 
   * PhylipReader opens a file and parses the Phylip contents using 
   * an iterator. This implementation is not ready - it may use the
   * BioJava PHYLIPFileListener as below, but one problem is that
   * ID names are restricted to 9 characters.
   */
  class PhylipReader(val filename: String) extends Iterator[Tuple3[String,String,String]] {
    private lazy val reader = new BufferedReader(new FileReader(filename))

    class PhylipReaderException(string: String) extends Exception(string)

    object PhylipListener extends PHYLIPFileListener {
      def receiveSequence(s : String) = { println(">>>>",s) }
      def setCurrentSequenceName(s : String) = { println("@@@@",s) }
      def setSitesCount(i: Int) = {}
      def setSequenceCount(i: Int) = {}
      def endFile() = {}
      def startFile() = {}
    }
    lazy val listener = PhylipListener
    val x = PHYLIPFileFormat.parse(listener,reader)


    def hasNext() = true

    def next(): Tuple3[String,String,String] = {
      ("","","")
    }
  } // PhylipReader

} // bio

