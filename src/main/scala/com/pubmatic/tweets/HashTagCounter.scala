package com.pubmatic.tweets

import akka.actor.Actor
import com.pubmatic.tweets.cache.SimpleHashTagCache
import scala.collection.mutable

/**
 * Created by jegan on 31/5/15.
 */
class HashTagCounter extends Actor {

//  var cache = mutable.Map.empty[String, Int].withDefaultValue(0)

  val cache = SimpleHashTagCache

  override def receive: Receive = {
    case Count(tag) =>
//      Console.println(s"Counting tag $tag")
//      cache.update(tag, cache(tag) + 1)
      cache ++ tag
    case GetCount =>
//      Console.println(s"Sending Result cache $cache")
      sender ! cache.getAll
    case _ => Console.err.println("Unknown message received in HashTagCounter")
  }
}

case class Count(tag: String)

case object GetCount