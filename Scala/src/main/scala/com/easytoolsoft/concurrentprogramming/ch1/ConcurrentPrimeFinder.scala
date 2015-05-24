package com.easytoolsoft.concurrentprogramming.ch1

import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import scala.collection.JavaConversions._

class ConcurrentPrimeFinder(number: Int,
                            threadPoolSize: Int,
                            chunkCount: Int) extends AbstractPrimeFinder(number) {

  override protected def countPrimes(number: Int): Int = {
    val tasks = new ArrayList[Callable[Integer]](100)
    val chunkSize = number / chunkCount
    for (i <- 0 to chunkCount - 1) {
      val lowerNumber = (i * chunkSize) + 1
      val upperNumber = if (i == chunkCount - 1) number else (lowerNumber + chunkSize - 1)
      tasks.add(new Callable[Integer]() {
        def call(): Integer = {
          ConcurrentPrimeFinder.this.countPrimesInRange(lowerNumber, upperNumber)
        }
      })
    }

    val executor = Executors.newFixedThreadPool(this.threadPoolSize)
    val futures = executor.invokeAll(tasks, 10000, TimeUnit.SECONDS)
    val count = futures.foldLeft(0) { (count: Int, future) =>
      count + future.get()
    }
    executor.shutdown()

    count
  }

  override protected def before() {
    System.out.println(">>>>并发执行计算出指定范围内的素数<<<<")
    System.out.printf("CPU核心数 :%s,线程个数：%s,区间个数:%s \n",
      Runtime.getRuntime().availableProcessors().toString,
      this.threadPoolSize.toString, this.chunkCount.toString)
    super.before()
  }

}
