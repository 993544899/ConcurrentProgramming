using System;
using System.Net;
using System.IO;

namespace ConcurrentProgramming.Helpers
{
    public static class SinaFinanceHelper
    {
        public static double GetCurrentStockPrice(string ticker)
        {
            double currentPrice = 0.0;

            string url = "http://hq.sinajs.cn/list=" + ticker;
            WebRequest httpReq = HttpWebRequest.Create(url);
            WebResponse httpRes = httpReq.GetResponse();           
            using (StreamReader reader = new StreamReader(httpRes.GetResponseStream()))
            {
                string data = reader.ReadToEnd();
                string[] dataItems = data.Split(',');
                if (dataItems != null && dataItems.Length > 0)
                {
                    currentPrice = double.Parse(dataItems[1]);
                }
            }

            return currentPrice;
        }
    }
}
