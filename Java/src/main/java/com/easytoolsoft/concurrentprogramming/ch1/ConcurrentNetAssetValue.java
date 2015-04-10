package com.easytoolsoft.concurrentprogramming.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.easytoolsoft.concurrentprogramming.util.SinaFinanceUitls;

/**
 * 
 * 并发执行计算用户股票的总资产净值
 *
 */
public class ConcurrentNetAssetValue extends AbstractNetAssetValue {
	@Override
	public double computeNetAssetValue(final List<String> tickers) throws Exception {
		//
		// 获取CPU可用的逻辑核心数(有些CPU是双核4线程，这个时间逻辑核心数为4)
		final int nubmerOfCPUCores = Runtime.getRuntime().availableProcessors();
		//
		// 假设当前程序的阻塞系数为0.9，即CPU有90%时间空闲，10%的时间繁忙
		final double blockingCoefficient = 0.9;
		//
		// 计算出当前应该开启的最佳线程数
		int poolSize = (int) (nubmerOfCPUCores / (1 - blockingCoefficient));
		// poolSize = 100;

		System.out.println("CPU核心数 :" + nubmerOfCPUCores);
		System.out.println("线程个数： " + poolSize);

		// 并发计算每支股票的资产净值
		final List<Callable<Double>> tasks = new ArrayList<Callable<Double>>();
		for (final String ticker : tickers) {
			tasks.add(new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return 1000 * SinaFinanceUitls.getCurrentStockPrice(ticker);
				}
			});
		}

		// 启动多个线程，并立即调用任务
		final ExecutorService executor = Executors.newFixedThreadPool(poolSize);
		final List<Future<Double>> futures = executor.invokeAll(tasks, 10000, TimeUnit.SECONDS);

		// 等待所有任务执行完成，并汇总所有股票的资产净值
		double netAssetValue = 0.0;
		for (final Future<Double> future : futures) {
			netAssetValue += future.get();
		}
		executor.shutdown();

		return netAssetValue;
	}

	public static void main(final String[] args) {
		new ConcurrentNetAssetValue().run();
	}
}
