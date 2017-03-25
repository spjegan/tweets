package com.pubmatic.tweets

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * Created by jegan on 31/5/15.
 */
class HashTagCounterTest extends TestKit(ActorSystem("testActorSystem"))
      with WordSpecLike with MustMatchers with ImplicitSender {

  "Counter actor" must {

    val counter = TestActorRef[HashTagCounter]

    "return the count of hashtags" in {
      counter ! Count("#test1")
      counter ! Count("#test1")
      counter ! Count("#test2")
    }

    "return 0 if hashtag is not present in cache" in {
    }
  }

}
