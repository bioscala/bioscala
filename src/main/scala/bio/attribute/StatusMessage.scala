package bio.attribute

abstract class StatusMessage

case object Ok extends StatusMessage

case object UnknownMessage extends StatusMessage

case object Error extends StatusMessage
