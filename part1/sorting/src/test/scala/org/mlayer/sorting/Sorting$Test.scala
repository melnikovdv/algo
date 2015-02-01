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
    val sorted: List[Int] = Sorting.sortByMerge(a)
    assert(a.length === sorted.length)
    assert(Sorting.isSorted(sorted))
  }

  test("countInversions") {
    assert(Sorting.countInversions(List()) === (List(), 0))
    assert(Sorting.countInversions(List(1)) === (List(1), 0))
    assert(Sorting.countInversions(List(1, 2)) === (List(1, 2), 0))
    assert(Sorting.countInversions(List(2, 1)) === (List(1, 2), 1))

    assert(Sorting.countInversions(List(1, 3, 5, 2, 4, 6)) === (List(1, 2, 3, 4, 5, 6), 3))
    assert(Sorting.countInversions(List(1, 5, 3, 2, 4))._2 === 4)
    assert(Sorting.countInversions(List(5, 4, 3, 2, 1))._2 === 10)
    assert(Sorting.countInversions(List(1, 3, 1))._2 === 1)
    assert(Sorting.countInversions(List(1, 1, 1))._2 === 0)
    assert(Sorting.countInversions(List(1, 3, 5, 2, 4, 6, 1))._2 === 8)
    assert(Sorting.countInversions(List(9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0))._2 === 56)
    assert(Sorting.countInversions(List(37, 7, 2, 14, 35, 47, 10, 24, 44, 17, 34, 11, 16, 48, 1,
      39, 6, 33, 43, 26, 40, 4, 28, 5, 38, 41, 42, 12, 13, 21, 29, 18, 3, 19, 0, 32, 46, 27, 31,
      25, 15, 36, 20, 8, 9, 49, 22, 23, 30, 45))._2 === 590)
    assert(Sorting.countInversions(List(4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44,
      8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82,
      10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35,
      86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41,
      36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54))._2 === 2372)
  }

  test("bubbleSort test") {
    val sorted: List[Int] = Sorting.bubbleSort(a)
    assert(a.length === sorted.length)
    assert(Sorting.isSorted(sorted))
  }

  ignore("count time for merge sorting and bubble sort") {
    val a = Sorting.genList(5000, 1000)

    var start = Platform.currentTime
    val a1 = Sorting.sortByMerge(a)
    println("Time for merge sort size = " + a.length + " is: " + (Platform.currentTime - start) + " ms")
    start = Platform.currentTime
    val a2 = Sorting.bubbleSort(a)
    println("Time for buuble sort size = " + a.length + " is: " + (Platform.currentTime - start) + " ms")
  }

  ignore("assigment 1: count array inversions") {
    def loadDictionary(file: String): List[Int] = {
      val wordstream = Option {
        getClass.getClassLoader.getResourceAsStream(file)
      } getOrElse {
        sys.error("Could not load word list, dictionary file not found")
      }
      try {
        val s = io.Source.fromInputStream(wordstream)
        s.getLines().toList.map(_.toInt)
      } catch {
        case e: Exception =>
          println("Could not load word list: " + e)
          throw e
      } finally {
        wordstream.close()
      }
    }


    val list = loadDictionary("IntegerArray.txt")
    println("array length: " + list.length)
    println("first 10 elements: " + list.dropRight(list.length - 9).mkString(" "))
    println("inversions count: " + Sorting.countInversions(list)._2)

  }

}
