package projectEuler;

import java.math.BigInteger;

public class P097 extends Problem {
	@Override
	public long solve(boolean printResults){
		BigInteger numDigits = new BigInteger("10000000000");

		/*		This is a cool idea, but BigInteger.pow(int) runs in O(1) I believe, at least Math.pow(double) does. 
		 *		Surely, it's optimized in some form at least. I took a glance at the source, and it seemed like it did
		 *		the same thing, but who am I to say.

		BigInteger prime = BigInteger.ONE;
		 
		String bin = Integer.toBinaryString(7830457);
		int[] powers = findPowers(bin);

		BigInteger[] two_two_powers = new BigInteger[50];
		two_two_powers[0] = TWO;
		for(int i = 1; i < two_two_powers.length; i++)
			two_two_powers[i] = two_two_powers[i - 1].multiply(two_two_powers[i - 1]).remainder(remainder);

		for(int i : powers)
			prime = prime.multiply(two_two_powers[i]);

		*/
		BigInteger prime = BigInteger.TWO.pow(7830457);
		prime = prime.multiply(BigInteger.valueOf(28433));
		prime = prime.add(BigInteger.ONE);
		prime = prime.remainder(numDigits);

		if(printResults)
			System.out.println(prime + " are the last 10 digits of 28433×2^7830457+1");
		return prime.longValue();
	}

	/*
	// "00011101" => [4,3,2,0], that is 0%00011101 = 2^4 + 2^3 + 2^2 + 2^0
	private static int[] findPowers(String bits){
		int size = 0;
		for(char bit : bits.toCharArray())
			if(bit == '1')
				size++;

		int[] help = new int[size];

		for(int i = 0; i < bits.length(); i++)
			if(bits.charAt(i) == '1')
				help[--size] = bits.length() - i - 1;

		return help;
	}	
	*/

	@Override
	public String getTitle(){
		return "Problem 097: Large Non-Mersenne Prime";
	}
}
