using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConcurrentProgramming.Main
{
    using CLI;

    class Program
    {
        static void Main(string[] args)
        {
            if (args == null ||
                args.Length == 0) return;

            string typeName = string.Format("ConcurrentProgramming.CLI.{0}CliCommand", args[0]);
            CliCommand cliCommand = (CliCommand)Activator.CreateInstance(Type.GetType(typeName));
            cliCommand.Execute(args);

            Console.Read();
        }
    }
}
