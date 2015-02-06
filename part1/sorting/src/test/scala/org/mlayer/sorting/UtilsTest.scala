package org.mlayer.sorting

import org.scalatest.FunSuite

class UtilsTest extends FunSuite with SortData {

  val utils = new Utils {

    test("genList") {
      assert(data10.length === 10)
      assert(data10.forall(i => (i >= 0) && (i < 10)))
    }

    test("isSorted") {
      assert(!isSorted(List(3, 1, 2)))
      assert(isSorted(List(1, 2, 3)))
      assert(isSorted(List(1, 2)))
      assert(isSorted(List(1, 3, 3)))
      intercept[Error](isSorted(List()))
    }
  }
}
