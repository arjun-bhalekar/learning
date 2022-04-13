package com.onString;

/**
 * Rotate(clock wise or anti clock wise) the Given String by X times.
 * 
 * @author arjun
 *
 */
public class StringProblem3 {

	public static void main(String[] args) {

		String inputString = "GeeksforGeeks";
		int r = 2; // no. roatations

		System.out.println(leftRotate(inputString, r)); //LLOHE
		
		System.out.println(rightRotate(inputString, r)); // LOHEL

	}

	public static String leftRotate(String inputString, int rotations) {

		String result = inputString;
		for (int j = 0; j < rotations; j++) {

			char[] strArr = result.toCharArray();
			char firstChar = strArr[0];
			for (int i = 1; i < strArr.length; i++) {

				strArr[i - 1] = strArr[i];
			}
			strArr[strArr.length - 1] = firstChar;
			result = String.valueOf(strArr);
		}
		return result;

	}
	//Hello 
	public static String rightRotate(String inputString, int rotations) {

		String result = inputString;
		for (int j = 0; j < rotations; j++) {

			char[] strArr = result.toCharArray();
			char lastChar = strArr[strArr.length-1];
			
			for (int i = strArr.length-2; i >=0; i--) {

				strArr[i+1] = strArr[i];
			}
			
			strArr[0] = lastChar;
			result = String.valueOf(strArr);
		}
		return result;

	}

}
