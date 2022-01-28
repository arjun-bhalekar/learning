package com.multithreading.synchronization;

public class DeadLockDemo {

	public static void main(String[] args) throws InterruptedException {

		String resource1 = new String("ARJUN");
		String resource2 = new String("BHALEKAR");

		// t1 lock resource1 & then resource2
		Thread t1 = new Thread() {
			@Override
			public void run() {

				synchronized (resource1) {
					System.out.println("Thread t1 locked resource 1");

					synchronized (resource2) {
						System.out.println("Thread t1 locked resource 2");
					}

				}

			}
		};

		// t2 lock resource2 and then resource1
		Thread t2 = new Thread() {
			@Override
			public void run() {

				synchronized (resource2) {
					System.out.println("Thread t2 locked resource 2");

					synchronized (resource1) {
						System.out.println("Thread t2 locked resource 1");
					}
				}

			}
		};

		t1.start();
		//t1.join(); // This will solve the deadlock as main will wait for t1 before t2 start
		t2.start();

	}

}
