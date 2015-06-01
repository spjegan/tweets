package com.pubmatic.tweets

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinRouter
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by jegan on 31/5/15.
 */
object TweetStream extends App {

  val configuration = new ConfigurationBuilder()
                      .setDebugEnabled(true)
                      .setOAuthConsumerKey("cZuOtLLtdpPe2vaiF5mZQxZIB")
                      .setOAuthConsumerSecret("eePBISVmXBWhk01uvb810qi5IpIo3xnIgJmbhdywqarV3dlmYL")
                      .setOAuthAccessToken("1394959760-IsZz7rGXxAuVox72rTAm8BomskV4VeoAg7tOi0y")
                      .setOAuthAccessTokenSecret("YXAiw4sKyqkDSjmBh9VaY1OEPRkMqSipx6Oa5PdtD0h8o")
                      .build()

  val as = ActorSystem("StreamingActors")
//  val distributor = as.actorOf(Props[HashTagDistributor], name = "Distributor")
  val counter = as.actorOf(Props[HashTagCounter], name = "Counter")

  val distributor = as.actorOf(Props(classOf[HashTagDistributor], counter), name = "Distributor")
  val tweetParser = as.actorOf(Props(classOf[TweetParser], distributor).withRouter(RoundRobinRouter(nrOfInstances = 5)), name = "TweetParser")

  val poller = as.actorOf(Props(classOf[Poller], counter), name = "Poller")

  val scheduler = as.scheduler.schedule(5 seconds, 5 seconds, poller, Tick)

  def listener = new StatusListener {

    def onStatus(status: Status) = tweetParser ! Tweet(status.getText)

    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) = ???

    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) = ???

    def onException(ex: Exception) = ex.printStackTrace()

    override def onStallWarning(warning: StallWarning): Unit = ???

    override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = ???
  }

  val twitterStream = new TwitterStreamFactory(configuration).getInstance
  twitterStream.addListener(listener)
  twitterStream.sample("en")
}

case class Tweet(tweet: String)
