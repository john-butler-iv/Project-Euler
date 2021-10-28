package projectEuler;

public class P246 extends Problem {
	// given constants
	// I'm translating so that the center of the ellipse is the origin, which is
	// easier for me.
	private static final double Mx = -5000.0;
	private static final double My = 0.0;
	private static final double Gx = 5000.0;
	private static final double Gy = 0.0;
	private static final double r = 15000.0;

	// calculated constants
	// distance from M to G
	private static final double MG = EulerTools.distForm(Mx, My, Gx, Gy);
	// distance from each focus to the edge of the ellipse
	private static final double del = Math.abs(r - MG) / 2.0;
	// semi-axes of the ellipse
	private static final double a = del + MG / 2.0;
	private static final double b = Math.sqrt(del * (del + MG));
	// edges of the ellipse
	private static final double LEFT_ELLIPSE_EDGE_X = Mx + del;
	private static final double RIGHT_ELLIPSE_EDGE_X = Gx + del;
	private static final double TOP_ELLIPSE_EDGE_Y = b;

	// smallest bounding box for the shape in the integers
	private final int TOP_SHAPE_EDGE = 18950;
	private final int RIGHT_SHAPE_EDGE = 15440;

	public static void main(String[] args) {
		new P246().solve(true);
	}

	@Override
	public long solve(boolean printResults) {
		long sum = 0;
		int y;
		int right = 0;

		for (y = TOP_SHAPE_EDGE - 1; y > TOP_ELLIPSE_EDGE_Y; y--) {
			// at x=7500, the tangent is vertical and my algorithm breaks
			if (y == 16854) {
				right = 7500;
			} else {
				right = findRightEdge(y, right);
			}
			sum += 2 * right + 1;
		}

		// only look for ellipse's edge when we must
		for (; y > 0; y--) {
			int left = (int) findRightEllipse(y);
			right = findRightEdge(y, right);
			sum += 2 * (right - left);
		}

		// y=0 does not have an opposite pair, so we must calculate this after doubling
		sum *= 2;
		int left = (int) findRightEllipse(y);
		right = findRightEdge(y, right);
		sum += 2 * (right - left);

		if (printResults) {
			System.out.println("There are " + sum
					+ " latice points whose lines of tangency with e form an angle greater than 45 degrees.");
		}
		return sum;
	}

	/**
	 * Find the positive y-value of the ellipse at x
	 * 
	 * @param x the input
	 * @return the point on the upper half of the ellipse at x
	 */
	private double top(double x) {
		return b * Math.sqrt(1 - x * x / (a * a));
	}

	/**
	 * Find the negative y-value of the ellipse at x
	 * 
	 * @param x the input
	 * @return the point on the lower half of the ellipse at x
	 */
	private double bottom(double x) {
		return -b * Math.sqrt(1 - x * x / (a * a));
	}

	/**
	 * finds the positive x-value of the ellipse at y
	 * 
	 * @param y
	 * @return the point on the right half of the ellipse at y
	 */
	private double findRightEllipse(double y) {
		return a * Math.sqrt(1 - y * y / (b * b));
	}

	/**
	 * Finds the greatest lattice point inside the shpae we're searching for the
	 * given y value. Runs in O(log(rightbound - leftbound)). Because shape is
	 * monotonically decreasing, you can feed the previous result in for left bound
	 * for greater efficiency.
	 * 
	 * @param y         the y-value we're searching along
	 * @param leftbound the smallest possible value our result could be
	 * @return the greatest x value such that (x,y) is in the shape
	 */
	private int findRightEdge(double y, int leftbound) {
		int min = leftbound;
		int max = RIGHT_SHAPE_EDGE;

		// if min +1 = max, then we get stuck, so break early and check after
		while (min + 1 < max) {
			int mid = (max + min) / 2;
			if (inShape(mid, y)) {
				min = mid;
			} else {
				max = mid - 1;
			}
		}

		return inShape(max, y) ? max : min;
	}

	/**
	 * determines if a given point is inside the shape we're finding lattice points
	 * in
	 * 
	 * @param x the x coordinate of a point outside of the ellipse
	 * @param y the y coordinate of a point inside the ellipse
	 * @return true if (x,y) is inside the shape; false otherwise
	 */
	private boolean inShape(double x, double y) {
		return angle(x, y) > (Math.PI / 4.0);
	}

	/**
	 * determines the angle, in radians, between the lines of tnagency to the
	 * ellipse for the given point
	 * 
	 * @param x a point outside the ellipse
	 * @param y a point outside the ellipse
	 * @return the angle between the lines of tangency to the ellpise for the given
	 *         point
	 */
	private double angle(double x, double y) {
		double x1 = x1(x, y);
		double x2 = x2(x, y);
		if (x <= LEFT_ELLIPSE_EDGE_X) {
			double m2 = (top(x1) - y) / (x1 - x);
			double m3 = (bottom(x2) - y) / (x2 - x);
			return Math.atan(m2) - Math.atan(m3);
		}
		if (x >= RIGHT_ELLIPSE_EDGE_X) {
			double m1 = (top(x2) - y) / (x2 - x);
			double m4 = (bottom(x1) - y) / (x1 - x);
			return Math.atan(m4) - Math.atan(m1);
		}
		if (y > 0) {
			double m1 = (top(x2) - y) / (x2 - x);
			double m2 = (top(x1) - y) / (x1 - x);
			return Math.PI - (Math.atan(m1) - Math.atan(m2));
		}
		double m3 = (bottom(x2) - y) / (x2 - x);
		double m4 = (bottom(x1) - y) / (x1 - x);
		return Math.PI - (Math.atan(m3) - Math.atan(m4));
	}

	/**
	 * finds the x value of the rightmost point of tangency on the ellipse
	 * 
	 * @param x the x coordinate of a point
	 * @param y the y coordinate of a point
	 * @return the x value of the rightmost point of tangency on the ellipse
	 */
	private double x1(double x, double y) {
		double a2 = a * a;
		double b2 = b * b;
		return ((a2 * b2 * x) + (a2 * y) * Math.sqrt(a2 * y * y - a2 * b2 + b2 * x * x)) / (a2 * y * y + b2 * x * x);
	}

	/**
	 * finds the x value of the leftmost point of tangency on the ellipse
	 * 
	 * @param x the x coordinate of a point
	 * @param y the y coordinate of a point
	 * @return the x value of the leftmost point of tangency on the ellipse
	 */
	private double x2(double x, double y) {
		double a2 = a * a;
		double b2 = b * b;
		return ((a2 * b2 * x) - (a2 * y) * Math.sqrt(a2 * y * y - a2 * b2 + b2 * x * x)) / (a2 * y * y + b2 * x * x);
	}

	@Override
	public String getTitle() {
		return "Problem 246: Tangents to an Ellipse";
	}

}