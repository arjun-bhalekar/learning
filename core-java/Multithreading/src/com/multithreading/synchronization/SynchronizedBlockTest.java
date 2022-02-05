package com.multithreading.synchronization;

public class SynchronizedBlockTest {
	
	public static void main(String[] args) {
		
		MyTable myTable = new MyTable();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//printTableThreadSafe method will be a synchronized
				myTable.printTableThreadSafeV2(5);
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				myTable.printTableThreadSafeV2(10);
			}
		});
		
		//start threads
		t1.start();
		t2.start();
		
		
	}
	

}
