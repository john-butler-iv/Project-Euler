package projectEuler;

import java.io.File;
import java.util.Scanner;

public class P102 extends ParameterizedProblem<File> {
	private static final double EPSILON = 0.0001;
	@Override
	public File getDefaultParameter() {
		return new File("p102.txt");
	}

	@Override
	public long solve(File triangles, boolean printResults){
		try{
			Scanner input = new Scanner(triangles);
			String in;
			int contains = 0;
			int total = 0;
			while (input.hasNext()) {
				in = input.next();
				if (in.contains(",")) {
					++total;
					// read point coordinates
					double ax = Double.valueOf(in.substring(0, in.indexOf(',')));
					in = in.substring(in.indexOf(',') + 1);
					double ay = Double.valueOf(in.substring(0, in.indexOf(',')));
					in = in.substring(in.indexOf(',') + 1);
					double bx = Double.valueOf(in.substring(0, in.indexOf(',')));
					in = in.substring(in.indexOf(',') + 1);
					double by = Double.valueOf(in.substring(0, in.indexOf(',')));
					in = in.substring(in.indexOf(',') + 1);
					double cx = Double.valueOf(in.substring(0, in.indexOf(',')));
					in = in.substring(in.indexOf(',') + 1);
					double cy = Double.valueOf(in);

					// find the sum of the angles between the origin and each side of the triangle
					double angleSum = EulerTools.findAngle(ax, ay, 0.0, 0.0, bx, by);
					angleSum += EulerTools.findAngle(ax, ay, 0.0, 0.0, cx, cy);
					angleSum += EulerTools.findAngle(bx, by, 0.0, 0.0, cx, cy);

					// if the origin, is in the triangle, the sides surround the point, so the angle sum is 2pi
					// otherwise, the sum will be less
					// I can't just use == because of floating point errors
					if(Math.abs(angleSum - 2*Math.PI) < EPSILON)
						++contains;
				} else 
					System.out.println("\"" + in + "\"");

			}

			if(printResults)
				System.out.println(contains + " out of " + total + " triangles contain the origin.");
			return contains;
		} catch(Exception e) {
			System.out.println("Invalid file " + triangles.getPath());
			return 0;
		}
	}

	@Override
	public File getTestParameter(){
		return new File("p102test.txt");
	}

	@Override
	public long getTestSolution(){
		return 1;
	}

	@Override
	public String getTitle(){
		return "Problem 102: Triangle Containment";
	}
}
