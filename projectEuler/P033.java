package projectEuler;

class P033 extends Problem{
PrimeFinder pf;
    @Override
    long solve(boolean printResults) {
        int cnt = 0;

        pf = new PrimeFinder(100);
		int prodNum = 1;
		int prodDen = 1;

		for(int num = 10; num < 100; num++){
            System.out.println(num);
			for(int den = num+1; den < 100; den++){
                int[] fake = fakeReduce(num, den);
                
				if(fake[0] >= 10 || fake[0] == 0 || fake[1] == 0)
					continue;

                    System.out.println("\t" + den);
				int[] real = pf.reduce(num, den);

				if(real[0] / (double)real[1] == fake[0] / (double)fake[1]){
					cnt++;
					prodNum *= real[0];
					prodDen *= real[1];
					if(cnt == 4)
						break;
				}
			}
		}

        int[] frac = pf.reduce(prodNum, prodDen);
        if(printResults)
           System.out.println(frac[1]);
        return frac[1];
    }

	private static int[] fakeReduce(int n, int d){
		char[] num = String.valueOf(n).toCharArray();
		char[] den = String.valueOf(d).toCharArray();

        if(num[0] == '0' || num[1] == '0' || den[0] == '0' || den[1] == '1')
            return new int[]{n, d};

		if(num[0] == den[0]){
			return new int[]{num[1]-'0', den[1]-'0'};
		} else if(num[0] == den[1]){
			return new int[]{num[1]-'0', den[0]-'0'};
		} else if(num[1] == den[0]){
			return new int[]{num[0]-'0', den[1]-'0'};
		} else if(num[1] == den[1]){
			return new int[]{num[0]-'0', den[0]-'0'};
		}
		return new int[]{n, d};
	}

    @Override
    String getTitle() {
        return "Problem 033: Digit Cancelling Fractions";
    }
}