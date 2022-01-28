package com.multithreading.synchronization;

public class MyTable {

	void printTable(int n) {
		System.out.println("Printing table of : " + n);
		for (int i = 1; i <= 10; i++) {

			System.out.println(n * i);

		}

	}
	
	/**
	 * Method without Synchronization
	 * @param n
	 */
	synchronized void printTableThreadSafeV1(int n) {
		System.out.println("Printing table of : " + n);
		for (int i = 1; i <= 10; i++) {

			System.out.println(n * i);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Synchronized block
	 * @param n
	 */
	void printTableThreadSafeV2(int n) {
		System.out.println("Printing table of : " + n);
		synchronized (this) {
			for (int i = 1; i <= 10; i++) {

				System.out.println(n * i);
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Synchronized static method
	 * @param n
	 */
	synchronized static void printTableThreadSafeV3(int n) {
		System.out.println("Printing table of : " + n);
		for (int i = 1; i <= 10; i++) {

			System.out.println(n * i);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
