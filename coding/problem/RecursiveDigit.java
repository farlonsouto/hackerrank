package coding.problem;

import java.util.Scanner;

public class RecursiveDigit {

	static int digitSum(String n, int k) {
		String aux = n;
		for (int i = 0; i < k - 1; i++) {
			aux += n;
		}
		return sum(aux);
	}

	private static int sum(String aux) {
		int acc = 0;
		if (aux.length() == 1) {
			acc = Integer.valueOf(aux);
		} else {
			for (int i = 0; i < aux.length(); i++) {
				acc += Integer.parseInt(String.valueOf(aux.charAt(i)));
			}
			acc = sum(String.valueOf(acc));
		}
		return acc;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String n = in.next();
		int k = in.nextInt();
		int result = digitSum(n, k);
		System.out.println(result);
		in.close();
	}
}
