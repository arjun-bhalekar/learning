package com;

import java.io.Serializable;

public class MySingleton implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MySingleton instance;

	private MySingleton() {
		// prevent breaking of singleton by Reflection method
		if (instance != null) {
			throw new IllegalStateException("instance is already created");
		}
	}

	public synchronized static MySingleton getInstance() {

		if (instance == null)
			instance = new MySingleton();

		return instance;
	}
	
	//prevent breaking of singleton by de-serialization method
	public Object readResolve() {
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		//prevent breaking of singetone by clone() method
		throw new CloneNotSupportedException();
		
		// return super.clone();
	}
}
