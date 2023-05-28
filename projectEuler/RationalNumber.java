package projectEuler;

public class RationalNumber extends Number implements Comparable<RationalNumber> {

	public static RationalNumber ZERO = new RationalNumber(0);
	public static RationalNumber HALF = new RationalNumber(1, 2);
	public static RationalNumber ONE = new RationalNumber(1);
	public static RationalNumber TWO = new RationalNumber(2);
	public static RationalNumber TEN = new RationalNumber(10);

	private int numerator;
	private int denominator;

	/**
	 * Creates a RationalNumber from the integer, so "numerator / 1"
	 * @param numerator the numerator of the RationalNumber to be created
	 */
	public RationalNumber(int numerator) {
		this(numerator, 1, null);
	}

	/**
	 * Creates a RationalNumber, but does not reduce the value. So 
	 * RationalNumber(2, 4) will be stored as 2 / 4, not 1 / 2.
	 * 
	 * @param numerator the numerator of the RationalNumber to be created.
	 * @param denominator the denominator of the RationalNumber to be created.
	 */
	public RationalNumber(int numerator, int denominator) {
		this(numerator, denominator, null);
	}

	/** 
	 * Creates a RationalNumber, and reduces the result. So 
	 * RationalNumber(2, 4) will be stored as 1 / 2, not 2 / 4.
	 * 
	 * @param numerator the numerator of the RationalNumber to be created
	 * @param denominator the denominator of the RationalNumber to be created
	 * @param pf the PrimeFinder to use to simplify. Make sure that you've 
	 *           calculated up to and including the square root of the maximum 
	 *           of numerator or denominator.
	 */
	public RationalNumber(int numerator, int denominator, PrimeFinder pf) {

		if (denominator == 0)
			throw new NumberFormatException();

		this.numerator = numerator;
		this.denominator = denominator;

		if (pf != null) {
			int gcd = pf.gcd(this.numerator, this.denominator);
			this.numerator /= gcd;
			this.denominator /= gcd;
		}
	}

	/**
	 * Computes this + val
	 * 
	 * @param val RationalNumber to be added to this.
	 * @return this + val 
	 */
	public RationalNumber add(RationalNumber val) {
		return add(val, null);
	}

	/**
	 * Computes and returns this + val. Returns a reduced result.
	 * 
	 * @param val RationalNumber to be added to this
	 * @param pf PrimeFinder to use to potentially recude the result
	 * @return a reduced version of this + val 
	 */
	public RationalNumber add(RationalNumber val, PrimeFinder pf) {
		int thisScaledNumerator = this.numerator * val.denominator;
		int valScaledNumarator = val.numerator * this.numerator;
		int sharedDenominator = this.denominator * val.denominator;

		RationalNumber summation = new RationalNumber(thisScaledNumerator + valScaledNumarator, sharedDenominator, pf);

		return summation;
	}

	/**
	 * Computes and returns this - val, does not reduce the result
	 * 
	 * @param val RationalNumber to be subtracted from this.
	 * @return this - val
	 */
	public RationalNumber subtract(RationalNumber val) {
		return subtract(val, null);
	}

	/**
	 * Computes and returns this - val. Returns a reduced result.
	 * 
	 * @param val RationalNumber to be subtracted from this.
	 * @param pf PrimeFinder to use to potentially recude the result
	 * @return a reduced version of this - val. 
	 */
	public RationalNumber subtract(RationalNumber val, PrimeFinder pf) {
		int thisScaledNumerator = this.numerator * val.denominator;
		int valScaledNumarator = val.numerator * this.numerator;
		int sharedDenominator = this.denominator * val.denominator;

		RationalNumber summation = new RationalNumber(thisScaledNumerator - valScaledNumarator, sharedDenominator, pf);

		return summation;
	}

	/**
	 * Computes and returns the reciprocal of this RationalNumber. So 
	 * new RationalNumber(1, 2).reciprocal() is 2 / 1.
	 * 
	 * @return the reciprocal of this RationalNumber
	 */
	public RationalNumber reciprocal() {
		// no reducing necessary because numerator/denominator is already in a 
		// reduced state if desired.
		return new RationalNumber(denominator, numerator);
	}

	/**
	 * Computes and returns the square of this RationalNumber. So
	 * new RationalNumber(2, 3).square() is 4 / 9.
	 * 
	 * @return the square of this RationalNumber
	 */
	public RationalNumber square() {
		// no reducing necessary because numerator/denominator is already in a 
		// reduced state, if desired, so they don't share any factors.
		return this.multiply(this, null);
	}

	/**
	 * Finds the value of this * val. Does not reduce the result
	 * 
	 * @param val RationalNumber to multiply
	 * @return this * val
	 */
	public RationalNumber multiply(RationalNumber val) {
		return multiply(val, null);
	}

	/**
	 * Finds the value of this * val, and reduces the result. 
	 * 
	 * @param val RationalNumber to multiply
	 * @param pf the PrimeFinder used to reduce the result.
	 * @return reduced version of this * val
	 */
	public RationalNumber multiply(RationalNumber val, PrimeFinder pf) {
		return new RationalNumber(this.numerator * val.numerator, this.denominator * val.denominator, pf);
	}

	/**
	 * Returns an integer representing the sign of this number.
	 * 
	 * @return -1 if this is negative, 0 if this is 0, or 1 if this is positive
	 */
	public int signum() {
		return Integer.signum(numerator) * Integer.signum(denominator);
	}

	//#region implementing/overriding functions
	@Override
	public int intValue() {
		return numerator / denominator;
	}

	@Override
	public long longValue() {
		return numerator / denominator;
	}

	@Override
	public float floatValue() {
		return ((float) numerator) / ((float) denominator);
	}

	@Override
	public double doubleValue() {
		return ((double) numerator) / ((double) denominator);
	}

	/**
	 * computes the relationship between this and val, so 
	 * this.compareTo(val) ? 0 <=> this ? val, where you replace "?" for <, >, =.
	 * 
	 * @param val RationalNumber to compare against
	 * @return a negative value if this < val, 0 if this = val, or a positive value if this > val
	 */
	@Override
	public int compareTo(RationalNumber val) {
		return this.subtract(val).signum();
	}
	//#endregion implementing/overriding functions
}
