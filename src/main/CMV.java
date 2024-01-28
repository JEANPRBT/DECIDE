package main;
import java.awt.geom.Point2D;
public class CMV {
  
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

        setLIC11();

    
    }
    /**
     * Getter for CMV.
     * @return the boolean array of all LICs representing the CMV.
     */
    public boolean[] getVector() {
        return cmv;
    }
    // condition one

    // condition two

    // condition three

    // condition four
    // ...

  
    // LIC 11
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
            for(int i = 0; i < numPoints - (parameters.G_PTS + 1); i++){
                System.out.println(i);
                Point2D p1 = this.points[i], p2 = this.points[i+parameters.G_PTS+1];
                if(p1.getX() > p2.getX()){
                    this.cmv[11] = true;
                    break;
                }
            }
            return;
        }
    }
}