package com.easytoolsoft.concurrentprogramming.cli;

import com.easytoolsoft.concurrentprogramming.ch1.ConcurrentNetAssetValue;
import com.easytoolsoft.concurrentprogramming.ch1.SequentialNetAssetValue;

public class Ch1CliCommand implements CliCommand {

	@Override
	public void execute(String[] args) {
		if ("nav".equals(args[1]))
			this.executeNavCli(args);
		if ("prime".equals(args[1]))
			this.executePrimeCli(args);
	}

	private void executeNavCli(final String[] args) {
		String arg2 = args[2];
		// cli:Ch1 nav seq
		if ("seq".equals(arg2)) {
			new SequentialNetAssetValue().run();
			return;
		}

		String arg3 = args[3];
		//
		// cli:Ch1 nav con -t 15
		if ("con".equals(arg2) && "-t".equals(arg3)) {
			int threadPoolSize = Integer.valueOf(args[4]);
			new ConcurrentNetAssetValue(threadPoolSize).run();
			return;
		}

		// cli:Ch1 nav con -b 0.9
		if ("con".equals(arg2) && "-b".equals(arg3)) {
			double blockingCoefficient = Double.valueOf(args[4]);
			new ConcurrentNetAssetValue(blockingCoefficient).run();
			return;
		}

		// cli:Ch1 nav con -n 70
		if ("con".equals(arg2) && "-n".equals(arg3)) {
			int maxThreads = Integer.valueOf(args[4]);
			for (int i = 2; i <= maxThreads; i++) {
				new ConcurrentNetAssetValue(i).run();
			}
			return;
		}

		System.out.println("Not Found Command!");
	}

	private void executePrimeCli(final String[] args) {

	}

}
