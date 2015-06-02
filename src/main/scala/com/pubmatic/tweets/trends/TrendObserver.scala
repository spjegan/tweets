package com.pubmatic.tweets.trends

import akka.actor.{ActorLogging, Actor}
import com.google.common.collect.MinMaxPriorityQueue

/**
 * Created by jegan on 1/6/15.
 */
class TrendObserver(size: Int) extends Actor with ActorLogging {

  val pq = MinMaxPriorityQueue.orderedBy(new TrendingTagComparator()).maximumSize(size).create[TrendingTag]()

  override def receive: Receive = {
    case tt: TrendingTag =>
//      println(s"Adding trending tag $tt")

//      pq.add(tt)
      if (pq.contains(tt)) {
        pq.remove(tt)
      }
      pq.add(tt)
    case TrendingTags =>
//      println(s"Trending tags are $pq")
      println(s"First in PQ is ${pq.peekFirst()}")
      println(s"Last in PQ is ${pq.peekLast()}")
      val itr = pq.iterator()
      while (itr.hasNext) {
        val tt = itr.next()
        println(s"TrendingTag -> $tt")
      }
//      pq.iterator()
//      sender ! pq
    case _ => Console.err.println("Unknown message received")
  }
}

case class TrendingTag(tag: String, count: Int) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[TrendingTag]

  override def equals(other: Any): Boolean = other match {
    case that: TrendingTag =>
      (that canEqual this) &&
        tag == that.tag
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(tag)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"TrendingTag($tag -> $count)"
}

case object TrendingTags

/*class Tags(val tag: String, val count: Int) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Tags]

  override def equals(other: Any): Boolean = other match {
    case that: Tags =>
      (that canEqual this) &&
        tag == that.tag
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(tag)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}*/
