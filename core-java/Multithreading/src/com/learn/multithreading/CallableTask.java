package com.learn.multithreading;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

	private String name;
	
	public CallableTask(String name) {
		this.name = name;
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return "Hello "+name;
	}

	
	
}
