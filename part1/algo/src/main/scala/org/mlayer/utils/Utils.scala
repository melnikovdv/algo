package org.mlayer.utils

object Utils {

  def loadData(file: String): List[String] = {
    val wordStream = Option {
      getClass.getClassLoader.getResourceAsStream(file)
    } getOrElse {
      sys.error("Could not load file " + file)
    }
    try {
      val s = io.Source.fromInputStream(wordStream)
      s.getLines().toList
    } catch {
      case e: Exception =>
        println("Could not load file: " + e)
        throw e
    } finally {
      wordStream.close()
    }
  }

}
