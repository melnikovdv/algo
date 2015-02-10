package org.mlayer.sorting

import org.scalatest.FunSuite

import scala.compat.Platform

class Quick$Test extends FunSuite with SortData with InvCountData {

  test("quick sort test") {
    val sorted: List[Int] = Quick.sort(data10)
    assert(data10.length === sorted.length)
    assert(Quick.isSorted(sorted))
  }

  test("quick partition_1st sort test") {
    val sorted: List[Int] = Quick.sortPartFirst(data10)._1
    assert(data10.length === sorted.length)
    assert(Quick.isSorted(sorted))
  }

  test("quick partition_last sort test") {
    assert(Quick.sortPartMedian(List(1, 2, 3))._1 == List(1,2,3))
    assert(Quick.sortPartMedian(List(1, 3, 2))._1 == List(1,2,3))
    assert(Quick.sortPartMedian(List(2, 1, 3))._1 == List(1,2,3))
    assert(Quick.sortPartMedian(List(3, 1, 2))._1 == List(1,2,3))
    assert(Quick.sortPartMedian(List(2, 3, 1))._1 == List(1,2,3))
    assert(Quick.sortPartMedian(List(3, 2, 1))._1 == List(1,2,3))
    assert(Quick.sortPartMedian(List(5, 6, 8, 2))._1 == List(2,5,6,8))
    //    assert(Quick.sortPartMedian(List(5, 6, 2, 8, 9, 2, 0, 0, 2, 7))._1 == List(0, 0, 2, 2, 2, 5, 6, 7, 8, 9))
    val sorted: List[Int] = Quick.sortPartLast(data10)._1
    assert(data10.length === sorted.length)
    assert(Quick.isSorted(sorted))

    val sorted2: List[Int] = Quick.sortPartLast(comparisons100)._1
    assert(data10.length === sorted.length)
    assert(Quick.isSorted(sorted))
  }

  test("median") {
    assert(Quick.median(Array(1, 2, 3, 4), 0, 1, 2) === 1)
    assert(Quick.median(Array(1, 3, 2, 4), 0, 1, 2) === 2)
    assert(Quick.median(Array(2, 3, 1, 4), 0, 1, 2) === 0)
  }

  test("quick partition_median sort test") {
    val sorted: List[Int] = Quick.sortPartMedian(data10)._1
    assert(data10.length === sorted.length)
    assert(Quick.isSorted(sorted))
  }

  test("count") {
    assert(Quick.sortPartFirst(comparisons10)._2 == 25)
    assert(Quick.sortPartLast(comparisons10)._2 == 29)
    assert(Quick.sortPartMedian(comparisons10)._2 == 21)

    assert(Quick.sortPartFirst(comparisons100)._2 == 615)
    assert(Quick.sortPartLast(comparisons100)._2 == 587)
    assert(Quick.sortPartMedian(comparisons100)._2 == 518)

    assert(Quick.sortPartFirst(comparisons1000)._2 == 10297)
    assert(Quick.sortPartLast(comparisons1000)._2 == 10184)
    assert(Quick.sortPartMedian(comparisons1000)._2 == 8921)
  }

  test("quick sort time") {
    val start = Platform.currentTime
    val a1 = Quick.sort(data10k)
    println("Quick sort partition_Cormen time for array size = " + data10k.length + " is: " + (Platform.currentTime - start) + " ms")
  }

  test("quick sort time partition_1st") {
    val start = Platform.currentTime
    val a1 = Quick.sortPartFirst(data10k)._1
    println("Quick sort partition_1st time for array size = " + data10k.length + " is: " + (Platform.currentTime - start) + " ms")
  }

  test("quick sort time partition_last") {
    val start = Platform.currentTime
    val a1 = Quick.sortPartLast(data10k)._1
    println("Quick sort partition_last time for array size = " + data10k.length + " is: " + (Platform.currentTime - start) + " ms")
  }
}
