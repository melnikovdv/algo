package org.mlayer.sorting

import org.scalatest.FunSuite

import scala.compat.Platform

class Bubble$Test extends FunSuite with SortData {

  test("bubble sort test") {
    val sorted: List[Int] = Bubble.sort(data10)
    assert(data10.length === sorted.length)
    assert(Bubble.isSorted(sorted))
  }

  test("bubble sort time") {
    val start = Platform.currentTime
    val sorted = Bubble.sort(data10k)
    println("Bubble sort time for array size = " + sorted.length + " is: " + (Platform.currentTime - start) + " ms")
  }

  ignore("bubble sort invertions") {

  }
}
