package com.multithreading.synchronization;

public class ProblemWithoutSync {
	
	public static void main(String[] args) {
		
		MyTable myTable = new MyTable();
		
		//myTable.printTable(5);
		//myTable.printTable(10);
		
		Runnable r1 = () -> {
			myTable.printTable(5);
		};
		
		Runnable r2 = () -> {
			myTable.printTable(10);
		};
		
		//now start both threads
		new Thread(r1).start();
		new Thread(r2).start();
		
		//result is inconsistent	
		
	}

}
