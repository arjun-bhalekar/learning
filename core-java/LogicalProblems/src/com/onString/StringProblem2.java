package com.onString;

/**
 * Reverse the given String by recursion method
 * @author arjun
 *
 */
public class StringProblem2 {
	
	public static void main(String[] args) {
		
		
		String inputString = "Hello";
		
		String outputString = reverseByRecursion(inputString);
			
		System.out.println(outputString);
	}
	
	
	static String reverseByRecursion(String inputString) {
		//System.out.println("inputString :"+inputString);
		if(inputString.isEmpty()) {
			//System.out.println("inputString is empty");
			return inputString;
		}else {
			return reverseByRecursion(inputString.substring(1)) + inputString.charAt(0);
		}
		
	}
	
	/**
	
	(ava)+J
	
	=> 	(va)+a+j
	
	 * 
	 */
	
	

}
