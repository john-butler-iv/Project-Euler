package projectEuler;

public class P144 extends Problem {
	private class Point {
		double x;
		double y;
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	private class Line {
		double m;
		double b;

		// slope intersept
		public Line(double m, double b){
			this.m = m;
			this.b = b;
		}

		// point slope formula
		public Line(double m, Point p){
			this.m = m;
			this.b = p.y - m * p.x;
		}
	}

	private Line reflectLine(Line incident, Point p){
		Line tangent = new Line(-4.0 * p.x / p.y, p);
		
		// see writeup for explaination of equation
		double mRefl = Math.tan(2*Math.atan(tangent.m) - Math.atan(incident.m));

		return new Line(mRefl, p);
	}

	private Point intersectsEllipseAt(Line line, Point lastIntersection){
		// see writeup for explaination of equation
		double a = 4 + line.m*line.m;
		double b = 2 * line.b * line.m;
		double c = line.b*line.b - 100;

		double plusX = (-b + Math.sqrt(b*b - 4*a*c)) / (2.0 * a);
		double minusX = (-b - Math.sqrt(b*b - 4*a*c)) / (2.0 * a);
		
		// there are two intersection points, and we don't want the one we already know about
		if(EulerTools.epsilonEquals(lastIntersection.x, plusX))
			return new Point(
				minusX,
				line.m * minusX + line.b
			);
		
		return new Point(
			plusX,
			line.m * plusX + line.b
		);
	}

	private final Line STARTING_LINE = new Line((-197.0/14.0), 10.1);

	@Override
	public long solve(boolean printResults){
		Line line = STARTING_LINE;
		// my program counts the exiting line as a hit, so I account for that here.
		int hits = -1;	
		Point intersectionPoint = new Point(0.0071, 10);

		do {
			intersectionPoint = intersectsEllipseAt(line, intersectionPoint);
			line = reflectLine(line, intersectionPoint);
			hits++;
		}while(!exits(intersectionPoint)) ;

		if(printResults)
			System.out.println("It takes " + hits + " reflections for the beam to exit.");
		return hits;
	}

	private boolean exits(Point p){
		return p.y > 0 && EulerTools.epsilonEquals(p.x, 0.0, 0.01);
	}

	@Override
	public String getTitle(){
		return "Problem 144: Investigating Multiple Reflections of a Laser Beam";
	}
}
