using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConcurrentProgramming.Ch1
{
    using Helpers;

    /// <summary>
    /// 顺序执行计算用户股票的总资产净值
    /// </summary>
    public class SequentialNetAssetValue : BaseNetAssetValue
    {
        protected override double computeNetAssetValue(IList<string> tickers)
        {
            double netAssetValue = 0.0;
            foreach (string ticker in tickers)
            {
                netAssetValue += 1000 * SinaFinanceHelper.GetCurrentStockPrice(ticker);
            }
            return netAssetValue;
        }

        protected override void Before()
        {
            Console.WriteLine(">>>>顺序执行计算用户股票的总资产净值<<<<");
            base.Before();
        }

    }
}
