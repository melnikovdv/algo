package org.mlayer.sorting

import org.mlayer.utils.Utils
import org.scalatest.FunSuite

import scala.compat.Platform

class AssignmentTest extends FunSuite {

  test("assignment 1.1: count array inversions") {
    val start = Platform.currentTime
    val list = loadData("assignment1data.txt")
    println("inversions count for array size " + list.length + " is: " + Merge.countInversions(list) + ". Time: " + (Platform.currentTime - start) + " ms")
  }

  test("assignment 1.2: count comparisons with different partitions") {
    var start = 0L
    val list = loadData("assignment2data.txt")
    start = Platform.currentTime
    println("comparisons partition_first count for array size " + list.length + " is: " + Quick.sortPartFirst(list)._2 + ". Time: " + (Platform.currentTime - start) + " ms")
    start = Platform.currentTime // 160361
    println("comparisons partition_last count for array size " + list.length + " is: " + Quick.sortPartLast(list)._2 + ". Time: " + (Platform.currentTime - start) + " ms")
    start = Platform.currentTime
    println("comparisons partition_median for array size " + list.length + " is: " + Quick.sortPartMedian(list)._2 + ". Time: " + (Platform.currentTime - start) + " ms")
  }


  def loadData(file: String): List[Int] = {
    Utils.loadData(file).map(_.toInt)
  }

}
