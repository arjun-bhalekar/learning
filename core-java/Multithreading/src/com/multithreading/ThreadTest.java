package com.multithreading;


public class ThreadTest {

	// 2 ways of creating a Thread
	// 1- By extending a Thread class
	// 2- By Implementing Runnable interface

	// Different Thread States
	// 1. New
	// 2. Runnable
	// 3. Running
	// 4. Blocked/Waiting
	// 5. Sleeping
	// 6. Terminated/Dead

	class Task1 extends Thread {

		@Override
		public void run() {
			System.out.println("Starting Task1");
			for (int i = 1; i < 100; i++) {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Thread.yield();
				System.out.print(" " + i);
			}
			System.out.println("\nTask 1 done");

		}

	}

	class Task2 implements Runnable {

		@Override
		public void run() {
			System.out.println("Starting Task2");
			for (int i = 100; i < 200; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print(" " + i);
			}
			System.out.println("\nTask 2 done");
		}
	}

	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();

		// Task 1
		Task1 task1 = test.new Task1();
		task1.setPriority(Thread.MAX_PRIORITY);
		task1.start(); // wrong : normal method - t1.run();
		// t1.start(); //RunTime exception - java.lang.IllegalThreadStateException

		// Task 2
		Task2 task2 = test.new Task2();
		Thread threadTask2 = new Thread(task2);
		threadTask2.setPriority(Thread.NORM_PRIORITY);
		threadTask2.start();

		// Wait for Task1 & Task2 before going to Task3
		
		
		try {
			task1.join();
			threadTask2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		
		// Task 3
		System.out.println("Starting Task3");
		for (int i = 200; i < 300; i++) {
			System.out.print(" " + i);
		}
		System.out.println("\nTask 3 done");

		System.out.println("--- End of Main ---");
	}

}
