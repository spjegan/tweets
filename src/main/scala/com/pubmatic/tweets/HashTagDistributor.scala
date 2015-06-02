package com.pubmatic.tweets

import akka.actor.{ActorLogging, Actor, ActorRef, Props}
import akka.routing.ConsistentHashingPool

/**
 * Created by jegan on 31/5/15.
 */
class HashTagDistributor(counter: ActorRef) extends Actor with ActorLogging {

  override def receive: Receive = {
    case HashTag(tag) => counter ! Count(tag)
    case _ => Console.err.println("Unknown message received")
  }
}

case class HashTag(tag: String)
