package com.pubmatic.tweets.trends

import java.util.Comparator

/**
 * Created by jegan on 1/6/15.
 */
class TrendingTagComparator extends Comparator[TrendingTag] {

  override def compare(tt1: TrendingTag, tt2: TrendingTag): Int = {
    var diff = 0
    if(tt1.equals(tt2)) {
      diff = tt1.count - tt2.count
    } else {
      diff = tt2.count - tt1.count
    }
    diff
  }
}
