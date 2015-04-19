package com.easytoolsoft.concurrentprogramming

import com.easytoolsoft.concurrentprogramming.cli.CliCommand

object StartUp {
  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      try {
        val className = String.format("com.easytoolsoft.concurrentprogramming.cli.%sCliCommand", args(0))
        val cliCommand = Class.forName(className).newInstance().asInstanceOf[CliCommand]
        cliCommand.execute(args)
      } catch {
        case t: Throwable => t.printStackTrace()
      }
    }
  }
}
