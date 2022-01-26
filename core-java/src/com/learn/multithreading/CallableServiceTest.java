package com.learn.multithreading;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		Future<String> taskResult =  executorService.submit(new CallableTask("Arjun"));
		
		
		System.out.println(taskResult.get());
		
		System.out.println("shutDown of executorService");
		executorService.shutdown();
	}
	
}
