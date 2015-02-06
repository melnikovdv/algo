package org.mlayer.sorting

object Bubble extends Sort {

  override def sort(list: List[Int]): List[Int] = {
    val a = list.toBuffer
    val len = a.length
    for (i <- 0 to len - 1) {
      for (j <- 0 to len - 2) {
        if (a(j) > a(j + 1)) {
          val tmp = a(j)
          a(j) = a(j + 1)
          a(j + 1) = tmp
        }
      }
    }
    a.toList
  }

}
