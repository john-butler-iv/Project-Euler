abstract class P144 {

	private static Point intersectionPoint(Line line, Point previousIntersection);
	private static Line reflectLine(Line incidence, Point reflectionPoint);
	private boolean exits(Point intersectionPoint);

	public static void main(String[] args){
		/* * Initialize everything * */
		do{
			// result 2.2
			intersectionPoint = insersectsEllipseAt(line, intersectionPoint);
			// result 2.4
			line = reflectLine(line, intersectionPoint);
		} while(!exits(intersectionPoint));
	}
}
