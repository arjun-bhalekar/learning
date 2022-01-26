package com.learn.multithreading;
public class Task extends Thread{
		
		private int taskNum;
		
		public Task(int taskNum) {
			this.taskNum = taskNum;
		}
		
		@Override
		public void run() {
			
			System.out.println("\nStarting Task :"+this.taskNum);
			for (int i = taskNum*100; i < taskNum*100+99; i++) {
				System.out.print(" "+i);
			}
			System.out.println("\nEnd of Task :"+this.taskNum);
		}
	}