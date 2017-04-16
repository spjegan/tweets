package com.tweets.cache

import com.pubmatic.tweets.cache.SimpleHashTagCache
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by jegan on 31/5/15.
 */
class SimpleHashTagCacheTest extends FlatSpec with Matchers {

  "+=" should "add elements to the cache" in {
    val cache = new SimpleHashTagCache()

    cache += ("k1", 1)

    cache("k1") should be (1)
    cache("k2") should be (0)
    cache.size should be (1)
  }

  "++" should "increment the value of the key" in {
    val cache = new SimpleHashTagCache()

    cache ++ "test1"
    cache("test1") should be (1)

    cache ++ "test1"
    cache("test1") should be (2)
  }

  "get" should "return Option" in {
    val cache = new SimpleHashTagCache()

    cache += ("k1", 1)

    cache get "k1" should be (Some(1))
    cache get "k2" should be (None)
  }

  "getAll" should "return immutable map" in {
    val cache = new SimpleHashTagCache()

    cache += ("k1", 1)
    cache += ("k2", 2)
    cache += ("k3", 3)

    val other = cache.getAll
    other should be (Map("k1" -> 1, "k2" -> 2, "k3" -> 3))
    cache.size should be (other.size)
  }

  "map" should "apply the function to the specified key" in {
    val cache = new SimpleHashTagCache()

    cache += ("k1", 1)
    cache += ("k2", 1)

    cache("k1") should be (1)

    cache map ("k1", c => c + 1)
    cache("k1") should be (2)
    cache("k2") should be (1)
  }
}
