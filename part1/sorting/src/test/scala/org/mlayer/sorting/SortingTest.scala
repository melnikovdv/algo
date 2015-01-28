package org.mlayer.sorting

import org.scalatest.FunSuite

class SortingTest extends FunSuite {
  test("genArray test") {
    val a = Sorting.genArray(10)
    assert(a.length === 10)
    assert(!a.forall(i => (i >= 0) && (i < 10)))
  }
}
