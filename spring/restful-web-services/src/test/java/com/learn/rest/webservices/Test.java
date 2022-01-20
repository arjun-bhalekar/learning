package com.learn.rest.webservices;

public class Test {
	
	
	public static void main(String[] args) {
		
		String s = new String("Arjun");
		
		s.concat("Bhalekar");
		
		System.out.println("s : "+s);
		
		
		StringBuffer sb = new StringBuffer("Arjun");
		
		sb.append("Bhalekar");
		
		System.out.println("sb : "+sb);
		
		
		StringBuilder sbr = new StringBuilder("Arjun");
		
		sbr.append("Bhalekar");
		
		System.out.println("sbr : "+sbr);
		
	}

}
