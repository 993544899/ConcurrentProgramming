package com.easytoolsoft.concurrentprogramming;

import com.easytoolsoft.concurrentprogramming.ch1.ConcurrentNetAssetValue;
import com.easytoolsoft.concurrentprogramming.ch1.SequentialNetAssetValue;

public class StartUp {
	public static void main(String[] args) {
		new SequentialNetAssetValue().run();
		new ConcurrentNetAssetValue().run();
	}

}
