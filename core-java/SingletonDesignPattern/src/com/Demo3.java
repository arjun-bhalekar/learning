package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo3 {

	public static void main(String[] args) {

		MySingleton obj1 = MySingleton.getInstance();
		MySingleton obj2 = MySingleton.getInstance();

		System.out.println("obj1: " + obj1.hashCode());
		System.out.println("obj2: " + obj2.hashCode());

		// ----- ways to break singleton dp in java-------

		// 1.By Reflection

		Constructor<?>[] constructors = MySingleton.class.getDeclaredConstructors();

		for (Constructor<?> constructor : constructors) {
			constructor.setAccessible(true); // to get access to private constructor

			MySingleton obj3 = null;
			try {
				obj2 = (MySingleton) constructor.newInstance();
				// we can handle by - throw exception from constructor if instance is not null
				System.out.println("by reflection obj3 :" + obj3.hashCode());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		// 2. By Serialization process
		try {
			// serialize object
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\object.ser"));
			objectOutputStream.writeObject(obj1);
			objectOutputStream.close();

			// Deserialize object
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\object.ser"));
			MySingleton obj4 = (MySingleton) objectInputStream.readObject();
			// we can handle by adding readResolve() method in MySingleton class
			objectInputStream.close();

			System.out.println("by deserialization obj3 :" + obj4.hashCode());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 3. By cloning
		try {

			MySingleton obj5 = (MySingleton) obj1.clone();
			System.out.println("by clone() obj5 :" + obj5.hashCode());
			// we cam handle by throwing CloneNotSupportedException in clone method of
			// singleton;

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

}
