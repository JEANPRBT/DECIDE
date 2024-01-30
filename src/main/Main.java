
package main;
import java.awt.geom.Point2D;



public final class Main {

    static int numPoints = 3;
    static Point2D[] points = new Point2D[]{
            new Point2D.Double(10, 5),
            new Point2D.Double(2, 6),
            new Point2D.Double(2, 3),
            new Point2D.Double(0, 1),
            new Point2D.Double(4,6)
    };
    static Parameters parameters = Parameters.DEFAULT;
  
    // add other relevant parameters 
    public static void main(String[] args) {
        CMV cmv = new CMV(numPoints, points, parameters);
        LCM lcm = LCM.DEFAULT;
        PUM pum = new PUM(lcm,cmv);
        PUV puv = PUV.DEFAULT;
        FUV fuv = new FUV(pum, puv);
        new Decide(fuv);

    }


}
