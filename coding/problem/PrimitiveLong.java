package coding.problem;

import java.util.Scanner;

public class PrimitiveLong {

	public static int Height, Bredth;

	static {
		Scanner sc = new Scanner(System.in);
		try {
			Height = sc.nextInt();
			Bredth = sc.nextInt();
			if (Height < 0 || Bredth < 0)
				System.out.println("java.lang.Exception: Breadth and height must be positive");
			else
				System.out.println(Height * Bredth);

		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public static void main(String[] argh) {
	}

}
