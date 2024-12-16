package bio.attribute

/** Description responds to the GetDescription message */
case class Description(str: String) extends StringAttribute(str, GetDescription)
