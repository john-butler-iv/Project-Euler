package projectEuler;

import java.math.BigInteger;

public class BigRationalNumber implements Comparable<BigRationalNumber> {

	public static BigRationalNumber ZERO = new BigRationalNumber(BigInteger.ZERO);
	public static BigRationalNumber HALF = new BigRationalNumber(BigInteger.ONE, BigInteger.TWO);
	public static BigRationalNumber ONE = new BigRationalNumber(BigInteger.ONE);
	public static BigRationalNumber TWO = new BigRationalNumber(BigInteger.TWO);
	public static BigRationalNumber TEN = new BigRationalNumber(BigInteger.TEN);

	private BigInteger numerator;
	private BigInteger denominator;

	/**
	 * Creates a BigRationalNumber from the integer, so "numerator / 1"
	 * @param numerator the numerator of the BigRationalNumber to be created
	 */
	public BigRationalNumber(BigInteger numerator) {
		this(numerator, BigInteger.ONE);
	}

	/** 
	 * Creates a BigRationalNumber
	 * 
	 * @param numerator the numerator of the BigRationalNumber to be created
	 * @param denominator the denominator of the BigRationalNumber to be created. May not be zero.
	 */
	public BigRationalNumber(BigInteger numerator, BigInteger denominator) {

		if (denominator.equals(BigInteger.ZERO))
			throw new NumberFormatException(
					"Tried to create a BigRationalNumber from " + numerator.toString() + " / 0");

		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Computes and returns this + val. Returns a reduced result.
	 * 
	 * @param val BigRationalNumber to be added to this
	 * @return a reduced version of this + val 
	 */
	public BigRationalNumber add(BigRationalNumber val) {
		BigInteger thisScaledNumerator = this.numerator.multiply(val.denominator);
		BigInteger valScaledNumarator = val.numerator.multiply(this.numerator);
		BigInteger sharedDenominator = this.denominator.multiply(val.denominator);

		BigRationalNumber summation = new BigRationalNumber(thisScaledNumerator.add(valScaledNumarator),
				sharedDenominator);

		return summation;
	}

	/**
	 * Computes and returns this - val. Returns a reduced result.
	 * 
	 * @param val BigRationalNumber to be subtracted from this.
	 * @return a reduced version of this - val. 
	 */
	public BigRationalNumber subtract(BigRationalNumber val) {
		BigInteger thisScaledNumerator = this.numerator.multiply(val.denominator);
		BigInteger valScaledNumarator = val.numerator.multiply(this.numerator);
		BigInteger sharedDenominator = this.denominator.multiply(val.denominator);

		BigRationalNumber summation = new BigRationalNumber(thisScaledNumerator.subtract(valScaledNumarator),
				sharedDenominator);

		return summation;
	}

	/**
	 * Computes and returns the reciprocal of this BigRationalNumber. So 
	 * new BigRationalNumber(1, 2).reciprocal() is 2 / 1.
	 * 
	 * @return the reciprocal of this BigRationalNumber
	 */
	public BigRationalNumber reciprocal() {
		// no reducing necessary because numerator/denominator is already in a 
		// reduced state if desired.
		return new BigRationalNumber(denominator, numerator);
	}

	/**
	 * Computes and returns the square of this BigRationalNumber. So
	 * new BigRationalNumber(2, 3).square() is 4 / 9.
	 * 
	 * @return the square of this BigRationalNumber
	 */
	public BigRationalNumber square() {
		// no reducing necessary because numerator/denominator is already in a 
		// reduced state, if desired, so they don't share any factors.
		return this.multiply(this);
	}

	/**
	 * Finds the value of this * val, and reduces the result. 
	 * 
	 * @param val BigRationalNumber to multiply
	 * @return reduced version of this * val
	 */
	public BigRationalNumber multiply(BigRationalNumber val) {
		return new BigRationalNumber(this.numerator.multiply(val.numerator),
				this.denominator.multiply(val.denominator));
	}

	/**
	 * Returns an integer representing the sign of this number.
	 * 
	 * @return -1 if this is negative, 0 if this is 0, or 1 if this is positive
	 */
	public int signum() {
		return numerator.signum() * denominator.signum();
	}

	/**
	 * computes the relationship between this and val, so 
	 * this.compareTo(val) ? 0 <=> this ? val, where you replace "?" for <, >, =.
	 * 
	 * @param val BigRationalNumber to compare against
	 * @return a negative value if this < val, 0 if this = val, or a positive value if this > val
	 */
	@Override
	public int compareTo(BigRationalNumber val) {
		return this.subtract(val).signum();
	}

	public String toString() {
		return this.numerator + " / " + this.denominator;
	}
}