package com.easytoolsoft.concurrentprogramming.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.easytoolsoft.concurrentprogramming.util.SinaFinanceUtils;

/**
 * 
 * 并发执行计算用户股票的总资产净值
 *
 */
public class ConcurrentNetAssetValue extends AbstractNetAssetValue {
	// 线程池大小
	private final int threadPoolSize;
	// 程序阻塞系数
	private double blockingCoefficient = 0.0;

	public ConcurrentNetAssetValue(final int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	public ConcurrentNetAssetValue(final double blockingCoefficient) {
		this.blockingCoefficient = blockingCoefficient;
		this.threadPoolSize = this.getBestThreadPoolSize(blockingCoefficient);
	}

	@Override
	protected double computeNetAssetValue(final List<String> tickers) throws Exception {
		//
		// 并发计算每支股票的资产净值
		final List<Callable<Double>> tasks = new ArrayList<Callable<Double>>(2000);
		for (final String ticker : tickers) {
			tasks.add(new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return 1000 * SinaFinanceUtils.getCurrentStockPrice(ticker);
				}
			});
		}

		// 启动多个线程，并立即调用任务
		final ExecutorService executor = Executors.newFixedThreadPool(this.threadPoolSize);
		final List<Future<Double>> futures = executor.invokeAll(tasks, 10000, TimeUnit.SECONDS);

		// 等待所有任务执行完成，并汇总所有股票的资产净值
		double netAssetValue = 0.0;
		for (final Future<Double> future : futures) {
			netAssetValue += future.get();
		}
		executor.shutdown();

		return netAssetValue;
	}

	@Override
	protected void before() {
		System.out.println(">>>>并发执行计算用户股票的总资产净值<<<<");
		System.out.println("CPU核心数 :" + Runtime.getRuntime().availableProcessors());
		System.out.println("阻塞系数： " + this.blockingCoefficient);
		System.out.println("线程个数： " + this.threadPoolSize);
		super.before();
	}

	/**
	 * 
	 * 计算出当前应该开启的最佳线程数
	 * 最佳线程数的计算公式为<code>(CPU或核心数  / (1 - 阻塞系数))</code>
	 * 
	 * @param blockingCoefficient
	 *            程序阻塞系数，假设当前程序的阻塞系数为0.9，即CPU有90%时间空闲，10%的时间繁忙
	 * @return 最佳线程数
	 */
	private int getBestThreadPoolSize(final double blockingCoefficient) {
		//
		// 获取CPU可用的逻辑核心数(有些CPU是双核4线程，这个时间逻辑核心数为4)
		final int nubmerOfCPUCores = Runtime.getRuntime().availableProcessors();
		return (int) (nubmerOfCPUCores / (1 - blockingCoefficient));
	}

	public static void main(final String[] args) {
		// 按指定线程数
		final int threadPoolSize = 15;
		new ConcurrentNetAssetValue(threadPoolSize).run();

		// 按阻塞系统自动计算出最佳线程数
		final double blockingCoefficient = 0.9;
		new ConcurrentNetAssetValue(blockingCoefficient).run();

		// 设置不同线程数
		for (int i = 4; i <= 70; i++) {
			new ConcurrentNetAssetValue(i).run();
		}
	}
}
