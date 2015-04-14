package com.easytoolsoft.concurrentprogramming.ch1;

public abstract class AbstractPrimeFinder {
	protected int number;

	/**
	 * 
	 * @param number
	 */
	protected AbstractPrimeFinder(final int number) {
		this.number = number;
	}

	public void run() {
		this.before();

		final long startTime = System.nanoTime();
		final long numberOfPrimes = this.countPrimes(this.number);
		final long endTime = System.nanoTime();

		System.out.printf("[1-%d]内有： (%d)个素数\n", this.number, numberOfPrimes);
		System.out.println("耗时(单位秒): " + (endTime - startTime) / 1.0e9);
		System.out.println("====================结束====================");
	}

	protected void before() {
		System.out.println("====================开始=====================");
	}

	/**
	 * 统计[1-number]内所有的素数个数
	 * 
	 * @param number
	 *            数字上限
	 * @return 素数个数
	 */
	protected abstract int countPrimes(final int number);

	/**
	 * 统计指定区间(如:[1000-2000])内的素数个数
	 * 
	 * @param lowerNumber
	 *            数字下限
	 * @param upperNumber
	 *            数字上限
	 * @return 素数个数
	 */
	protected int countPrimesInRange(final int lowerNumber, final int upperNumber) {
		int total = 0;
		for (int i = lowerNumber; i <= upperNumber; i++) {
			if (this.isPrime(i)) {
				total++;
			}
		}
		return total;
	}

	/**
	 * 判断当前数字是否为素数
	 * 
	 * @param number
	 *            当前数字
	 * @return true|false
	 */
	private boolean isPrime(final int number) {
		if (number <= 1)
			return false;

		for (int i = 2; i <= Math.sqrt(number); i++)
			if (number % i == 0)
				return false;

		return true;
	}
}
