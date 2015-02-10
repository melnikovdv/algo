package org.mlayer.sorting

object Quick extends Sort {

  override def sort(list: List[Int]): List[Int] = {
    val a: Array[Int] = list.toArray
    qsort(a, 0, a.length - 1, partitionCormen)
    a.toList
  }

  def sortPartFirst(list: List[Int]): (List[Int], Long) = {
    val a = list.toArray
    val count = qsort(a, 0, a.length - 1, partitionFirst)
    (a.toList, count)
  }

  def sortPartLast(list: List[Int]): (List[Int], Long) = {
    val a = list.toArray
    val count = qsort(a, 0, a.length - 1, partitionLast)
    (a.toList, count)
  }

  def sortPartMedian(list: List[Int]): (List[Int], Long) = {
    val a = list.toArray
    val count = qsort(a, 0, a.length - 1, partitionMedian)
    (a.toList, count)
  }

  private def qsort(a: Array[Int], l: Int, r: Int, partition: (Array[Int], Int, Int) => Int, invCount: Long = 0): Long = {
    if (l < r) {
      val q = partition(a, l, r)
      val left = qsort(a, l, q - 1, partition)
      val right = qsort(a, q + 1, r, partition)
      left + right + (r - l)
    } else 0
  }

  private def partitionCormen(a: Array[Int], l: Int, r: Int): Int = {
    val x = a(r)
    var i = l
    for (j <- l to r - 1) {
      if (a(j) < x) {
        swap(a, i, j)
        i = i + 1
      }
    }
    swap(a, i, r)
    i
  }

  private def partitionFirst(a: Array[Int], l: Int, r: Int): Int = {
    val x = a(l)
    var i = l + 1
    for (j <- l + 1 to r) {
      if (a(j) < x) {
        swap(a, j, i)
        i = i + 1
      }
    }
    swap(a, l, i - 1)
    i - 1
  }

  private def partitionLast(a: Array[Int], l: Int, r: Int): Int = {
    swap(a, l, r)
    val x = a(l)
    var i = l + 1
    for (j <- l + 1 to r) {
      if (a(j) < x) {
        swap(a, j, i)
        i = i + 1
      }
    }
    swap(a, l, i - 1)
    i - 1
  }

  private def partitionMedian(a: Array[Int], l: Int, r: Int): Int = {
    val pivotInd = median(a, l, r, (r - l)/2 + l)
    swap(a, l, pivotInd)
    val x = a(l)
    var i = l + 1
    for (j <- l + 1 to r) {
      if (a(j) < x) {
        swap(a, j, i)
        i = i + 1
      }
    }
    swap(a, l, i - 1)
    i - 1
  }

  def median(a: Array[Int], i1:Int, i2: Int, i3: Int): Int = {
    val list = List(a(i1), a(i2), a(i3))
    val sorted = list.sorted
    val med = sorted(1)
    if (med == a(i1)) i1
    else if (med == a(i2)) i2
    else i3
  }

}
