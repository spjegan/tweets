package com.pubmatic.tweets.trends

import java.util.Comparator

/**
 * Created by jegan on 1/6/15.
 */
class TrendingTagComparator extends Comparator[TrendingTag] {

//  override def compare(tt1: TrendingTag, tt2: TrendingTag): Int = tt2.count - tt1.count

  override def compare(tt1: TrendingTag, tt2: TrendingTag): Int = {
    var diff = 0
    if(tt1.equals(tt2)) {
      diff = tt1.count - tt2.count
    } else {
      diff = tt2.count - tt1.count
    }
    diff
  }

/*  override def compare(tt1: TrendingTag, tt2: TrendingTag): Int = {
    var diff = 0
    if(!tt1.equals(tt2)) {
      diff = tt2.count - tt1.count
    }
    diff
  }*/

/*  override def compare(tt1: TrendingTag, tt2: TrendingTag): Int = {
    var diff = 0
    if (tt1.tag.equals(tt2.tag)) {
      if (tt1.count > tt2.count) {
        diff = tt1.count - tt2.count
      } else {
        diff = tt2.count - tt1.count
      }
    } else {
      diff = tt2.count - tt1.count
    }
    diff
  }*/
}
