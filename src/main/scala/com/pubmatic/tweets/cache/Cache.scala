package com.pubmatic.tweets.cache

import scala.collection.mutable

/**
 * Created by jegan on 31/5/15.
 */
sealed trait Cache[K, V] extends WithDefault[V] {

  def apply(key: K) = get(key) match {
    case None => default
    case Some(value) => value
  }

  /**
   * Maps the specified key to the value in the cache.
   *
   * @param key - Key to be mapped
   * @param value
   */
  def +=(key: K, value: V)

  def -=(key: K)

  /**
   * Returns the Value associated with the key
   *
   * @param key
   * @return
   */
  def get(key: K): Option[V]

  /**
   * Returns the whole cache as an immutable map
   *
   * @return - immutable map
   */
  def getAll: Map[K, V]

  /**
   * Applies the function to the specified key
   *
   * @param key - Key
   * @param f - Function to be applied on the key
   */
  def map(key: K, f: V => V)

  /**
   * Returns the size of the map
   *
   * @return
   */
  def size: Int
}

trait WithDefault[V] {
  def default: V
}

object SimpleHashTagCache extends Cache[String, Int] {

  val cache = mutable.Map[String, Int]().empty.withDefaultValue(0)

  override def +=(tag: String, count: Int) = cache += (tag -> count)

  override def -=(key: String) = cache -= key

  def ++(tag: String) = map(tag, c => c + 1)

  override def get(tag: String): Option[Int] = cache.get(tag)

  override def getAll: Map[String, Int] = cache.toMap

  override def map(key: String, f: (Int) => Int) = this += (key, f(cache(key)))

  override def size: Int = cache.size

  override def default: Int = 0
}
