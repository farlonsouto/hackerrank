package coding.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Anagrams {

	static int makingAnagrams(String s1, String s2) {

		HashMap<String, List<String>> s1_map = process(s1);
		HashMap<String, List<String>> s2_map = process(s2);

		int accDelete = 0;

		int sizeS1Aux, sizeS2Aux;

		for (String key : s1_map.keySet()) {
			if (!s2_map.containsKey(key)) {
				accDelete += s1_map.get(key).size();
			} else {
				sizeS1Aux = s1_map.get(key).size();
				sizeS2Aux = s2_map.get(key).size();
				accDelete += Math.abs(sizeS2Aux - sizeS1Aux);
			}
		}

		for (String key : s2_map.keySet()) {
			if (!s1_map.containsKey(key)) {
				accDelete += s2_map.get(key).size();
			} else {
				// not necessary. Already evaluated.
			}
		}

		return accDelete;
	}

	private static HashMap<String, List<String>> process(String word) {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		char[] charArray = word.toCharArray();
		String key;
		List<String> auxList;
		for (char c : charArray) {
			key = String.valueOf(c);
			if (result.containsKey(key)) {
				result.get(key).add(key);
			} else {
				auxList = new ArrayList<String>();
				auxList.add(key);
				result.put(key, auxList);
			}
		}
		return result;
	}

	public static void main(String[] args) {
	/*	Scanner in = new Scanner(System.in);
		String s1 = in.next();
		String s2 = in.next();
		int result = makingAnagrams(s1, s2);
		System.out.println(result);
	*/
		//timeConversion("07:05:45PM");
		
		stringSimilarity("ababaa");
	}

	static void miniMaxSum(int[] arr) {

		boolean firstSet = true;
		long min = 0;
		long max = 0;
		int acc = 0;
		int step = 0;
		while (step < arr.length) {
			for (int index = 0; index < arr.length; index++) {
				if (step != index) {
					acc += arr[index];
				}
			}
			if(firstSet) {
				firstSet = false;
				min = acc;
			} else if (min > acc) {
				min = acc;
			}
			if(acc > max) {
				max = acc;
			}
			acc = 0;
			step++;
		}
		System.out.println(min+" "+max);
	}
	
	
	static int birthdayCakeCandles(int n, int[] ar) {
		int acc = 0;
		int maxHeight = 0;
		for(int value: ar) {
			if(value > maxHeight) {
				maxHeight = value;
				acc = 1;
			}
			if(value == maxHeight) {
				acc++;
			}
		}
		return acc;
    }
	
    static String timeConversion(String s) {
    	String result = "";
    	boolean isPM = s.indexOf("PM")>0;
    	result = s.substring(0, s.length()-2); // remove PM or AM
    	int hour = Integer.parseInt(result.substring(0, result.indexOf(":"))); // read hour as integer
    	if(isPM) {
    		hour += 12; // corrects to 24h format
    	} else {
    		if(hour == 12) {
    			hour = 0;
    		}
    	}
    	result = String.format("%02d", hour)+ result.substring(result.indexOf(":"), result.length());
    	
    	return result;
    }
    
    static int stringSimilarity(String s) {
    	int sum = 0;
    	int offSet = 0;
    	int sufixSize = s.length();
        char[] word = s.toCharArray();
        while(offSet < s.length()) {
        	sufixSize = s.length() - offSet;
        	for(int i = 0; i< sufixSize; i++) {
    			System.out.println("["+word[i]+"] x ["+word[i+offSet]+"]");        			
        		if(word[i] == word[i+offSet]) {
        			sum++;
        		}
        	}
        	System.out.println("------------");
        	offSet++;
        }
        return sum;
    }    
	

}
