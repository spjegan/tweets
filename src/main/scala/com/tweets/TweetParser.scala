package com.tweets

import java.util.StringTokenizer

import akka.actor.{Actor, ActorLogging, ActorRef}

/**
 * Created by jegan on 31/5/15.
 */
class TweetParser(distributor: ActorRef) extends Actor with ActorLogging {

  Console.println("Inside TweetParser")

  override def receive: Receive = {
    case Tweet(text) => parse(text)
    case _ => Console.err.println("Unknown message received")
  }

  private def parse(text: String) = {
    val tokenizer = new StringTokenizer(text, " \t\n\r\f,.:;?!'")
    while(tokenizer.hasMoreTokens) {
      val token = tokenizer.nextToken()
      if (token.startsWith("#")) {
        distributor ! HashTag(token)
      }
    }
  }
}
