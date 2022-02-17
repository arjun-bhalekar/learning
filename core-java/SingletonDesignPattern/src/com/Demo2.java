package com;

//from 1.5 onwords, we can implement singleton design pattern by enum also
enum B {
	
	instance;
	
	B(){
		System.out.println("B instance created");
	}
	
}



public class Demo2 {

	public static void main(String[] args) {
		
		B b1 = B.instance;
		B b2 = B.instance;
		
		
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				B b1 = B.instance;
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				B b2 = B.instance;
			}
		});

		t1.start();
		t2.start();
		
	}
}
