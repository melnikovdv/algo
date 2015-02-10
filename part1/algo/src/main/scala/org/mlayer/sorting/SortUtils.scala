package org.mlayer.sorting

trait SortUtils {

  def swap(a: Array[Int], i: Int, j: Int) = {
    if (i != j) {
      val tmp = a(i)
      a(i) = a(j)
      a(j) = tmp
    }
  }

  def isSorted(list: List[Int]): Boolean = list match {
    case Nil => throw new Error("list is empty")
    case head :: Nil => true
    case head :: tail => (head <= tail.head) && isSorted(tail)
  }

  def genList(size: Int, max: Int): List[Int] = List.fill(size)(scala.util.Random.nextInt(max))
  def genList(size: Int): List[Int] = genList(size, 100)

}
