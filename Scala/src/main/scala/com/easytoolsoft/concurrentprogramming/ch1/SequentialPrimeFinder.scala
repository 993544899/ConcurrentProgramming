package com.easytoolsoft.concurrentprogramming.ch1

class SequentialPrimeFinder(number: Int) extends AbstractPrimeFinder(number) {

  override protected def countPrimes(number: Int): Int = {
    countPrimesInRange(1, number)
  }

  override protected def before() {
    System.out.println(">>>>顺序执行计算出指定范围内的素数<<<<")
    super.before()
  }
}
