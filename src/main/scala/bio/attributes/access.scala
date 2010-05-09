
package bio {
  package attribute {

    /**
     * Access functions for the attribute list
     */
    trait AttributeAccess {

      /**
       * @return list of attributes matching message
       */
      def attribList(message: Message, attributes: List[Attribute]) = {
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
      def attribValues(message: Message, attributes: List[Attribute]) = {
        attribList(message, attributes) map { a =>
          val (Ok, value) = a.send(message)
          value
        }
      }
      /**
       * @return the first attribute matching message
       */
      def attribFirst(message: Message, attributes: List[Attribute]) = {
        val (Ok, msg) = attribList(message,attributes).first.send(message)
        msg
      }
    }

  }
}

