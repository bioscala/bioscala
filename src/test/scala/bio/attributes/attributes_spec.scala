import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class AttributesSpec extends FlatSpec with ShouldMatchers {

    import bio.attribute._

    "ID Attribute" should "instantiate from String" in {
      val id = new attribute.Id("testid")
      id.data should equal ("testid")
    }
    "ID Attribute" should "respond to send getId" in {
      val id = new Id("testid")
      val msg = id.send(getId) 
      msg should equal (Ok,"testid")
    }
    "ID Attribute" should "not respond to send getDescription" in {
      val id = new Id("testid")
      val msg = id.send(getDescription) 
      msg should equal (UnknownMessage,"")
    }
    "Description Attribute" should "respond to getDescription" in {
      val descr = new Description("Description")
      val msg = descr.send(getDescription) 
      msg should equal (Ok,"Description")
    }
  }
}
