package com.easytoolsoft.concurrentprogramming.ch1

import com.easytoolsoft.concurrentprogramming.util.SinaFinanceUtils

class SequentialNetAssetValue extends AbstractNetAssetValue {
  override def computeNetAssetValue(tickers: List[String]): Double = {
    tickers.foldLeft(0.0: Double) {
      (netAssetValue: Double, ticker) =>
        netAssetValue + SinaFinanceUtils.getCurrentStockPrice(ticker)
    }
  }
}
