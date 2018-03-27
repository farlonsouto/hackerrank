package coding.problem;

import java.util.Scanner;

public class DiagonalDifference {

	static int diagonalDifference(int[][] a) {
		int n = a[0].length;
		int main = 0;
		int secondary = 0;
		
		
		int j = n;
		int i = 0;
		while(i< n) {
			main += a[i][i];			
			j--;
			secondary += a[i][j];
			i++;
		}
		
		if(main >= secondary) {
			return Math.abs(main - secondary);			
		} else {
			return Math.abs(secondary - main);						
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] a = new int[n][n];
		for (int a_i = 0; a_i < n; a_i++) {
			for (int a_j = 0; a_j < n; a_j++) {
				a[a_i][a_j] = in.nextInt();
			}
		}
		int result = diagonalDifference(a);
		System.out.println(result);
		in.close();
	}
}
