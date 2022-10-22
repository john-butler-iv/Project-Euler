package projectEuler;

import java.util.Collections;
import java.util.PriorityQueue;

public class P247 extends ParameterizedProblem<Integer> {
	private class CurveRange implements Comparable<CurveRange> {
		double leftBound;
		double bottomBound;

		double squareX;
		double squareY;

		double size;

		int leftIndex;
		int bottomIndex;

		public CurveRange(double leftBound, double bottomBound) {
			this.leftBound = leftBound;
			this.bottomBound = bottomBound;

			this.findSquare();
		}

		private CurveRange(double leftBound, double bottomBound, int leftIndex, int bottomIndex) {
			this.leftBound = leftBound;
			this.bottomBound = bottomBound;

			this.findSquare();

			this.leftIndex = leftIndex;
			this.bottomIndex = bottomIndex;
		}

		/**
		 * Finds the laregest square in the region represented by this CurveRange. This
		 * will populate squareX, squareY and size as a side-effect.
		 */
		private void findSquare() {
			double shiftVar = bottomBound - leftBound;
			double numerator = Math.sqrt(shiftVar * shiftVar + 4) - shiftVar;

			squareX = numerator / 2.0;
			squareY = 2.0 / numerator;

			// Since bottomBound <= 1 / leftBound (because the bounds are under y=1/x and 1
			// <= a), the square will always be left-justified, which means the side length
			// is the distance from the left bound.
			this.size = squareX - leftBound;
		}

		/**
		 * creates a new CurveRange that represents the section above the largest square
		 * for this region.
		 * 
		 * @return A new CurveRange that represents the section above the largest square
		 *         for this region.
		 */
		public CurveRange leftRange() {
			return new CurveRange(leftBound, squareY, leftIndex, bottomIndex + 1);
		}

		/**
		 * creates a new CurveRange that represents the section to the right of the
		 * largest square for this region.
		 * 
		 * @return A new CurveRange that represents the section to the right of the
		 *         largest square for this region.
		 */
		public CurveRange rightRange() {
			return new CurveRange(squareX, bottomBound, leftIndex + 1, bottomIndex);
		}

		@Override
		public int compareTo(CurveRange otherRange) {
			return Double.compare(this.size, otherRange.size);
		}
	}

	@Override
	protected Integer getTestParameter() {
		return 1;
	}

	@Override
	protected long getTestSolution() {
		return 50;
	}

	@Override
	public Integer getDefaultParameter() {
		return 3;
	}

	@Override
	public long solve(Integer maxIndex, boolean printResults) {
		final CurveRange ROOT_RANGE = new CurveRange(1.0, 0.0);

		PriorityQueue<CurveRange> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.add(ROOT_RANGE);

		// We quickly run out of space if we store all pending subdivisions, so we only
		// store those that we know we will have to count.
		// this search runs in O(2^maxIndex), but maxIndex is 3 in the official problem,
		// so this isn't too bad
		double minSize = findMinSize(ROOT_RANGE, maxIndex, maxIndex);

		int ranking = 0;
		while (pq.peek() != null) {
			// grab the next biggest square
			CurveRange range = pq.poll();
			ranking++;

			// add the next newly created regions to the heap
			CurveRange leftRange = range.leftRange();
			CurveRange rightRange = range.rightRange();

			// only store ranges that are big enough to save space/time
			if (leftRange.size >= minSize)
				pq.add(range.leftRange());
			if (rightRange.size >= minSize)
				pq.add(range.rightRange());
		}

		if (printResults)
			System.out.println(ranking + " is the largest n that is an index of (" + maxIndex + ", "
					+ maxIndex + ").");

		return ranking;
	}

	private double findMinSize(CurveRange baseRange, int maxLeftIndex, int maxRightIndex) {
		if (maxLeftIndex == 0 && maxRightIndex == 0)
			return baseRange.size;
		else if (maxLeftIndex == 0) {
			return findMinSize(baseRange.rightRange(), maxLeftIndex, maxRightIndex - 1);
		} else if (maxRightIndex == 0) {
			return findMinSize(baseRange.leftRange(), maxLeftIndex - 1, maxRightIndex);
		}
		double minLeft = findMinSize(baseRange.leftRange(), maxLeftIndex - 1, maxRightIndex);
		double minRight = findMinSize(baseRange.rightRange(), maxLeftIndex, maxRightIndex - 1);
		return Math.min(minLeft, minRight);
	}

	@Override
	public String getTitle() {
		return "Problem 247: Squares under a Hyperbola";
	}
}
