package com.easytoolsoft.concurrentprogramming.ch1

import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import scala.collection.JavaConversions._

import com.easytoolsoft.concurrentprogramming.util.SinaFinanceUtils

class ConcurrentNetAssetValue extends AbstractNetAssetValue {
  private var threadPoolSize = 4
  private var blockingCoefficient = 0.0

  def this(threadPoolSize: Int) {
    this()
    this.threadPoolSize = threadPoolSize
  }

  def this(blockingCoefficient: Double) {
    this()
    this.blockingCoefficient = blockingCoefficient
  }

  def computeNetAssetValue(tickers: List[String]): Double = {
    val tasks = new ArrayList[Callable[Double]](2000)
    tickers.foreach { ticker =>
      tasks.add(new Callable[Double]() {
        def call(): Double = {
          1000 * SinaFinanceUtils.getCurrentStockPrice(ticker)
        }
      })
    }

    val executor = Executors.newFixedThreadPool(this.threadPoolSize)
    val futures = executor.invokeAll(tasks, 10000, TimeUnit.SECONDS)

    val netAssetValue = futures.foldLeft(0.0) {
      (netAssetValue: Double, future) =>
        netAssetValue + future.get().toDouble
    }
    executor.shutdown()

    netAssetValue
  }

  def getBestThreadPoolSize(blockingCoefficient: Double): Int = {
    (Runtime.getRuntime().availableProcessors() / (1 - blockingCoefficient)).toInt
  }

  override def before() = {
    System.out.println(">>>>并发执行计算用户股票的总资产净值<<<<")
    System.out.println("CPU核心数 :" + Runtime.getRuntime().availableProcessors())
    System.out.println("阻塞系数： " + this.blockingCoefficient)
    System.out.println("线程个数： " + this.threadPoolSize)
    super.before();
  }
}
