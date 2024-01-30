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
        setLIC6();
        setLIC7();
        setLIC8();
        setLIC9();
        setLIC10();
        setLIC14();
        setLIC11();
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

           boolean res = circleContain(p1, p2, p3);
           if (res == false){
            this.cmv[1] = true;
            break;
           }
           
            

        }
    }
    public boolean circleContain(Point2D p1, Point2D p2,Point2D p3){
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
     * Getter for CMV.
     * @return the boolean array of all LICs representing the CMV.
     */
    public boolean[] getCMV() {
        return cmv;
    }
  
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
     * Setter for LIC n°7
     * "There exists at least one set of two data points separated by exactly K PTS consecutive in- tervening points 
     * that are a distance greater than the length, LENGTH1, apart. The condition is not met when NUMPOINTS < 3.
     * 1 ≤ K PTS ≤ (NUMPOINTS − 2)"
     */
    private void setLIC7() {
        cmv[7] = false;

        if (1 > this.parameters.k_pts || this.parameters.k_pts > (numPoints - 2) || numPoints < 3) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numPoints - this.parameters.k_pts - 1; i++) {
            Point2D p1 = points[i], p2 = points[i + this.parameters.k_pts + 1];
            if (p1.distance(p2) > this.parameters.length1) {
                cmv[7] = true;
                return;
            }
        }
      }

  
     /**
     * Setter for LIC n°8
     * "There exists at least one set of three data points separated by exactly A PTS and B PTS consecutive 
     * intervening points, respectively, that cannot be contained within or on a circle of radius RADIUS1. 
     * The condition is not met when NUMPOINTS < 5.
     * 1≤A PTS,1≤B PTS
     * A PTS+B PTS ≤ (NUMPOINTS−3)"
     */
    private void setLIC8(){
        cmv[8] = false;

        if (1 > this.parameters.a_pts || this.parameters.a_pts > this.parameters.b_pts || 1 > this.parameters.b_pts || (this.parameters.a_pts + this.parameters.b_pts) > (numPoints - 3)) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numPoints - this.parameters.a_pts - this.parameters.b_pts - 2; i++) {
            Point2D p1 = points[i], p2 = points[i + this.parameters.a_pts + 1], p3 = points[i + this.parameters.a_pts + this.parameters.b_pts + 2];
            if (!circleContain(p1, p2, p3)) {
                cmv[8] = true;
                return;
            }
        }

    /* There exists at least one set of three data points separated by exactly C PTS and D PTS 
    consecutive intervening points, respectively, that form an angle such that: 
    angle < (PI−EPSILON) or angle > (PI+EPSILON)
    The second point of the set of three points is always the vertex of the angle. 
    If either the first point or the last point (or both) coincide with the vertex, 
    the angle is undefined and the LIC is not satisfied by those three points. 
    When NUMPOINTS < 5, the condition is not met.
    1 ≤ C PTS, 1 ≤ D PTS
    C PTS+D PTS ≤ NUMPOINTS−3 */
    private void setLIC9(){
        cmv[9] = false;
        if(numPoints < 5){
            return;
        }
        else{
              for(int i = 0; i < numPoints - (parameters.c_pts + 1) - (parameters.d_pts + 1); i++){

                Point2D p1 = this.points[i], p2 = this.points[i + parameters.c_pts + 1], p3 = this.points[i + parameters.c_pts + 1 + parameters.d_pts + 1];
                Point2D p1p2 = new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                Point2D p2p3 = new Point2D.Double(p2.getX() - p3.getX(), p2.getY() - p3.getY());

                if((p1.getX() == p2.getX() && p1.getY() == p2.getY()) || (p3.getX() == p2.getX() && p3.getY() == p2.getY())){
                    continue;
                }
                else{
                    double magp1p2 = Math.sqrt((Math.pow(p1p2.getX(), 2) + Math.pow(p1p2.getY(), 2)));
                    double magp2p3 = Math.sqrt((Math.pow(p2p3.getX(), 2) + Math.pow(p2p3.getY(), 2)));

                    double dotProduct = p1p2.getX() * p2p3.getX() + p1p2.getY() * p2p3.getY();

                    double angleInRadians = Math.acos(dotProduct / (magp1p2 * magp2p3));

                    if(angleInRadians < Math.PI - parameters.epsilon || angleInRadians > Math.PI + parameters.epsilon){
                        cmv[9] = true;
                        break;
                    }
                 }

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
    /* There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
    exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
    condition is not met when NUMPOINTS < 3.
        1 ≤ G PTS ≤ NUMPOINTS−2 */

    /**
     * Setter for LIC11 
     */
    public void setLIC11(){
        this.cmv[11] = false;
        // The condition is not met when NUMPOINTS < 3.
        if(this.numPoints < 3){
            return;
        }
        else{
            for(int i = 0; i < numPoints - (parameters.g_pts + 1); i++){
                Point2D p1 = this.points[i], p2 = this.points[i+parameters.g_pts+1];
                if(p1.getX() > p2.getX()){
                    this.cmv[11] = true;
                    break;
                }
            }
            return;
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