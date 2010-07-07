package bio.io

object Control {
  def using[A <: {def close(): Unit}, B](param: A)(f: A => B): B = {
    try {
      f(param)
    }
    finally {
      param.close()
    }
  }
}
