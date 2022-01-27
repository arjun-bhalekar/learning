package com.multithreading.synchronization;

public class MyTable {

	void printTable(int n) {
		System.out.println("Printing table of : " + n);
		for (int i = 1; i <= 10; i++) {

			System.out.println(n * i);

		}

	}

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

}
