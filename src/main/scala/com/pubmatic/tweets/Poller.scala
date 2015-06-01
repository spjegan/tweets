package com.pubmatic.tweets

import akka.actor.{Actor, ActorRef}

/**
 * Created by jegan on 31/5/15.
 */
class Poller(counter: ActorRef) extends Actor {

  override def receive: Receive = {

    case Tick => {
      Console.println("Tick............")
      counter ! GetCount
    }

    case map: Map[String, Int] => {
      Console.println("HashTags are....")
      map.foreach(t => Console.println(s"HashTag - ${t._1} = ${t._2}"))
    }

    case _ => Console.println("Invalid message received by the Poller")
  }
}

case object Tick
