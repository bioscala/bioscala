/**
 * Sequence translation is a trait.
 */

import bio._

package bio {

  trait SequenceTranslation {
    def translate = { toString }
  }

}
