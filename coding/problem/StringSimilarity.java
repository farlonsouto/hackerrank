package coding.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class StringSimilarity implements Runnable, Future<Long> {

	private String baseString = null;

	private boolean isDone = false;

	private long sumOfSimilarities = 0;

	public StringSimilarity(String s) {
		baseString = s;
	}

	@Override
	public void run() {
		sumOfSimilarities = stringSimilarity(baseString);
		isDone = true;
	}

	public long getSumOfSimilarities() {
		return sumOfSimilarities;
	}

	long stringSimilarity(String s) {
		long sum = 0;
		int offSet = 0;
		int sufixSize = s.length();
		char[] word = s.toCharArray();
		while (offSet < s.length()) {
			sufixSize = s.length() - offSet;
			for (int i = 0; i < sufixSize; i++) {
				if (word[i] == word[i + offSet]) {
					sum++;
				} else {
					break;
				}
			}
			offSet++;
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		int numCores = Runtime.getRuntime().availableProcessors();
		ExecutorService execServ = Executors.newFixedThreadPool(numCores);
		List<StringSimilarity> listAcc = new ArrayList<StringSimilarity>();
		StringSimilarity similarity = null;
		// executor loop to add similarities computations
		for (int a0 = 0; a0 < t; a0++) {
			String s = in.next();
			similarity = new StringSimilarity(s);
			listAcc.add(similarity);
			execServ.execute(similarity);
		}
		in.close();
		execServ.shutdown();
		for (StringSimilarity computation : listAcc) {
			while(!computation.isDone()) {
				// wait each computation to finish. Respect input order
			}
			long result = computation.getSumOfSimilarities();
			System.out.println(result);
		}

	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long get() throws InterruptedException, ExecutionException {
		return this.getSumOfSimilarities();
	}

	@Override
	public Long get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		return this.isDone;
	}

}
