package coding.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class NonDecreasing {

	/**
	 * Each position coordinate and it's corresponding value.
	 * 
	 * @author farlonsouto@gmail.com
	 *
	 */
	protected static class Position {
		public Position(int i, int j, int value) {
			super();
			this.i = i;
			this.j = j;
			this.value = value;
		}

		public int i = -1;
		public int j = -1;
		public int value = -1;
	}

	/**
	 * A given sequence and it's size
	 * 
	 * @author farlonsouto@gmail.com
	 *
	 */
	protected static class Sequence {

		protected ArrayList<Position> positions = null;
		private int size = -1;

		/**
		 * Calculates the size of this sequence.
		 * 
		 * @return the size of this sequence, being 0 when position array is null or
		 *         empty
		 */
		public int getSize() {
			// returns 0 when position is null
			if (positions == null) {
				return 0;
			}
			if (size < 0) { // run once and saves the value
				size = 0; // initialize
				for (Position p : positions) {
					size += p.value; // accumulates
				}
			}
			return size;
		}
	}

	private static int numRows = 0;
	private static int numCols = 0;

	private static int[][] grid = null;

	// Globally accumulates results regardless of palindromes
	private static ArrayList<Sequence> globalSequences = new ArrayList<Sequence>();

	/**
	 * Take a rectangular grid of numbers and find the length of the longest
	 * sub-sequence.
	 * 
	 * @return the length as an integer.
	 */
	public static int longestSequence(int[][] grid) {

		Position position = null;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) { // for each grid[i][j]
				// record its position
				position = new Position(i, j, grid[i][j]);
				// initialize cannot repeat list with current position
				buildSubSequences(position, new ArrayList<Position>(), new Sequence());
			}
		}
		return largestSequenceSize();
	}

	/**
	 * 
	 * @return
	 */
	private static int largestSequenceSize() {
		int aux = 0;
		for (Sequence sequence : globalSequences) {
			if (sequence.size >= aux) {
				aux = sequence.getSize();
			}
		}
		return aux;
	}

	/**
	 * 
	 * @param currentPosition
	 * @param cannotRepeat
	 * @param sequenceSoFar
	 */
	private static void buildSubSequences(Position currentPosition, ArrayList<Position> cannotRepeat,
			Sequence sequenceSoFar) {

		boolean isEndOfPath = true;

		// adds current position to the sequence
		if (sequenceSoFar.positions == null) {
			sequenceSoFar.positions = new ArrayList<Position>();
		}
		sequenceSoFar.positions.add(currentPosition);

		// explore neighborhood
		for (int i = currentPosition.i - 1; i <= currentPosition.i + 1; i++) {
			for (int j = currentPosition.j - 1; j <= currentPosition.j + 1; j++) {
				if (isValidPath(currentPosition, i, j, cannotRepeat)) {
					// add current Position to the cannot repeat list
					cannotRepeat.add(currentPosition);

					// advances to the next position ...
					Position newPosition = new Position(i, j, grid[i][j]);

					// ... preserving the sequence built so far.
					Sequence currentSequence = copyOf(sequenceSoFar);

					// adds the next valid position to the sequence
					currentSequence.positions.add(newPosition);
					buildSubSequences(newPosition, cannotRepeat, currentSequence);
					isEndOfPath = false;
				}
			}
		}

		if (isEndOfPath) { // when current position is an end of path, nested loops had run but not gone
							// into any recursion. Then, saves the sequences obtained so far
			globalSequences.add(sequenceSoFar);
		}
	}

	private static boolean isValidPath(Position currentPosition, int i, int j, ArrayList<Position> cannotRepeat) {

		if (i >= 0 && j >= 0 && i < numRows && j < numCols) { // only the valid positions around
			if (i != currentPosition.i || j != currentPosition.j) { // ignores current position
				if (!isRepeated(i, j, cannotRepeat)) { // avoid candidates repeated
					if (Math.abs(currentPosition.value - grid[i][j]) > 3) { // difference grater than 3
						return true;
					}
				}
			}
		}
		return false;
	}

	private static Sequence copyOf(Sequence sequenceSoFar) {
		Sequence copy = new Sequence();
		copy.positions = new ArrayList<Position>();
		for (Position p : sequenceSoFar.positions) {
			copy.positions.add(new Position(p.i, p.j, p.value));
		}
		return copy;
	}

	private static boolean isRepeated(int i, int j, ArrayList<Position> cannotRepeat) {
		for (Position p : cannotRepeat) {
			if (p.i == i && p.j == j) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String[] firstLine = reader.readLine().split("\\s+");
		numRows = Integer.parseInt(firstLine[0]);
		numCols = Integer.parseInt(firstLine[1]);

		grid = new int[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
			String[] inputRow = reader.readLine().split("\\s+");
			for (int col = 0; col < numCols; col++) {
				grid[row][col] = Integer.parseInt(inputRow[col]);
			}
		}
		int length = longestSequence(grid);
		System.out.println(length);
	}
}