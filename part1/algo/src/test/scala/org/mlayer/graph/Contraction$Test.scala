package org.mlayer.graph

import org.mlayer.utils.Utils
import org.scalatest.FunSuite

import scala.compat.Platform

class Contraction$Test extends FunSuite {

  val graph00 = prepareData("1 2 3 4\n2 1 3\n3 1 2\n4 1 5 6\n5 4 6\n6 5 4".split("\n").toList)

  /*
  3--4-----5--6
  |\/|     |\/|
  |/\|     |/\|
  2--1-----7--8
  expected result: 2
  cuts are [(1,7), (4,5)]
   */
  val graph01 = prepareData("1 2 3 4 7\n2 1 3 4\n3 1 2 4\n4 1 2 3 5\n5 4 6 7 8\n6 5 7 8\n7 1 5 6 8\n8 5 6 7".split("\n").toList)

  /*
  expected result: 2
  cuts are [(1,7), (4,5)]
   */
  val graph02 = prepareData("1 4 2 7 3\n2 4 1 3\n3 1 2 4\n4 5 1 2 3\n5 8 7 6 4\n6 8 5 7\n7 6 8 5 1\n8 7 6 5".split("\n").toList)

  /*
  3--4-----5--6
  |\/|     |\/|
  |/\|     |/\|
  2--1     7--8
  expected result: 1
  cut is [(4,5)]
   */
  val graph03 = prepareData("1 2 3 4\n2 1 3 4\n3 1 2 4\n4 1 2 3 5\n5 4 6 7 8\n6 5 7 8\n7 5 6 8\n8 5 6 7".split("\n").toList)

  /*
  expected result: 1
  cut is [(4,5)]
   */
  val graph04 = prepareData("1 3 4 2\n2 1 4 3\n3 1 2 4\n4 5 3 2 1\n5 4 8 6 7\n6 8 7 5\n7 5 8 6\n8 5 7 6".split("\n").toList)

  /*
  expected result: 2
   */
  val graph05 = prepareData("1 2 3 4 5\n2 3 4 1\n3 4 1 2\n4 1 2 3 8\n5 1 6 7 8\n6 7 8 5\n7 8 5 6\n8 4 6 5 7".split("\n").toList)

  /*
  expected result: 3
   */
  val graph06 = prepareData(("1 19 15 36 23 18 39 \n2 36 23 4 18 26 9\n3 35 6 16 11\n4 23 2 18 24\n5 14 8 29 21\n6 34 35 3 16\n" +
    "7 30 33 38 28\n8 12 14 5 29 31\n9 39 13 20 10 17 2\n10 9 20 12 14 29\n11 3 16 30 33 26\n12 20 10 14 8\n" +
    "13 24 39 9 20\n14 10 12 8 5\n15 26 19 1 36\n16 6 3 11 30 17 35 32\n17 38 28 32 40 9 16\n18 2 4 24 39 1\n" +
    "19 27 26 15 1\n20 13 9 10 12\n21 5 29 25 37\n22 32 40 34 35\n23 1 36 2 4\n24 4 18 39 13\n25 29 21 37 31\n" +
    "26 31 27 19 15 11 2\n27 37 31 26 19 29\n28 7 38 17 32\n29 8 5 21 25 10 27\n30 16 11 33 7 37\n31 25 37 27 26 8\n" +
    "32 28 17 40 22 16\n33 11 30 7 38\n34 40 22 35 6\n35 22 34 6 3 16\n36 15 1 23 2\n37 21 25 31 27 30\n" +
    "38 33 7 28 17 40\n39 18 24 13 9 1\n40 17 32 22 34 38").split("\n").toList)

  test("min cut search with contraction algorithm") {
    assert(Contraction.minCut(graph00).head._2.size === 1)
    assert(Contraction.minCut(graph01).head._2.size === 2) // cuts are [(1,7), (4,5)]
    assert(Contraction.minCut(graph02).head._2.size === 2) // cuts are [(1,7), (4,5)]
    assert(Contraction.minCut(graph03).head._2.size === 1) // cut is [(4,5)]
    assert(Contraction.minCut(graph04).head._2.size === 1) // cut is [(4,5)]
    assert(Contraction.minCut(graph05).head._2.size === 2)
    assert(Contraction.minCut(graph06).head._2.size === 3)
  }

  test("assignment") {
    val data = loadData("assignments/3/kargerMinCut.txt")

    val start = Platform.currentTime
    println("min cuts for " + data.size + " is: " + Contraction.minCut(data).head._2.size + ". Time: " + (Platform.currentTime - start) + " ms")
  }

  def prepareData(data: List[String]): Map[Int, List[Int]] = data
    .map(_.split("\\s")
    .map(_.toInt).toList)
    .map(a => (a(0), a.drop(1)))
    .toMap


  def loadData(file: String): Map[Int, List[Int]] = {
    prepareData(Utils.loadData(file))
  }
}
