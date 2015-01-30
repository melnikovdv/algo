package org.mlayer.sorting

import org.scalatest.FunSuite

import scala.compat.Platform

class Sorting$Test extends FunSuite {

  val a = Sorting.genList(10)

  test("genArray test") {
    assert(a.length === 10)
    assert(!a.forall(i => (i >= 0) && (i < 10)))
  }

  test("isSorted") {
    assert(!Sorting.isSorted(List(3, 1, 2)))
    assert(Sorting.isSorted(List(1, 2, 3)))
    assert(Sorting.isSorted(List(1, 2)))
    assert(Sorting.isSorted(List(1, 3, 3)))
    intercept[Error](Sorting.isSorted(List()))
  }

  test("mergeSort test") {
    val sorted: List[Int] = Sorting.mergeSort(a)
    assert(a.length === sorted.length)
    assert(Sorting.isSorted(sorted))
  }

  test("bubbleSort test") {
    val sorted: List[Int] = Sorting.bubbleSort(a)
    assert(a.length === sorted.length)
    assert(Sorting.isSorted(sorted))
  }

  test("time") {
    val a = Sorting.genList(5000, 1000)

    var start = Platform.currentTime
    val a1 = Sorting.mergeSort(a)
    println("Time for merge sort size = " + a.length + " is: " + (Platform.currentTime - start) + " ms")
    start = Platform.currentTime
    val a2 = Sorting.bubbleSort(a)
    println("Time for buuble sort size = " + a.length + " is: " + (Platform.currentTime - start) + " ms")
  }

}
