package com.pubmatic.tweets

import akka.actor.{ActorLogging, Actor, ActorRef}
import com.pubmatic.tweets.trends.TrendingTags

/**
 * Created by jegan on 31/5/15.
 */
class Poller(to: ActorRef) extends Actor with ActorLogging {

  override def receive: Receive = {

    case Tick => {
      Console.println("Tick............")
      to ! TrendingTags
    }

    case _ => Console.println("Invalid message received by the Poller")
  }
}

case object Tick
