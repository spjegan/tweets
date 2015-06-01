package com.pubmatic.tweets.trends

import java.util.Comparator

/**
 * Created by jegan on 1/6/15.
 */
class TrendingTagComparator extends Comparator[TrendingTag] {

  override def compare(tt1: TrendingTag, tt2: TrendingTag): Int = tt2.count - tt1.count
}
