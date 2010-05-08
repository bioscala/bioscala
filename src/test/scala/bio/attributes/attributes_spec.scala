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
    "ID Attribute" should "respond to getid" in {
      val id = new Id("testid")
      val msg = id.send(Id) 
      msg should equal (Ok,true)
    }
  }
}
