using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ConcurrentProgramming.Ch1
{
    using Helpers;
    using Extension;

    /// <summary>
    /// 并发执行计算用户股票的总资产净值
    /// </summary>
    public class ConcurrentNetAssetValue : BaseNetAssetValue
    {
        /// <summary>
        /// 线程池大小
        /// </summary>
        private readonly int threadPoolSize;

        /// <summary>
        /// 程序阻塞系数
        /// </summary>
        private double blockingCoefficient = 0.0;

        public ConcurrentNetAssetValue(int threadPoolSize)
        {
            this.threadPoolSize = threadPoolSize;
        }

        public ConcurrentNetAssetValue(double blockingCoefficient)
        {
            this.blockingCoefficient = blockingCoefficient;
            this.threadPoolSize = this.GetBestThreadPoolSize(blockingCoefficient);
        }

        protected override double computeNetAssetValue(IList<string> tickers)
        {
            TaskFactory factory = new TaskFactory(new LimitedConcurrencyLevelTaskScheduler(this.threadPoolSize));
            var tasks = new List<Task<double>>(2000);
            foreach (var ticker in tickers)
            {
                tasks.Add(factory.StartNew(
                    () => 1000 * SinaFinanceHelper.GetCurrentStockPrice(ticker)));
            }

            return Task.WhenAll(tasks).Result.Sum();
        }

        protected override void Before()
        {
            Console.WriteLine(">>>>并发执行计算用户股票的总资产净值<<<<");
            Console.WriteLine("CPU核心数:" + Environment.ProcessorCount);
            Console.WriteLine("阻塞系数：" + this.blockingCoefficient);
            Console.WriteLine("线程个数：" + this.threadPoolSize);
            base.Before();
        }

        /// <summary>
        /// 计算出当前最佳线程数
        /// 最佳线程数的计算公式为<code>(CPU或核心数  / (1 - 阻塞系数))</code>
        /// </summary>
        /// <param name="blockingCoefficient">程序阻塞系数，假设当前程序的阻塞系数为0.9，即CPU有90%时间空闲，10%的时间繁忙</param>
        /// <returns>最佳线程数</returns>
        private int GetBestThreadPoolSize(double blockingCoefficient)
        {
            // 获取CPU可用的逻辑核心数(有些CPU是双核4线程，这个时间逻辑核心数为4)
            int nubmerOfCPUCores = Environment.ProcessorCount;
            return (int)(nubmerOfCPUCores / (1 - blockingCoefficient));
        }
    }
}
