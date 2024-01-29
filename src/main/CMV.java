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
        setLIC0();
        setLIC1();
        setLIC2();
        setLIC3();
        setLIC4();
        setLIC5();
        setLIC10();
        setLIC12();
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
     * LIC 0  
     * There exists at least one set of two consecutive data points that are a distance greater than the length, LENGTH1, apart.
     * (0 ≤ LENGTH1)
     */
    public void setLIC0(){
        this.cmv[0] = false;
        double distance = 0;
        if(this.parameters.length1 < 0 ) throw new IllegalArgumentException();

        for(int i = 0; i < numPoints - 1; i++){
            Point2D p1 = this.points[i], p2 = this.points[i+1];
            distance =  Math.hypot(p1.getX()-p2.getX(), p1.getY()-p2.getY());
            if(distance > this.parameters.length1){
                this.cmv[0] = true;
                break;
            }
        }
    }
    /**
    * There exists at least one set of three consecutive data points that cannot all be contained within or on a circle of radius RADIUS1.
    * (0 ≤ RADIUS1)
    */
    public void setLIC1(){
        this.cmv[1] = false;
        for (int i = 0; i < numPoints - 2; i++){
            Point2D p1 = this.points[i], p2 = this.points[i + 1], p3 = this.points[i + 2];

           boolean res = LIC1Helper(p1, p2, p3);
           if (res == false){
            this.cmv[1] = true;
            break;
           }
           
            

        }
    }
    public boolean LIC1Helper(Point2D p1, Point2D p2,Point2D p3){
        double r = 0;
        double d12 = Math.hypot(p1.getX()-p2.getX(), p1.getY()-p2.getY());
        double d23 = Math.hypot(p2.getX()-p3.getX(), p2.getY()-p3.getY());
        double d13 = Math.hypot(p1.getX()-p3.getX(), p1.getY()-p3.getY());
        
        double crossProduct = (p2.getX() -p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY()-p1.getY()) * (p3.getX() - p1.getX());
        if (crossProduct == 0){
            if(d12 + d23 <= 2 * this.parameters.radius1 ||d23 + d13 <= 2 * this.parameters.radius1 || d12 + d13 <= 2 * this.parameters.radius1)
            return true;
        }
        if(d12 == 0 && d13 == 0 && d23 == 0) return true;
        
        r = (d12 * d23  * d13) / (Math.sqrt((d12 + d13 + d23) * (d12 + d13 - d23) * 
        (d13+d23 - d13) * (d12 + d23 - d13)));
        System.out.println(r);
        if (r <= this.parameters.radius1){
            return true;
        }
        return false;
    }
    /**
    * There exists at least one set of three consecutive data points which form an angle such that: angle < (PI − EPSILON)
    * or angle > (PI + EPSILON)
    * The second of the three consecutive points is always the vertex of the angle. If either the first point or the last point (or both) coincides with the vertex, 
    * the angle is undefined and the LIC is not satisfied by those three points.
    * (0 ≤ EPSILON < PI)
    */
    public void setLIC2() {
        this.cmv[2] = false;
        double EPSILON = this.parameters.epsilon;
        
        if (EPSILON < 0 || EPSILON >= Math.PI) {
            throw new IllegalArgumentException("Invalid EPSILON value");
        }
        
        for (int i = 0; i < numPoints - 2; i++) {
            Point2D p1 = this.points[i], p2 = this.points[i + 1], p3 = this.points[i + 2];

            // Skip if p1 or p3 coincides with p2
            if (p1.equals(p2) || p3.equals(p2)) {
                continue;
            }
            Point2D v1 =  new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
            Point2D v2 =  new Point2D.Double(p2.getX() - p3.getX(), p2.getY() - p3.getY());
            double v1Mag = Math.sqrt(v1.getX()*v1.getX() + v1.getY() * v1.getY());
            double v2Mag = Math.sqrt(v2.getX()*v2.getX() + v2.getY() * v2.getY());
            double angle = Math.acos((v1.getX() * v2.getX() + v1.getY() * v2.getY())/(v1Mag*v2Mag));
         
            if (angle > Math.PI + EPSILON || angle < Math.PI - EPSILON) {
                this.cmv[2] = true;
                break;
            }
        }
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

    /**
     * Setter for the LIC n°12.
     * There exists at least one set of two data points, separated by exactly K PTS consecutive intervening points, which are a distance greater than the length, LENGTH1, apart.
     * In addi- tion, there exists at least one set of two data points (which can be the same or different from the two data points just mentioned), separated by exactly K PTS consecutive intervening points, that are a distance less than the length, LENGTH2, apart.
     * Both parts must be true for the LIC to be true. The condition is not met when NUMPOINTS < 3.
     * 0 ≤ LENGTH2
     */
    public void setLIC12(){
        boolean c_1 = false, c_2= false;  /*c_1/2= conditions */
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
}