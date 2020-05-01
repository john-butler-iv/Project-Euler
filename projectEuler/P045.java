package projectEuler;

public class P045 extends Problem {

	@Override
	public boolean test() {
		long t = EulerTools.triangleL(285);
		long p = EulerTools.pentagonL(165);
		long h = EulerTools.hexagonL(143);
		return t == p && p == h;
	}

	@Override
	long solve(boolean printResults) {
		// T285 = P165 = H144 was the last occurrence.
		int t = 286;
		int p = 166;
		int h = 145;
		long T = EulerTools.triangleL(t);
		long P = EulerTools.pentagonL(p);
		long H = EulerTools.hexagonL(h);

		while (H != P) {
			// find a T = P
			while (T != P) {
				while (T < P)
					T = EulerTools.triangleL(++t);
				while (P < T)
					P = EulerTools.pentagonL(++p);
			}
			// find a T = H
			while (T != H) {
				while (T < H)
					T = EulerTools.triangle(++t);
				while (H < T)
					H = EulerTools.hexagonL(++h);
			}
			// continue until H = P (= T)
		}

		if (printResults)
			System.out.println(T + " is the second triangular, pentagonal, and hexagonal number");
		return T;
	}

	@Override
	String getTitle() {
		return "Problem 045: Triangular, pentagonal, and hexagonal";
	}

}