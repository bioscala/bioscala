package bio.attribute

abstract class Message

case object GetId extends Message

case object GetDescription extends Message

case object GetXML extends Message

case object GetCodon extends Message

case object GetDNA extends Message

case object GetFeature extends Message

case object GetGap extends Message

case object GetRDF extends Message
