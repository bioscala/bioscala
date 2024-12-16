package bio.attribute

/** Id responds to the GetId message */
case class Id(str: String) extends StringAttribute(str, GetId)
