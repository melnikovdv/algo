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
    def doSort(list: List[Int], len: Int): List[Int] = {
      val n = len / 2
      if (n == 0) list
      else {
        def merge(fst: List[Int], scd: List[Int], acc: List[Int] = List()): List[Int] = (fst, scd) match {
          case (Nil, ys) => acc.reverse ::: ys
          case (xs, Nil) => acc.reverse ::: xs
          case (x :: xs1, y :: ys1) =>
            if (x < y) merge(xs1, scd, x :: acc)
            else merge(fst, ys1, y :: acc)
        }
        val (fst, scd) = list.splitAt(n)
        merge(doSort(fst, n), doSort(scd, len - n))
      }
    }
    doSort(list, list.length)
  }

  def countInversions(list: List[Int]): Long = {
    def doCount(list: List[Int], len: Int): (List[Int], Long) = {
      val n = len / 2
      if (n == 0) (list, 0L)
      else {
        def mergeAndCountSplit(fst: List[Int], fstLen: Int, scd: List[Int], scdLen: Int, accInv: Long = 0L, acc: List[Int] = List()): (List[Int], Long) =
          (fst, scd) match {
            case (Nil, ys) => (acc.reverse ::: ys, accInv)
            case (xs, Nil) => (acc.reverse ::: xs, accInv)
            case (x :: xs1, y :: ys1) =>
              if (x <= y) mergeAndCountSplit(xs1, fstLen - 1, scd, scdLen, accInv, x :: acc)
              else mergeAndCountSplit(fst, fstLen, ys1, scdLen - 1, accInv + 1 + fstLen - 1, y :: acc)
          }
        val (fst, scd) = list.splitAt(n)
        val (left, countFst) = doCount(fst, n)
        val (right, countScd) = doCount(scd, len - n)
        mergeAndCountSplit(left, n, right, len - n, countFst + countScd)
      }
    }
    doCount(list, list.length)._2
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
