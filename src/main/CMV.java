package main;

import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * @author Rickard Cornell, Elissa Arias Sosa, Raahitya Botta, Zaina Ramadan, Jean Perbet
 * Class representing a Conditions Met Vector (main.CMV), composed of fifteen elements corresponding
 * to one Launch Interception Condition (LIC) each.
 */
public final class CMV {
    private boolean[] cmv;
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
        setLIC10();
        setLIC14();
    }

    /**
     * Getter for CMV.
     * @return the boolean array of all LICs representing the CMV.
     */
    public boolean[] getVector() {
        return cmv;
    }

    /**
     * Setter for CMV, making it easier for testing purposes.
     * @param vector: the boolean vector to set
     */
    public void setVector(boolean[] vector){
        assert(vector.length == 15);
        this.cmv = vector;
    }

    /**
     * Getter for the number of true conditions in the CMV.
     * @return the number of true conditions out of 15 in the CMV.
     */
    public int getTrueConditions(){
        int count = 0;
        for (boolean b : cmv){
            if (b) count++;
        }
        return count;
    }

    /**
     * Setter for the LIC n°3.
     * "There exists at least one set of three consecutive data points that are the vertices of a triangle
     * with area greater than AREA1.
     * (0 ≤ AREA1)"
     */
    private void setLIC3(){
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
    private void setLIC4(){
        cmv[4] = false;
        int Q_POINTS = parameters.q_pts, QUADS = parameters.quads;
        for (int i = 0; i < numPoints - Q_POINTS; i++) {
            int[] quadrants = new int[4];
            for (int j = 0; j < Q_POINTS; j++) {
                Point2D p = points[i + j];
                int quadrantIndex = (p.getX() >= 0 ? 0 : 2) + (p.getY() >= 0 ? 0 : 1);
                quadrants[quadrantIndex] = 1;
            }
            int sum = Arrays.stream(quadrants).sum();
            if (sum >= QUADS) {
                cmv[4] = true;
                break;
            }
        }
    }

    /**
     * Setter for the LIC n°5.
     * "There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[i + 1],Y[i + 1]), such
     * that X[j] - X[i] < 0."
     */
    private void setLIC5(){
        cmv[5] = false;
        for (int i = 0; i < numPoints - 1; i++){
            Point2D p1 = points[i], p2 = points[i + 1];
            if (p2.getX() - p1.getX() < 0) {
                cmv[5] = true;
                break;
            }
        }
    }
   /*  There exists at least one set of three data points separated by exactly E PTS and F PTS consecutive intervening points, 
     respectively, that are the vertices of a triangle with area greater than AREA1. 
    The condition is not met when NUMPOINTS < 5.
    1≤E PTS,1≤F PTS
    E PTS+F PTS ≤ NUMPOINTS−3 */

/* ß */
    private void setLIC10(){
        cmv[10] = false;
        if(numPoints < 5){
            return;
        }
        else{
            for(int i = 0; i < numPoints - (parameters.e_pts + 1) - (parameters.f_pts + 1); i++){
                Point2D p1 = this.points[i], p2 = this.points[i + parameters.e_pts + 1], p3 = this.points[i + parameters.e_pts + 1 + parameters.f_pts + 1];
                double AREA = 0.5 * Math.abs(
                        (p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY()) -
                        (p1.getX() * p3.getY() + p2.getX() * p1.getY() + p3.getX() * p2.getY())
                );
                
                if (AREA > this.parameters.area1) {
                    this.cmv[10] = true;
                    break;
                }
            }


        }
    }
    /*
     * 4. There exists at least one set of three data points, separated by exactly E PTS and F PTS consecutive intervening points,  
     * respectively, that are the vertices of a triangle with area greater than AREA1. In addition, there exist three data points
     * (which can be the same or different from the three data points just mentioned) separated by exactly E PTS and F PTS 
     * consecutive intervening points, respectively, that are the vertices of a triangle with area less than AREA2. Both parts 
     * must be true for the LIC to be true. The condition is not met when NUMPOINTS < 5.
     * 0 ≤ AREA2
     */
    public void setLIC14() {
        this.cmv[14] = false;
        if (numPoints < 5) {
            return; 
        }
        boolean c_1 = false;
        boolean c_2 = false;
        for (int i = 0; i <= numPoints - 3; i++) {
            int eIndex = i + 1 + this.parameters.e_pts;
            int fIndex = eIndex + 1 + this.parameters.f_pts;
            if (fIndex < numPoints) {
                Point2D p1 = this.points[i];
                Point2D p2 = this.points[eIndex];
                Point2D p3 = this.points[fIndex];
                double triangleArea = AreaOfTriangle(p1, p2, p3);
                if (triangleArea > this.parameters.area1) {
                    c_1 = true;
                }
                if (triangleArea < this.parameters.area2) {
                    c_2 = true;
                }
                if (c_1 && c_2) {
                    this.cmv[14] = true;
                    break;
                }
            }
        }
    }

    private double AreaOfTriangle(Point2D p1, Point2D p2, Point2D p3) {
        double a = p1.distance(p2);
        double b = p2.distance(p3);
        double c = p3.distance(p1);
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c)); 
    }
}