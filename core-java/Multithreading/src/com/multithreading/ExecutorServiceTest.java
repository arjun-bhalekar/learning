package com.multithreading;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
	
	
	
	
	public static void main(String[] args) {
		
		//simple single thread executor service
		//ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		//run multiple thread
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		executorService.execute(new Task(1));
		executorService.execute(new Task(2));
		executorService.execute(new Task(3));
		executorService.execute(new Task(4));
		executorService.execute(new Task(5));
		executorService.execute(new Task(6));
		executorService.execute(new Task(7));

//		Future<Object> future =  (Future<Object>) executorService.submit(new Task(8));
//		try {
//			System.out.println(future.get());
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		executorService.submit(new Task(2));
//		executorService.submit(new Task(3));
//		executorService.submit(new Task(4));
//		executorService.submit(new Task(5));
		
		executorService.shutdown();
		
	}

}
