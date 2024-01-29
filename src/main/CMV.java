package main;

import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * @author Rickard Cornell, Elissa Arias Sosa, Raahitya Botta, Zaina Ramadan, Jean Perbet
 * Class representing a Conditions Met Vector (main.CMV), composed of fifteen elements corresponding
 * to one Launch Interception Condition (LIC) each.
 */
public final class CMV {
    private final boolean[] cmv;
    private final int numPoints;
    private final Point2D[] points;
    private final Parameters parameters;

    /**
     * Public constructor for a main.CMV.
     */
    public CMV(int numPoints, Point2D[] points, Parameters parameters){
        this.cmv = new boolean[15];
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;

        setLIC3();
        setLIC4();
        setLIC5();
        setLIC12();
        setLIC13();
    }

    /**
     * Setter for the LIC n°3.
     * "There exists at least one set of three consecutive data points that are the vertices of a triangle
     * with area greater than AREA1.
     * (0 ≤ AREA1)"
     */
    public void setLIC3(){
        this.cmv[3] = false;
        for (int i = 0; i < numPoints - 2; i++){
            Point2D p1 = this.points[i], p2 = this.points[i + 1], p3 = this.points[i + 2];
            double AREA = 0.5 * Math.abs(
                    (p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY()) -
                    (p1.getX() * p3.getY() + p2.getX() * p1.getY() + p3.getX() * p2.getY())
            );
            if (AREA > this.parameters.area1) {
                this.cmv[3] = true;
                break;
            }
        }
    }

    /**
     * Setter for the LIC n°4.
     * "There exists at least one set of Q PTS consecutive data points that lie in more than QUADS quadrants.
     *  Where there is ambiguity as to which quadrant contains a given point, priority of decision will be by
     *  quadrant number, i.e., I, II, III, IV. For example, the data point (0,0) is in quadrant I, the point (-l,0)
     *  is in quadrant II, the point (0,-l) is in quadrant III, the point (0,1) is in quadrant I and the point (1,0)
     *  is in quadrant I.
     * (2 ≤ Q_PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)"
     */
    public void setLIC4(){
        this.cmv[4] = false;
        int Q_POINTS = this.parameters.q_pts, QUADS = this.parameters.quads;
        for (int i = 0; i < numPoints - Q_POINTS; i++) {
            int[] quadrants = new int[4];
            for (int j = 0; j < Q_POINTS; j++) {
                Point2D p = this.points[i + j];
                int quadrantIndex = (p.getX() >= 0 ? 0 : 2) + (p.getY() >= 0 ? 0 : 1);
                quadrants[quadrantIndex] = 1;
            }
            int sum = Arrays.stream(quadrants).sum();
            if (sum >= QUADS) {
                this.cmv[4] = true;
                break;
            }
        }
    }

    /**
     * Setter for the LIC n°5.
     * "There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[i + 1],Y[i + 1]), such
     * that X[j] - X[i] < 0."
     */
    public void setLIC5(){
        this.cmv[5] = false;
        for (int i = 0; i < numPoints - 1; i++){
            Point2D p1 = this.points[i], p2 = this.points[i + 1];
            if (p2.getX() - p1.getX() < 0) {
                this.cmv[5] = true;
                break;
            }
        }
    }

    /**
     * Setter for the LIC n°12.
     * There exists at least one set of two data points, separated by exactly K PTS consecutive intervening points, which are a distance greater than the length, LENGTH1, apart.
     * In addi- tion, there exists at least one set of two data points (which can be the same or different from the two data points just mentioned), separated by exactly K PTS consecutive intervening points, that are a distance less than the length, LENGTH2, apart.
     * Both parts must be true for the LIC to be true. The condition is not met when NUMPOINTS < 3.
     * 0 ≤ LENGTH2
     */
    public void setLIC12(){
        boolean c_1 = false, c_2= false;  //c_1/2= conditions 
        int k_pts = this.parameters.k_pts;
        if (numPoints < k_pts + 2){
            return;
        }
        for (int i = 0; i< numPoints - k_pts - 1; i++){
            Point2D p1 = this.points[i], p2 = this.points[i + k_pts + 1];
            double distance = p1.distance(p2);
            if (distance > this.parameters.length1) {
                c_1 = true;
            }
            if (distance < this.parameters.length2) {
                c_2 = true;
            }
            if (c_1 && c_2){
                this.cmv[12] = true;
                break;
            }
        }
    }

    /*
     * Setter for the LIC n°13. 
     * There exists at least one set of three data points, separated by exactly
     *  A PTS and B PTS consecutive intervening points, respectively, that cannot be contained within or 
     * on a circle of radius RADIUS1. In addition, there exists at least one set of three data points 
     * (which can be the same or different from the three data points just mentioned) separated by exactly 
     * A PTS and B PTS consecutive intervening points, respectively, that can be contained in or on a circle of 
     * radius RADIUS2. Both parts must be true for the LIC to be true. The condition is not met when NUMPOINTS < 5.
     * 0 ≤ RADIUS2
     */
    
    public void setLIC13(){
        this.cmv[13]= false;
        if (numPoints<5){ //condition is not met
            return;
        }
        for(int i=0; i <= numPoints-3; i++){
            int a_index = i + 1 + this.parameters.a_pts;
            int b_index = a_index + 1 + this.parameters.b_pts;

            if (b_index < numPoints){
                Point2D  pointA = this.points[i];
                Point2D  pointB = this.points[a_index];
                Point2D  pointC = this.points[b_index];
                boolean c1 = !pointsInCircle(pointA,pointB,pointC, this.parameters.radius1);
                boolean c2 = pointsInCircle(pointA,pointB,pointC,this.parameters.radius2);
                if (c1 && c2){
                    this.cmv[13] = true;
                    break;
                }
            }
        }   
    }
    private boolean pointsInCircle(Point2D pointA, Point2D pointB,  Point2D pointC, double radius){
        double l_a = pointA.distance(pointB); // lengths of sides of triangle
        double l_b =  pointB.distance(pointC);
        double l_c = pointC.distance(pointA);
        double s = (l_a+l_b+l_c)/2;
        if (l_a + l_b == l_c || l_a + l_c == l_b || l_b + l_c == l_a) {
            return false; // Collinear points cannot form a circumcircle with finite radius
        }
        double area = Math.sqrt((s - l_a)*(s - l_b)*(s - l_c)); // area of  triangle
        double circum_radius= (l_a*l_b*l_c)/(4*area);
        return circum_radius <= radius; // checks if c_r is less than or equal to given radius
    }
    /**
     * Getter for CMV.
     * @return the boolean array of all LICs representing the CMV.
     */
    public boolean[] getCMV() {
        return cmv;
    }

    /**
     * Getter for the number of true conditions in the CMV.
     * @return the number of true conditions out of 15 in the CMV.
     */
    public int getTrueConditions(){
        int count = 0;
        for (boolean b : this.cmv){
            if (b) count++;
        }
        return count;
    }
}