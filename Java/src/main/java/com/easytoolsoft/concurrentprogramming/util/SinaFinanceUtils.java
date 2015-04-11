package com.easytoolsoft.concurrentprogramming.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 
 * 操作新浪财经频道提供的股票数据接口的工具类 。
 * 接口地址：http://hq.sinajs.cn/list=sh600000
 * 返回格式为:var hq_str_sh600000="浦发银行,16.86,16.87,17.53,17.57,16.76,
 * 17.54,17.55,451543445,7772119507,306100,17.54,169959,17.53,1085347,
 * 17.52,408084,17.51,644926,17.50,1336909,17.55,936770,17.56,1500764,
 * 17.57,1791929,17.58,816981,17.59,2015-04-10,15:03:05,00";
 */
public class SinaFinanceUtils {
	/**
	 * 获取某一代号的股票的当前开盘价
	 * 
	 * @param ticker
	 *            股票代号
	 * @return 当前开盘价
	 * @throws IOException
	 */
	public static double getCurrentStockPrice(final String ticker) throws IOException {
		BufferedReader reader = null;
		double currentPrice = 0.0;

		try {
			final URL url = new URL("http://hq.sinajs.cn/list=" + ticker);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			final String data = reader.readLine();
			final String[] dataItems = data.split(",");
			if (dataItems != null && dataItems.length > 0) {
				// 其中数据接口中第2个位置的数据为当前开盘价（16.86)
				currentPrice = Double.valueOf(dataItems[1]);
			}
		} finally {
			reader.close();
		}

		return currentPrice;
	}
}
