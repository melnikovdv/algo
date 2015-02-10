package org.mlayer.sorting

import org.scalatest.FunSuite

import scala.compat.Platform

class Merge$Test extends FunSuite with InvCountData with SortData {

  test("merge sort test") {
    val sorted: List[Int] = Merge.sort(data10)
    assert(data10.length === sorted.length)
    assert(Merge.isSorted(sorted))
  }

  test("merge count inversions test") {
    assert(Merge.countInversions(invList01) === invList01Count)
    assert(Merge.countInversions(invList02) === ivnList02Count)
    assert(Merge.countInversions(invList03) === invList03Count)
    assert(Merge.countInversions(invList04) === invList04Count)
    assert(Merge.countInversions(invList05) === invList05Count)
    assert(Merge.countInversions(invList06) === invList06Count)
    assert(Merge.countInversions(invList07) === invList07Count)
    assert(Merge.countInversions(invList08) === invList08Count)
    assert(Merge.countInversions(invList09) === invList09Count)
    assert(Merge.countInversions(invList10) === invList10Count)
    assert(Merge.countInversions(invList11) === invList11Count)
    assert(Merge.countInversions(invList12) === invList12Count)
    assert(Merge.countInversions(invList13) === invList13Count)
  }

  test("merge sort time") {
    val start = Platform.currentTime
    val a1 = Merge.sort(data10k)
    println("Merge sort time for array size = " + data10k.length + " is: " + (Platform.currentTime - start) + " ms")
  }
}
