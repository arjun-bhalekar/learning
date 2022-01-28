package com.multithreading.inter.thread;

public class BankAccount {
	
	int accountBalance;

	public BankAccount(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
	synchronized void withdraw(int amount) {
		System.out.println("Going to withdraw...");
		
		if(accountBalance<amount) {
			System.out.println("Less balance, waiting for deposit...");
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		accountBalance = accountBalance - amount;
		
		System.out.println("Withdraw completed. Current balance is :"+accountBalance);
	}
	
	synchronized void deposit(int amount) {
		
		System.out.println("Going to deposit...");
		
		accountBalance = accountBalance + amount;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		notify(); //OR  //notifyAll();
		
		System.out.println("Deposit completed. Current balance is :"+accountBalance);
		
	}
	
	
	

}
