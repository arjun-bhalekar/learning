package com.multithreading.synchronization;

public class StaticSynchronizationTest {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread() {
			public void run() {
				MyTable.printTableThreadSafeV3(5);
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				MyTable.printTableThreadSafeV3(9);
			}
		};
		
		t1.start();
		t2.start();
		
	}

}
