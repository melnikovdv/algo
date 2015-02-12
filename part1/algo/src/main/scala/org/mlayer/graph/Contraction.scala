package org.mlayer.graph

object Contraction {

  def minCut(g: Map[Int, List[Int]]): Map[Int, List[Int]] = {
    var minCut = g
    for { i <- 1 to 1000} {
      val contraction = contract(g)
      if (minCut.head._2.size - 1 > contraction.head._2.size - 1) minCut = contraction
    }
    println(minCut)
    minCut
  }

  private def merge(map: Map[Int, List[Int]], fst: Int, scd: Int): Map[Int, List[Int]] = {
    var res = map
    for (key <- map.keySet) {
      if (key == fst) {
        res = res.updated(fst, map(fst) ++ map(scd))
      }
      res = res.updated(key, res(key).map(i => if (i == scd) fst else i).filter(_ != key))
    }
    res - scd
  }

  private def contract(map: Map[Int, List[Int]]): Map[Int, List[Int]] = {
    if (map.size > 2) {
      val fst = map.keySet.toList(scala.util.Random.nextInt(map.size))
      val scd = map(fst)(scala.util.Random.nextInt(map(fst).size))
      val merged: Map[Int, List[Int]] = merge(map, fst, scd)
      contract(merged)
    } else map
  }
}
