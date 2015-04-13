using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConcurrentProgramming.CLI
{
    using Ch1;

    public class Ch1CliCommand : CliCommand
    {
        public void Execute(string[] args)
        {
            if ("nav" == args[1]) 
                this.ExecuteNavCli(args);
            if ("prime" == args[1])
                this.ExecutePrimeCli(args);
        }

        private void ExecuteNavCli(string[] args)
        {
            string arg2 = args[2];
            // cli:Ch1 nav seq
            if ("seq" == arg2)
            {
                new SequentialNetAssetValue().Run();
                return;
            }

            string arg3 = args[3];
            // cli:Ch1 nav con -t 15
            if ("con" == arg2 && "-t" == arg3)
            {
                int threadPoolSize = int.Parse(args[4]);
                new ConcurrentNetAssetValue(threadPoolSize).Run();
                return;
            }

            // cli:Ch1 nav con -b 0.9
            if ("con" == arg2 && "-b" == arg3)
            {
                double blockingCoefficient = double.Parse(args[4]);
                new ConcurrentNetAssetValue(blockingCoefficient).Run();
                return;
            }

            // cli:ch1 nav con -n 70
            if ("con" == arg2 && "-n" == arg3)
            {
                int maxThreads = int.Parse(args[4]);
                for (int i = 2; i <= maxThreads; i++)
                {
                    new ConcurrentNetAssetValue(i).Run();
                }
                return;
            }

            Console.WriteLine("Not found command!");
        }

        private void ExecutePrimeCli(string[] args)
        {

        }
    }
}
