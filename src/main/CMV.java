package main;

import java.awt.geom.Line2D;
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
        setLIC6();
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
     * Setter for LIC n°6
     * "There exists at least one set of N PTS consecutive data points such that at least one of the points lies a 
     * distance greater than DIST from the line joining the first and last of these N PTS points. If the first and 
     * last points of these N PTS are identical, then the calculated distance to compare with DIST will be the distance 
     * from the coincident point to all other points of the N PTS consecutive points. The condition is not met when NUMPOINTS < 3.
     * (3 ≤ N PTS ≤ NUMPOINTS), (0 ≤ DIST)"
     */
    public void setLIC6() {
        this.cmv[6] = false;

        if (this.parameters.dist < 0 || this.parameters.n_pts < 3 || this.parameters.n_pts > numPoints) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numPoints - this.parameters.n_pts; i++) {
            Point2D first = this.points[i], last = this.points[i + this.parameters.n_pts];
            if (first.equals(last)) {
                for (int j = i+1; j < i + this.parameters.n_pts-1; j++) {
                    Point2D point = this.points[j];
                    if (first.distance(point) > this.parameters.dist) {
                        this.cmv[6] = true;
                        return;
                    }
                }
            }
            else {
                Line2D line = new Line2D.Double();
                line.setLine(first, last);
                for (int j = i+1; j < i + this.parameters.n_pts-1; j++) {
                    Point2D point = this.points[j];
                    if (line.ptLineDist(point) > this.parameters.dist) {
                        this.cmv[6] = true;
                        return;
                    }
                }
            }
        }
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