package bio.attribute

/** StringAttribute stores a typical String value */
class StringAttribute(str: String, respondTo: Message) extends Attribute {
  lazy val data: String = str
  lazy val respondMsg: Message = respondTo

  override def toString: String = data

  def toXML: String = {
    val name = getClass.getName.split('.').last
    "<" + name + ">" + data + "</" + name + ">"
  }

  override def send(msg: Message): (StatusMessage, String) = {
    msg match {
      case `respondMsg` => (Ok, data)
      case GetXML => (Ok, toXML)
      case _      => (UnknownMessage, msg.getClass.getName)
    }
  }
}
