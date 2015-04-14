package com.easytoolsoft.concurrentprogramming.ch1;

/**
 * 
 * 顺序执行计算出指定范围内(如1-2000万)的素数
 *
 */
public class SequentialPrimeFinder extends AbstractPrimeFinder {

	public SequentialPrimeFinder(final int number) {
		super(number);
	}

	@Override
	protected int countPrimes(final int number) {
		return countPrimesInRange(1, number);
	}

	@Override
	protected void before() {
		System.out.println(">>>>顺序执行计算出指定范围内的素数<<<<");
		super.before();
	}

	public static void main(final String[] args) {
		int number = 20000000;// Integer.parseInt(args[0]);
		new SequentialPrimeFinder(number).run();
	}
}
