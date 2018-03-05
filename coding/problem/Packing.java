/**
 * 
 */
package coding.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author farlonsouto
 *
 */
public class Packing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static boolean binPacking(List<Integer> list) {
		if (list == null) {
			return true; // no list, nothing to worry: pack zero inside each pack. Easy.
		} else if (list.size() == 0) {
			return true; // empty list: pack zero inside each pack. Easy.
		}
		int totalSize = getTotalSize(list);
		float halfSize = totalSize / 2;
		// Sort from the bigger to the smaller
		Collections.sort(list, Collections.reverseOrder());
		// he first is the bigger one
		int biggerItem = list.get(0).intValue();
		if (biggerItem > halfSize) {
			// largest item is greater than half of the total
			return false;
		}
		if (biggerItem == halfSize) {
			// the first item matches exactly the half size of the total amount, then the
			// rest corresponds to the other half. Easy to pack.
			return true;
		}

		// How far we are from having exactly half of the weight/value? Let's call this
		// distance "gap"
		float gapToTheHalf = halfSize - biggerItem;

		// lets list only the wight/item/values smaller than the gap
		List<Integer> gapCandidates = getGapCandidates(list, gapToTheHalf);

		// maybe one of the candidates are match exactly the size of this gap
		for (Integer candidate : gapCandidates) {
			if (candidate.intValue() == gapToTheHalf) {
				// if it does, then we have found half of the values/wight/size, which means bin
				// packing is possible
				return true;
			}
		}

		// If we are here, it's time to explore the candidates in a polynomial way
		return buildMatchingGap(gapCandidates, gapToTheHalf);
	}

	/**
	 * The key to the polynomial solution consists on implementing an approach
	 * inside this method that avoids a factorial explosion.
	 * 
	 * @param gapCandidates
	 * @param gapToTheHalf
	 * @return true if a sum of the values of a giving subset of the gapCandidates
	 *         matches the size of gapToThe Half variable. False otherwise.
	 */

	private static boolean buildMatchingGap(List<Integer> gapCandidates, float gapToTheHalf) {

		float proportion = gapToTheHalf / gapCandidates.size();
		return false;
	}

	private static List<Integer> getGapCandidates(List<Integer> list, float gapToTheHalf) {
		List<Integer> gapCandidates = new ArrayList<Integer>();
		for (Integer item : list) {
			if (item <= gapToTheHalf) {
				gapCandidates.add(item);
			}
		}
		return gapCandidates;
	}

	private static int getTotalSize(List<Integer> list) {
		int totalSize = 0;
		for (Integer item : list) {
			totalSize += item.intValue();
		}
		return totalSize;
	}

}
