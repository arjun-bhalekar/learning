package com.onString;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Find the Repeated characters from given String.
 * 
 * @author arjun
 *
 */
public class StringProblem1 {

	public static void main(String[] args) {

		String input = "SolveLogicalProblems";
		
		System.out.println("------- solution 1 ---------");
		solution1(input);
		
		System.out.println("------- solution 2---------");
		solution2(input);
		
		
		
	}
	
	public static void solution1(String input) {
		
		// solution 1 - considering each char is case-insensitive

				char[] charArray = input.toUpperCase().toCharArray();

				Map<Character, Integer> map = new LinkedHashMap<>();
				// taken LinkedHashMap to preserve insertion order
				// HashMap can be user if we don't want order of insertion

				for (int i = 0; i < charArray.length; i++) {
					int count = 0;
					if (map.get(charArray[i]) != null) {
						count = map.get(charArray[i]);
					}

					map.put(charArray[i], count + 1);
				}
				
				//to print just repeated chars on console
				map.entrySet().stream()
						.filter(e -> e.getValue() > 1)
						.forEach(e -> System.out.println(e));
				
				//to create new Map of repeated chars
				Map<Character, Integer> outMap = new LinkedHashMap<>();
				
				for(Map.Entry<Character, Integer> entry : map.entrySet()) {
						if(entry.getValue() > 1)
							outMap.put(entry.getKey(), entry.getValue());
				}
				
				System.out.println("output in Map : "+outMap);
		
	}

	
public static void solution2(String input) {
		
		// solution 2 - considering each char is case-sensitive

				char[] charArray = input.toCharArray();

				Map<Character, Integer> map = new LinkedHashMap<>();
				// taken LinkedHashMap to preserve insertion order
				// HashMap can be user if we don't want order of insertion

				for (int i = 0; i < charArray.length; i++) {
					int count = 0;
					if (map.get(charArray[i]) != null) {
						count = map.get(charArray[i]);
					}

					map.put(charArray[i], count + 1);
				}
				
				//to print just repeated chars on console
				map.entrySet().stream()
						.filter(e -> e.getValue() > 1)
						.forEach(e -> System.out.println(e));
				
				//to create new Map of repeated chars
				Map<Character, Integer> outMap = new LinkedHashMap<>();
				
				for(Map.Entry<Character, Integer> entry : map.entrySet()) {
						if(entry.getValue() > 1)
							outMap.put(entry.getKey(), entry.getValue());
				}
				
				System.out.println("output in Map : "+outMap);
		
	}

}
