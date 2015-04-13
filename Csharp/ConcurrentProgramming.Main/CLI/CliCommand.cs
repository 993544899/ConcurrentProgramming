using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConcurrentProgramming.CLI
{
    public interface CliCommand
    {
        void Execute(string[] args);
    }
}
