package com.easytoolsoft.concurrentprogramming.util

import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

object SinaFinanceUtils {
  def getCurrentStockPrice(ticker: String): Double = {
    val url = new URL("http://hq.sinajs.cn/list=" + ticker)
    val reader = new BufferedReader(new InputStreamReader(url.openStream()))
    val data = reader.readLine()
    data.split(",")(1).toDouble
  }
}
