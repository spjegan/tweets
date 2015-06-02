package com.pubmatic.tweets.trends

import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestActorRef, ImplicitSender, TestKit, TestActor}
import com.google.common.collect.MinMaxPriorityQueue
import com.pubmatic.tweets.{Count, HashTagCounter}
import org.scalatest.{WordSpecLike, MustMatchers, FunSuite}

/**
 * Created by jegan on 1/6/15.
 */
class TrendObserverTest extends TestKit(ActorSystem("testActor"))
        with WordSpecLike with MustMatchers with ImplicitSender {

  "TrendObserver actor" must {

    val to = TestActorRef(Props(classOf[TrendObserver], 3), name = "TestTrendObserver")

    "return the trending hashtags" in {
      to ! TrendingTag("#test1", 10)
      to ! TrendingTag("#test2", 20)
      to ! TrendingTag("#test3", 30)
      to ! TrendingTag("#test4", 40)
      to ! TrendingTag("#test5", 50)
      to ! TrendingTag("#test6", 15)
      to ! TrendingTag("#test7", 100)

      to ! TrendingTags

      //      counter ! GetCount("#test1")
//      expectMsg(pq: MinMaxPriorityQueue)

      //      counter ! GetCount("#test2")
      //      expectMsg(1)
    }
  }
}
