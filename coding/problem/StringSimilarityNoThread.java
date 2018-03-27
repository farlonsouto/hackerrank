package coding.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringSimilarityNoThread {
		
	private static class Interval {
		int begin = 0;
		int end = 0;
		char symbol = '0';
	}
	

	private static long stringSimilarity(String s) {
		
		long sum = 0;
		int offSet = 0;
		int sufixSize = s.length();
		char[] input = s.toCharArray();
		Interval deltaZeroInterval = findDeltaZeroInterval(input);
		
		while (offSet < s.length()) {
			sufixSize = s.length() - offSet;
			for (int i = accountUsingIntervals(sum, offSet, deltaZeroInterval); i < sufixSize; i++) {						
				if (input[i] == input[i + offSet]) {
					sum++;
				} else {
					break;
				}
			}
			offSet++;
		}
		return sum;
	}

	private static int accountUsingIntervals(long sum, int offSet, Interval interval) {
		int newIndex = 0;
		if(offSet <= interval.end) {
			sum += (interval.end-offSet);
			newIndex = interval.end-offSet;
		}
		return newIndex;
	}

	// no variance initial interval
	private static Interval findDeltaZeroInterval(char[] input) {
		List<Interval> deltaZero = new ArrayList<Interval>();
		Interval intervalAux = new Interval();;
		for(int i =1; i<input.length; i++) {
			if(input[i] != input[i-1]) {
					intervalAux.symbol = input[i]; 
					intervalAux.begin = 0;
					intervalAux.end = i-1;
					deltaZero.add(intervalAux);
			}
		}
		return intervalAux;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		long result = 0;
		for (int a0 = 0; a0 < t; a0++) {
			String s = in.next();
			result = stringSimilarity(s);
			System.out.println(result);
		}
		in.close();

	}
}
