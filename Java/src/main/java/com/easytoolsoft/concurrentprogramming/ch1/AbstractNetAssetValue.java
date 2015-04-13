package com.easytoolsoft.concurrentprogramming.ch1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractNetAssetValue {
	public void run() {
		final List<String> tickers = this.getTickers();

		this.before();
		final long startTime = System.nanoTime();
		double nav = 0.0;
		try {
			nav = this.computeNetAssetValue(tickers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final long endTime = System.nanoTime();

		System.out.println("总资产净值为： " + new DecimalFormat("##,##0.00").format(nav));
		System.out.println("耗时(单位秒): " + (endTime - startTime) / 1.0e9);
		System.out.println("============结束==========");
	}

	protected void before() {
		System.out.println("============开始==========");
	}

	/**
	 * 计算所有股票的总价值（每支股票的数量*当前开盘价，然后再求和 )
	 * 
	 * @param tickersMap
	 *            每次股票代号及数量
	 * @return 所有股票的总资产净值
	 */
	protected abstract double computeNetAssetValue(final List<String> tickers) throws Exception;

	/**
	 * 为简单问题,重复请求100支股票20次，目的是让程序计算量增加
	 * 可以明显看出顺序与并发执行之前的差别
	 * 
	 * @return 2000支股票
	 */
	private List<String> getTickers() {
		List<String> originTickers = Arrays.asList("sh600000", "sh600004", "sh600005", "sh600006", "sh600007",
				"sh600008", "sh600009", "sh600010", "sh600011", "sh600012", "sh600015", "sh600016", "sh600017",
				"sh600018", "sh600019", "sh600020", "sh600021", "sh600022", "sh600023", "sh600026", "sh600027",
				"sh600028", "sh600029", "sh600030", "sh600031", "sh600033", "sh600035", "sh600036", "sh600037",
				"sh600038", "sh600039", "sh600048", "sh600050", "sh600051", "sh600052", "sh600053", "sh600054",
				"sh600055", "sh600056", "sh600057", "sh600058", "sh600059", "sh600060", "sh600061", "sh600062",
				"sh600063", "sh600064", "sh600066", "sh600067", "sh600068", "sh600070", "sh600071", "sh600073",
				"sh600074", "sh600075", "sh600076", "sh600077", "sh600078", "sh600079", "sh600080", "sh600081",
				"sh600082", "sh600083", "sh600084", "sh600085", "sh600086", "sh600088", "sh600089", "sh600090",
				"sh600093", "sh600094", "sh600095", "sh600096", "sh600097", "sh600098", "sh600099", "sh600100",
				"sh600101", "sh600103", "sh600104", "sh600105", "sh600106", "sh600107", "sh600108", "sh600109",
				"sh600110", "sh600111", "sh600112", "sh600113", "sh600114", "sh600115", "sh600116", "sh600117",
				"sh600118", "sh600119", "sh600120", "sh600121", "sh600122", "sh600123", "sh600125");

		List<String> tickers = new ArrayList<String>(2000);
		for (int i = 0; i < 20; i++) {
			tickers.addAll(originTickers);
		}
		return tickers;
	}
}
