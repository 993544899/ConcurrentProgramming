package com.easytoolsoft.concurrentprogramming.ch1;

/**
 * 
 * 顺序执行计算出指定范围内(如1-1000万)的素数
 *
 */
public class SequentialPrimeFinder extends AbstractPrimeFinder {
	@Override
	public int countPrimes(final int number) {
		return countPrimesInRange(1, number);
	}

	public static void main(final String[] args) {
		int number = 10000000;// Integer.parseInt(args[0]);
		new SequentialPrimeFinder().run(number);
	}
}
