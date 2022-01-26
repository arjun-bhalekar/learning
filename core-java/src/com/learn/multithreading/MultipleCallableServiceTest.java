package com.learn.multithreading;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultipleCallableServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		// --- Submitting task one at time
//		Future<String> task1Result = executorService.submit(new CallableTask("Arjun"));
//		Future<String> task2Result = executorService.submit(new CallableTask("Ranga"));
//		Future<String> task3Result = executorService.submit(new CallableTask("Atharva"));
//		
//		
//		System.out.println(task1Result.get());
//		System.out.println(task2Result.get());
//		System.out.println(task3Result.get());

		// --- Submit multiple task at a time

		List<CallableTask> taskList = List.of(new CallableTask("Arjun"), new CallableTask("Ranga"),
				new CallableTask("Atharva"), new CallableTask("Rama"));
		List<Future<String>> taskResults = executorService.invokeAll(taskList);

		for (Future<String> future : taskResults) {
			System.out.println(future.get());
		}

		executorService.shutdown();

	}

}
