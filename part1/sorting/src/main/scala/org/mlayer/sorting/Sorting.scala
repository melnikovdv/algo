package org.mlayer.sorting

object Sorting {

  def genList(size: Int, max: Int): List[Int] = List.fill(size)(scala.util.Random.nextInt(max))
  def genList(size: Int): List[Int] = genList(size, 100)

  def isSorted(list: List[Int]): Boolean = list match {
    case Nil => throw new Error("list is empty")
    case head :: Nil => true
    case head :: tail => (head <= tail.head) && isSorted(tail)
  }

  def sortByMerge(list: List[Int]): List[Int] = {
    val n = list.length / 2
    if (n == 0) list
    else {
      def merge(fst: List[Int], scd: List[Int], acc: List[Int] = List()): List[Int] = (fst, scd) match {
        case (Nil, ys) => acc ::: ys
        case (xs, Nil) => acc ::: xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) merge(xs1, scd, acc :+ x)
          else merge(fst, ys1, acc :+ y)
      }
      val (fst, scd) = list.splitAt(n)
      merge(sortByMerge(fst), sortByMerge(scd))
    }
  }

  def countInversions(list: List[Int]): (List[Int], Long) = {
    val n = list.length / 2
    if (n == 0) (list, 0L)
    else {
      def mergeAndCountSplit(fst: List[Int], scd: List[Int], accInv: Long = 0L, acc: List[Int] = List()): (List[Int], Long) =
        (fst, scd) match {
        case (Nil, ys) => (acc ::: ys, accInv)
        case (xs, Nil) => (acc ::: xs, accInv)
        case (x :: xs1, y :: ys1) =>
          if (x < y) mergeAndCountSplit(xs1, scd, accInv, acc :+ x)
          else mergeAndCountSplit(fst, ys1, accInv + 1 + xs1.length, acc :+ y)
      }
      val (fst, scd) = list.splitAt(n)
      val (left, countFst) = countInversions(fst)
      val (right, countScd) = countInversions(scd)
      mergeAndCountSplit(left, right, countFst + countScd)
    }
  }

  def bubbleSort(list: List[Int]): List[Int] = {
    val len = list.length
    val a = list.toArray
    for {
      i <- 0 to len - 1
      j <- 0 to len - 1 - 1
    } yield {
      if (a(j) > a(j + 1)) {
        val tmp = a(j)
        a(j) = a(j + 1)
        a(j + 1) = tmp
      }
    }
    a.toList
  }
}
