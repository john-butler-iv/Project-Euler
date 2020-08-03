package projectEuler;

import java.util.ArrayList;

public class P144alt {
	private static class Point {
		double x, y;
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}
		public void setX(double x){
			this.x = x;
		}
		public double getX(){
			return x;
		}
		public void setY(double y){
			this.y = y;
		}
		public double getY(){
			return y;
		}
	}
	private static class Line {
		double m, b;

		public Line(double m, double b){
			this.m = m;
			this.b = b;
		}
		public Line(double m, Point p){
			this.m = m;
			this.b = p.y - m * p.x;
		}

		public double getM(){
			return m;
		}
		public double getB(){
			return b;
		}

		public boolean equals(Line l){
			return l.m == this.m && this.b == l.b;
		}
	}
	//problem 144
	public static final Line STARTING_LINE= new Line((-197.0/14.0), 10.1);
	//ellipse equation: 4x² + y² = 100
	//					y = ± v(-4x² + 100)
	//starting line: y = -197/14 * x + 10.1
	//Derivative of the ellipse: -8x / [±2 * v(-4x² + 100)]
	
	private static double upperEllipse(double x) {
		return Math.sqrt(-4 * Math.pow(x, 2) + 100);
	}
	private static double lowerEllipse(double x) {
		return -1 * upperEllipse(x);
	}
	public static double getEllipse(Point p) {
		if(p.getY() > 0)
			return upperEllipse(p.getX());
		return lowerEllipse(p.getX());
	}
	
	public static Line getTangentLine(Point p) {
		return new Line(-4.0 * p.getX() / p.getY(), p);
	}
	
	public static Line reflectLine(Line incidence, Point p) {
		Line tangent = getTangentLine(p);
		double mRefl = Math.tan(2 * Math.atan(tangent.getM()) + Math.PI - Math.atan(incidence.getM()));
		
		return new Line(mRefl, p);
	}
	/**
	 * @param line
	 * @param direction true if the line is pointing up and to the left
	 * @return
	 */
	public static Point intersectsEllipseAt(Line line, boolean direction) {
		double a = (4 + line.getM()*line.getM());
		double b = (2 * line.getB() * line.getM());
		double c = (line.getB()*line.getB() - 100);
		double x1 = (-1*b + Math.sqrt(b*b - 4*a*c)) / (2.0 * a);
		double x2 = (-1*b - Math.sqrt(b*b - 4*a*c)) / (2.0 * a);
		double bigX = Math.max(x1, x2);
		double smallX = Math.min(x1, x2);
		
		Point point= new Point(0,0);
		
		if(direction) {
			point.setX(smallX);
			double y = line.getM() * smallX + line.getB();
			point.setY(y);
		}
		else {
			point.setX(bigX);
			double y = line.getM() * bigX + line.getB();
			point.setY(y);
		}
		return point;
	}
	public static void main(String[] args) {//354 should be the answer
		Line line = STARTING_LINE;
		int hits = 0;
		ArrayList<Line> lines = new ArrayList<Line>();
		/*
		 * false - down/right
		 * true  - up/left
		 */
		boolean direction = false;
		while(hits < 10){//!exits(intersectsEllipseAt(line, direction))) {
			line = reflectLine(line, intersectsEllipseAt(line, direction));
			System.out.println(line.m + " x + " + line.b);
			direction = !direction;
			++hits;
			lines.add(line);
			if(isWeird(lines, line, direction))
				direction = !direction;
		}
		System.out.println("it takes " + hits + " reflections for the beam to exit.");
    }
	private static boolean isWeird(ArrayList<Line> lines, Line line, boolean direction) {
		Line next = reflectLine(line, intersectsEllipseAt(line, direction));
		if(contains(lines, next)) {
			return true;
		}
		//if next one is already contained
		return false;
	}
	private static boolean contains(ArrayList<Line>lines , Line line) {
		for(Line l : lines) {
			if(line.equals(l))
				return true;
		}
		return false;
	}
	private static boolean exits(Point point) {
		return   point.getY() >  0 && 
				(point.getX() <= 0.01 && point.getX() >= -0.01);
	}
	
}
