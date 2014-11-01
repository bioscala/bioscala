
package bio
  package attribute {

    /**
     * Access functions for the attribute list
     */
    trait AttributeAccess {

      /**
       * @return list of attributes matching message
       */
      def attribList(message: Message, attributes: List[Attribute]) : List[Attribute] = {
        attributes.filter { a =>
          a.send(message) match {
            case (Ok,_) => true
            case _ => false
          }
        }
      }
      /**
       * @return the values of messages as a list
       */
      def attribValues(message: Message, attributes: List[Attribute]) : List[Any] = {
        attribList(message, attributes) map { a =>
          val (Ok, value) = a.send(message)
          value
        }
      }
      /**
       * @return the first attribute value matching message
       */
      def attribFirst(message: Message, attributes: List[Attribute]) : Option[Any] = {
        val list: List[Attribute] = attribList(message,attributes)
        if (list.size > 0) {
          val (Ok, msg) = list.head.send(message)
          Some(msg)
        } else None
      }
    }

  }


