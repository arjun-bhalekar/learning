package com;

//consider class A for which we want create only one instance
class A {

	private A() {
		System.out.println("A instnace created");
	}

	private static A a;

	static A getInstance() {

		if (a == null) {
			synchronized (A.class) {
				if(a==null) {
					a = new A();
				}
			}
		}
		return a;
	}

}

public class Demo1 {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			public void run() {
				A a = A.getInstance();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				A a = A.getInstance();
			}
		});

		t1.start();
		t2.start();

	}
}
