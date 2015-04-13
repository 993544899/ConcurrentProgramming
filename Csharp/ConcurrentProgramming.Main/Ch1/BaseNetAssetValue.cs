using System;
using System.Collections.Generic;
using System.Linq;
using System.Diagnostics;

namespace ConcurrentProgramming.Ch1
{
    public abstract class BaseNetAssetValue
    {
        public void Run()
        {
            var tickers = this.GetTickers();

            this.Before();
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();

            double nav = 0.0;
            try
            {
                nav = this.computeNetAssetValue(tickers);
            }
            catch (Exception ex)
            {
                throw ex;
            }
            stopWatch.Stop();

            Console.WriteLine("总资产净值为：{0:0,0.00}", nav);
            Console.WriteLine("耗时(单位秒): " + stopWatch.Elapsed.TotalSeconds);
            Console.WriteLine("============结束==========");
        }

        protected virtual void Before()
        {
            Console.WriteLine("============开始==========");
        }

        /// <summary>
        /// 计算所有股票的总价值（每支股票的数量*当前开盘价，然后再求和 )
        /// </summary>
        /// <param name="tickers">每次股票代号及数量</param>
        /// <returns>所有股票的总资产净值</returns>
        protected abstract double computeNetAssetValue(IList<string> tickers);

        /// <summary>
        ///  为简单问题,重复请求100支股票20次，目的是让程序计算量增加
        ///  可以明显看出顺序与并发执行之前的差别
        /// </summary>
        /// <returns>2000支股票</returns>
        private IList<string> GetTickers()
        {
            IList<string> originTickers = new List<string>(100) {
                "sh600000","sh600004","sh600005","sh600006","sh600007",
                "sh600008","sh600009","sh600010","sh600011","sh600012",
                "sh600015","sh600016","sh600017","sh600018","sh600019",
                "sh600020","sh600021","sh600022","sh600023","sh600026",
                "sh600027","sh600028","sh600029","sh600030","sh600031",
                "sh600033","sh600035","sh600036","sh600037","sh600038",
                "sh600039","sh600048","sh600050","sh600051","sh600052",
                "sh600053","sh600054","sh600055","sh600056","sh600057",
                "sh600058","sh600059","sh600060","sh600061","sh600062",
                "sh600063","sh600064","sh600066","sh600067","sh600068",
                "sh600070","sh600071","sh600073","sh600074","sh600075",
                "sh600076","sh600077","sh600078","sh600079","sh600080",
                "sh600081","sh600082","sh600083","sh600084","sh600085",
                "sh600086","sh600088","sh600089","sh600090","sh600093",
                "sh600094","sh600095","sh600096","sh600097","sh600098",
                "sh600099","sh600100","sh600101","sh600103","sh600104",
                "sh600105","sh600106","sh600107","sh600108","sh600109",
                "sh600110","sh600111","sh600112","sh600113","sh600114",
                "sh600115","sh600116","sh600117","sh600118","sh600119",
                "sh600120","sh600121","sh600122","sh600123","sh600125" 
            };

            var tickers = new List<string>(2000);
            foreach (var i in Enumerable.Range(1, 20))
            {
                tickers.AddRange(originTickers);
            }
            return tickers;
        }
    }
}
