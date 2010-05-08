import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class AttributesSpec extends FlatSpec with ShouldMatchers {

    import bio.attribute._

    "ID Attribute" should "instantiate from String" in {
      val id = new attribute.Id("testid")
      id.id should equal ("testid")
    }
    "ID Attribute" should "respond to send Id" in {
      val id = new Id("testid")
      val msg = id.send(getId) 
      msg should equal (Ok,"testid")
    }
    "ID Attribute" should "not respond to send Description" in {
      val id = new Id("testid")
      val msg = id.send(getDescription) 
      msg should equal (Unknown,getDescription)
    }
  }
}
