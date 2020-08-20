package projectEuler;

public class P085 extends ParameterizedProblem<Integer>{
	public Integer getDefaultParameter(){
		return 2000000;
	}

	public long solve(Integer goalRects, boolean printResults){
		int bestError = goalRects;
		int bestArea = 0;

		// we can caluclate the required width based on the height of the rectangle
		// we only go to sqrt(goalRects) because we want height < width
		for(int height = 1; height < Math.sqrt(goalRects); height++){

			// calculate the required width to get the right amout of rectangles (as mentioned before)
			double temp = 16*goalRects / (height * (height + 1));
			int width = (int) ((Math.sqrt(temp + 1)) / 2);
			// technically, we need to check the floor and ceiling, since the ideal width is guarunteed not to be
			// rational, or more importantly an integer, so we round to the nearest integer


			// calculate how many sub-rectangles fit in the grid
			int currRects = height * (height + 1) / 2;
			currRects *= width * (width + 1) / 2;

			// fingure out how close we were to our goal
			int error = Math.abs(currRects - goalRects);
			if(error < bestError) {
				bestError = error;
				bestArea = height * width;
			}
		}
		if(printResults)
			System.out.println(bestArea + " is the area of the rectangular grid that contains the closest to "
					+ goalRects + " rectangles (" + bestError + " off)");
		return bestArea;
	}

	public Integer getTestParameter(){
		return 18;
	}

	public long getTestSolution(){
		return 6;
	}

	public String getTitle(){
		return "Problem 085: Counting Rectangles";
	}
}
