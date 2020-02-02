import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * File: Driver_lab1.java
 * Version: 1.0
 * Due 2/2/2020
 * Course: MSCS 630
 * Assignment: Lab 1
 * @author Paul Ippolito
 * This file contains the Driver1_lab.java
 * file. It takes in a String given by the
 * user and converts all letters of the String
 * to ints, a-z will be 0-25, spaces 26.h
 */
public class Driver_lab1 {
	

	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		
		while (input.hasNext()) {
			
			// message converted to lower case as to not cause
			// errors
			String message = input.nextLine();
			message = message.toLowerCase();
			
			// new array that will be encoded text
			int[] encoded = str2int(message);
			
			// print all of the elements of the encoded
			// array
			for(int i = 0; i < encoded.length; i++) {
			  System.out.print(encoded[i] + " ");
			}
			System.out.println();
		}
		
		input.close();

	}
	
	/**
	 * This method takes the given String and converts each letter
	 * and space to a number. A-Z is 0-25, spaces are mapped to 26
	 * After conversion, the new message is sent number by number
	 * into an array and then displayed.
	 * Example: "hello" becomes 7 4 11 11 14
	 * "Cryptography is fun" becomes
	 * 2 17 24 15 19 14 6 17 0 15 7 24 26 8 18 26 5 20 13
	 * @param plainText
	 */
	public static int[] str2int(String plainText) {
	  
	  int[] resultArray = new int[plainText.length()];
		
		/*
		 * This Hash map maps the characters a-z as 0-25
		 * and spaces as 26
		 */
		final Map<Character, Integer> myMap;
		
		myMap = new HashMap<>();
		
		myMap.put('a', 0);
		myMap.put('b', 1);
		myMap.put('c', 2);
		myMap.put('d', 3);
		myMap.put('e', 4);
		myMap.put('f', 5);
		myMap.put('g', 6);
		myMap.put('h', 7);
		myMap.put('i', 8);
		myMap.put('j', 9);
		myMap.put('k', 10);
		myMap.put('l', 11);
		myMap.put('m', 12);
		myMap.put('n', 13);
		myMap.put('o', 14);
		myMap.put('p', 15);
		myMap.put('q', 16);
		myMap.put('r', 17);
		myMap.put('s', 18);
		myMap.put('t', 19);
		myMap.put('u', 20);
		myMap.put('v', 21);
		myMap.put('w', 22);
		myMap.put('x', 23);
		myMap.put('y', 24);
		myMap.put('z', 25);
		myMap.put(' ', 26);
		
		for(int i = 0; i < plainText.length(); i++) {
			
			final Integer value;
			// get the integer value based on the given
			// character
			value = myMap.get(plainText.charAt(i));
			
			resultArray[i] = value;
			
			
		}
		
		// return the resulting array to main
		return resultArray;
		
	}

}
