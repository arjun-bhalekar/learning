package com.multithreading.inter.thread;

public class InterThreadCommDemo {

	public static void main(String[] args) throws InterruptedException {
		
		
		BankAccount account = new BankAccount(500);
		
		
		Thread t1 = new Thread() {
			public void run() {
				account.withdraw(1000);
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				account.deposit(700);
			}
		};
		
		t1.start();
		
		Thread.sleep(2000);
		
		t2.start();
		
	}
	
}
