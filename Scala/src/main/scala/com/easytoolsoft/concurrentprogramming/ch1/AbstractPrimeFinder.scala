package com.easytoolsoft.concurrentprogramming.ch1

import org.scalacheck.Prop.False

abstract class AbstractPrimeFinder(protected val number: Int) {
  protected def run() {
    this.before()

    val startTime = System.nanoTime()
    val numberOfPrimes = this.countPrimes(this.number)
    val endTime = System.nanoTime()

    System.out.printf("[1-%d]内有： (%d)个素数", this.number.toString, numberOfPrimes.toString)
    System.out.println("耗时(单位秒): " + (endTime - startTime) / 1.0e9)
    System.out.println("====================结束====================")
  }

  protected def before() = {
    System.out.println("====================开始=====================")
  }

  protected def countPrimes(number: Int): Int

  protected def countPrimesInRange(lower: Int, upper: Int): Int = {
    Range(lower, upper).count(this.isPrime(_))
  }

  private def isPrime(number: Int): Boolean = {
    if (number <= 1)
      false
    for (i <- 2 to Math.sqrt(number).toInt) {
      if (number % i == 0)
        return false
    }
    true
  }
}
