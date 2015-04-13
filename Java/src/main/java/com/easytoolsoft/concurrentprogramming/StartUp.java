package com.easytoolsoft.concurrentprogramming;

import com.easytoolsoft.concurrentprogramming.cli.CliCommand;

public class StartUp {
	public static void main(final String[] args) {
		if (args == null || args.length == 0) {
			return;
		}

		String className = String.format("com.easytoolsoft.concurrentprogramming.cli.%sCliCommand", args[0]);
		try {
			CliCommand cliCommand = (CliCommand) Class.forName(className).newInstance();
			cliCommand.execute(args);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
