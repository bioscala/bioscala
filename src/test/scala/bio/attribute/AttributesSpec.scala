package bio.attribute

import bio._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.attribute._

class AttributesSpec extends AnyFlatSpec with Matchers {
  "ID Attribute" should "instantiate from String" in {
    val id = attribute.Id("testid")
    id.data should equal("testid")
  }

  "ID Attribute" should "respond to send getId" in {
    val id = Id("testid")
    val msg = id.send(GetId)
    msg should equal(Ok, "testid")
  }

  "ID Attribute" should "not respond to send getDescription" in {
    val id = Id("testid")
    val msg = id.send(GetDescription)
    msg should equal(UnknownMessage, "bio.attribute.GetDescription$")
  }

  "Description Attribute" should "respond to getDescription" in {
    val descr = Description("Description")
    val msg = descr.send(GetDescription)
    msg should equal(Ok, "Description")
  }

  "Description Attribute" should "respond to GetXML" in {
    val descr = Description("Description")
    val msg = descr.send(GetXML)
    msg should equal(Ok, "<Description>Description</Description>")
  }
}
