package com.tweets

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.routing.ConsistentHashingRouter.ConsistentHashable
import com.pubmatic.tweets.cache.SimpleHashTagCache
import com.pubmatic.tweets.trends.{TrendingTags, TrendingTag}

/**
 * Created by jegan on 31/5/15.
 */
class HashTagCounter(to: ActorRef) extends Actor with ActorLogging {

  val cache = new SimpleHashTagCache()

  override def receive: Receive = {
    case Count(tag) =>
      cache ++ tag
      to ! TrendingTag(tag, cache(tag))
    case _ => Console.err.println("Unknown message received in HashTagCounter")
  }
}

case class Count(tag: String) extends ConsistentHashable {
  def consistentHashKey: Any = tag
}