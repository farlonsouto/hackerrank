package coding.problem;

import java.util.Scanner;

class Serie {

	public static void main(String[] argh) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			// reads query parameter
			int a = in.nextInt();
			int b = in.nextInt();
			int n = in.nextInt();

			// processes query
			printSerie(a, b, n, 0, 0);
		}
		in.close();

	}

	/**
	 * Recursively prints. Come on. It's not rocket science.
	 */
	private static void printSerie(int a, int b, int n, long previous, int polynomeIndex) {
		// return condition: recursion must not dive into polynomeIndex == n
		if (polynomeIndex < n) {
			long termValue = (long)(Math.pow(2, polynomeIndex))* b;
			if (polynomeIndex == 0) {
				// this could be managed differently by passing a as previous value, which would
				// decrease readability. Readability is ALWAYS better.
				termValue += a;
			}
			termValue += previous;

			if (polynomeIndex > 0) {
				System.out.print(",");
			}
			System.out.print(termValue);
			// recursively calls with pre-incremented polynomeIndex
			printSerie(a, b, n, termValue, ++polynomeIndex);
		} else {
			System.out.println();
		}
	}
}
