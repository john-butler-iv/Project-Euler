package projectEuler;

import java.util.ArrayList;
import java.util.function.Function;

public class P061 extends Problem{
	private enum Shape {
		triangle,
		square,
		pentagon,
		hexagon,
		heptagon,
		octagon
	}
	private class ShapedNumber{
        int number;
        Shape type;
        public ShapedNumber(int number, Shape type){
            this.number = number;
            this.type = type;
        }

		@Override
		public String toString(){
			return String.valueOf(number);
		}
    }


	@Override
	public long solve(boolean printResults){
    	// I can make this slightly more efficient if I find the index of 1000, 
		// then round up and start from there so I don't have to iterate up until that point.
        ArrayList<ShapedNumber> numbers = new ArrayList<>();
		numbers.addAll(getList(n -> new ShapedNumber(EulerTools.triangle(n), Shape.triangle)));
		numbers.addAll(getList(n -> new ShapedNumber(n*n, Shape.square)));
		numbers.addAll(getList(n -> new ShapedNumber(EulerTools.pentagon(n), Shape.pentagon)));
		numbers.addAll(getList(n -> new ShapedNumber(EulerTools.hexagon(n), Shape.hexagon)));
		numbers.addAll(getList(n -> new ShapedNumber(EulerTools.heptagon(n), Shape.heptagon)));
		numbers.addAll(getList(n -> new ShapedNumber(EulerTools.octagon(n), Shape.octagon)));
        


        for(int a = 0; a < numbers.size(); a++){
            ShapedNumber A = numbers.get(a);

            for(int b = 0; b < numbers.size(); b++){
                ShapedNumber B = numbers.get(b);
                if(invalidType(A, B) || invalidSequence(A, B))
                    continue;

                for(int c = 0; c < numbers.size(); c++) {
                    ShapedNumber C = numbers.get(c);
                    if(invalidType(A, C) || invalidType(B, C) || invalidSequence(B, C))
                        continue;

                    for(int d = 0; d < numbers.size(); d++){
                        ShapedNumber D = numbers.get(d);
                        if(invalidType(A, D) || invalidType(B, D) || invalidType(C, D) || 
								invalidSequence(C, D))
                            continue;

                        for(int e = 0 ; e < numbers.size(); e++){
                            ShapedNumber E = numbers.get(e);
                            if(invalidType(A, E) || invalidType(B , E) || invalidType(C, E) || 
									invalidType(D, E) || invalidSequence(D, E))
                                continue;

                            for(int f = 0; f < numbers.size(); f++){
                                ShapedNumber F = numbers.get(f);
                                if(invalidType(A, F) || invalidType(B, F) || invalidType(C, F) || 
										invalidType(D, F) || invalidType(E, F) || invalidSequence(E, F) ||
									   	invalidSequence(F, A))
                                    continue;

                                int sum = A.number + B.number + C.number + D.number + E.number + F.number;

								if(printResults)
									System.out.println(A + ", " + B + ", " + C + ", " + D + ", " + E + ", " + F
											+ " is the only set of six cyclic 4-digit numbers that have each " 
											+ "polygon type. Their sum is " + sum);
                                return sum;
                            }
                        }
                    }
                }
            }
        }
		if(printResults)
			System.out.println("Could not find a valid set");

		return -1;
    }

    private static boolean invalidType(ShapedNumber a, ShapedNumber b){
        return (a.type.equals(b.type));
    }

    private static boolean invalidSequence(ShapedNumber a, ShapedNumber b){
        return (a.number % 100 != b.number / 100);
    }

	private ArrayList<ShapedNumber> getList(Function<Integer, ShapedNumber> func){
		ArrayList<ShapedNumber> list = new ArrayList<ShapedNumber>();

		int index = 1;
		ShapedNumber elem = func.apply(index);

		while(elem.number < 10000){
			if(elem.number >= 1000)
				list.add(elem);
			
			elem = func.apply(++index);
		}

		return list;
	}
	
	@Override
	public String getTitle(){
		return "Problem 061: Cyclical Figurate Numbers";
	}
}
