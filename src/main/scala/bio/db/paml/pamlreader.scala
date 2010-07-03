/**
 * PamlReader writes a list of sequences to a file in Paml format.
 *
 */

import java.io._
import org.biojava.bio.alignment._
import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import org.biojavax.bio.phylo.io.phylip._


package bio {

  object PamlReader {
    class PamlReaderException(string: String) extends Exception(string)
  } // PamlReader

} // bio

