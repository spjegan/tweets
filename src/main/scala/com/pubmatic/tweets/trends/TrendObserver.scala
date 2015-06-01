package com.pubmatic.tweets.trends

import akka.actor.Actor
import com.google.common.collect.MinMaxPriorityQueue

/**
 * Created by jegan on 1/6/15.
 */
class TrendObserver(size: Int) extends Actor {

  val pq = MinMaxPriorityQueue.orderedBy(new TrendingTagComparator()).maximumSize(size).create[TrendingTag]()

  override def receive: Receive = {
    case tt: TrendingTag => pq.add(tt)
    case "get" => sender ! pq
    case _ => Console.err.println("Unknown message received")
  }

}

case class TrendingTag(tag: String, count: Int)
