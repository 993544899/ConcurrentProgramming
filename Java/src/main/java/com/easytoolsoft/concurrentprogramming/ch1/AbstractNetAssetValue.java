package com.easytoolsoft.concurrentprogramming.ch1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractNetAssetValue {
	private static Map<String, Integer> tickerMap = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			// key:股票代号,value:数量(为简化问题全设置为1000股)
			put("sh600000", 1000);// 浦发银行
			put("sh600004", 1000);// 白云机场
			put("sh600005", 1000);// 武钢股份
			put("sh600006", 1000);// 东风汽车
			put("sh600007", 1000);// 中国国贸
			put("sh600008", 1000);// 首创股份
			put("sh600009", 1000);// 上海机场
			put("sh600010", 1000);// 包钢股份
			put("sh600011", 1000);// 华能国际
			put("sh600012", 1000);// 皖通高速
			put("sh600015", 1000);// 华夏银行
			put("sh600016", 1000);// 民生银行
			put("sh600017", 1000);// 日照港
			put("sh600018", 1000);// 上港集团
			put("sh600019", 1000);// 宝钢股份
			put("sh600020", 1000);// 中原高速
			put("sh600021", 1000);// 上海电力
			put("sh600022", 1000);// 山东钢铁
			put("sh600023", 1000);// 浙能电力
			put("sh600026", 1000);// 中海发展
			put("sh600027", 1000);// 华电国际
			put("sh600028", 1000);// 中国石化
			put("sh600029", 1000);// 南方航空
			put("sh600030", 1000);// 中信证券
			put("sh600031", 1000);// 三一重工
			put("sh600033", 1000);// 福建高速
			put("sh600035", 1000);// 楚天高速
			put("sh600036", 1000);// 招商银行
			put("sh600037", 1000);// 歌华有线
			put("sh600038", 1000);// 中直股份
			put("sh600039", 1000);// 四川路桥
			put("sh600048", 1000);// 保利地产
			put("sh600050", 1000);// 中国联通
			put("sh600051", 1000);// 宁波联合
			put("sh600052", 1000);// 浙江广厦
			put("sh600053", 1000);// 中江地产
			put("sh600054", 1000);// 黄山旅游
			put("sh600055", 1000);// 华润万东
			put("sh600056", 1000);// 中国医药
			put("sh600057", 1000);// 象屿股份
			put("sh600058", 1000);// 五矿发展
			put("sh600059", 1000);// 古越龙山
			put("sh600060", 1000);// 海信电器
			put("sh600061", 1000);// 中纺投资
			put("sh600062", 1000);// 华润双鹤
			put("sh600063", 1000);// 皖维高新
			put("sh600064", 1000);// 南京高科
			put("sh600066", 1000);// 宇通客车
			put("sh600067", 1000);// 冠城大通
			put("sh600068", 1000);// 葛洲坝
			put("sh600070", 1000);// 浙江富润
			put("sh600071", 1000);// 凤凰光学
			put("sh600073", 1000);// 上海梅林
			put("sh600074", 1000);// 中达股份
			put("sh600075", 1000);// 新疆天业
			put("sh600076", 1000);// 青鸟华光
			put("sh600077", 1000);// 宋都股份
			put("sh600078", 1000);// 澄星股份
			put("sh600079", 1000);// 人福医药
			put("sh600080", 1000);// 金花股份
			put("sh600081", 1000);// 东风科技
			put("sh600082", 1000);// 海泰发展
			put("sh600083", 1000);// 博信股份
			put("sh600084", 1000);// 中葡股份
			put("sh600085", 1000);// 同仁堂
			put("sh600086", 1000);// 东方金钰
			put("sh600088", 1000);// 中视传媒
			put("sh600089", 1000);// 特变电工
			put("sh600090", 1000);// 啤酒花
			put("sh600093", 1000);// 禾嘉股份
			put("sh600094", 1000);// 大名城
			put("sh600095", 1000);// 哈高科
			put("sh600096", 1000);// 云天化
			put("sh600097", 1000);// 开创国际
			put("sh600098", 1000);// 广州发展
			put("sh600099", 1000);// 林海股份
			put("sh600100", 1000);// 同方股份
			put("sh600101", 1000);// 明星电力
			put("sh600103", 1000);// 青山纸业
			put("sh600104", 1000);// 上汽集团
			put("sh600105", 1000);// 永鼎股份
			put("sh600106", 1000);// 重庆路桥
			put("sh600107", 1000);// 美尔雅
			put("sh600108", 1000);// 亚盛集团
			put("sh600109", 1000);// 国金证券
			put("sh600110", 1000);// 中科英华
			put("sh600111", 1000);// 北方稀土
			put("sh600112", 1000);// 天成控股
			put("sh600113", 1000);// 浙江东日
			put("sh600114", 1000);// 东睦股份
			put("sh600115", 1000);// 东方航空
			put("sh600116", 1000);// 三峡水利
			put("sh600117", 1000);// 西宁特钢
			put("sh600118", 1000);// 中国卫星
			put("sh600119", 1000);// 长江投资
			put("sh600120", 1000);// 浙江东方
			put("sh600121", 1000);// 郑州煤电
			put("sh600122", 1000);// 宏图高科
			put("sh600123", 1000);// 兰花科创
			put("sh600125", 1000);// 铁龙物流
			put("sh600126", 1000);// 杭钢股份
			put("sh600127", 1000);// 金健米业
			put("sh600128", 1000);// 弘业股份
			put("sh600129", 1000);// 太极集团
			put("sh600130", 1000);// 波导股份
			put("sh600131", 1000);// 岷江水电
			put("sh600132", 1000);// 重庆啤酒
			put("sh600133", 1000);// 东湖高新
			put("sh600135", 1000);// 乐凯胶片
			put("sh600136", 1000);// 道博股份
			put("sh600137", 1000);// 浪莎股份
			put("sh600138", 1000);// 中青旅
			put("sh600139", 1000);// 西部资源
			put("sh600141", 1000);// 兴发集团
			put("sh600143", 1000);// 金发科技
			put("sh600146", 1000);// 大元股份
			put("sh600148", 1000);// 长春一东
			put("sh600149", 1000);// 廊坊发展
			put("sh600150", 1000);// 中国船舶
			put("sh600151", 1000);// 航天机电
			put("sh600152", 1000);// 维科精华
			put("sh600153", 1000);// 建发股份
			put("sh600155", 1000);// 宝硕股份
			put("sh600156", 1000);// 华升股份
			put("sh600157", 1000);// 永泰能源
			put("sh600158", 1000);// 中体产业
			put("sh600159", 1000);// 大龙地产
			put("sh600160", 1000);// 巨化股份
			put("sh600161", 1000);// 天坛生物
			put("sh600162", 1000);// 香江控股
			put("sh600165", 1000);// 新日恒力
			put("sh600166", 1000);// 福田汽车
			put("sh600167", 1000);// 联美控股
			put("sh600168", 1000);// 武汉控股
			put("sh600169", 1000);// 太原重工
			put("sh600170", 1000);// 上海建工
			put("sh600171", 1000);// 上海贝岭
			put("sh600172", 1000);// 黄河旋风
			put("sh600173", 1000);// 卧龙地产
			put("sh600175", 1000);// 美都能源
			put("sh600176", 1000);// 中国巨石
			put("sh600177", 1000);// 雅戈尔
			put("sh600179", 1000);// 黑化股份
			put("sh600180", 1000);// 瑞茂通
			put("sh600182", 1000);// S佳通
			put("sh600183", 1000);// 生益科技
			put("sh600184", 1000);// 光电股份
			put("sh600185", 1000);// 格力地产
			put("sh600186", 1000);// 莲花味精
			put("sh600187", 1000);// 国中水务
			put("sh600188", 1000);// 兖州煤业
			put("sh600189", 1000);// 吉林森工
			put("sh600190", 1000);// 锦州港
			put("sh600191", 1000);// 华资实业
			put("sh600192", 1000);// 长城电工
			put("sh600193", 1000);// 创兴资源
			put("sh600195", 1000);// 中牧股份
			put("sh600196", 1000);// 复星医药
			put("sh600197", 1000);// 伊力特
			put("sh600198", 1000);// 大唐电信
		}
	};

	public void run() {
		final List<String> tickers = this.getTickers();

		final long startTime = System.nanoTime();
		double nav = 0.0;
		try {
			nav = this.computeNetAssetValue(tickers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final long endTime = System.nanoTime();

		final String value = new DecimalFormat("##,##0.00").format(nav);
		System.out.println("总资产净值为： " + value);
		System.out.println("耗时(单位秒): " + (endTime - startTime) / 1.0e9);
	}

	/**
	 * 重复请求160支股票10次，目的是让程序计算量增加
	 * 可以明显看出顺序与并发执行之前的差别
	 * 
	 * @return 1600支股票
	 */
	private List<String> getTickers() {
		List<String> tickers = new ArrayList<String>(1600);
		for (int i = 0; i < 10; i++) {
			for (Entry<String, Integer> es : tickerMap.entrySet()) {
				tickers.add(es.getKey());
			}
		}
		return tickers;
	}

	/**
	 * 计算所有股票的总价值（每支股票的数量*当前开盘价，然后再求和 )
	 * 
	 * @param tickersMap
	 *            每次股票代号及数量
	 * @return 所有股票的总资产净值
	 */
	public abstract double computeNetAssetValue(final List<String> tickers) throws Exception;
}
