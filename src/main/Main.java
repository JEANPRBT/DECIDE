package main;

import java.awt.geom.Point2D;

public final class Main {
    public static void main(String[] args) {

        // ------------------------------------ Define inputs ------------------------------------ //
        int numPoints = 5;
        Point2D[] points = new Point2D[]{
                new Point2D.Double(10, 5),
                new Point2D.Double(2, 6),
                new Point2D.Double(2, 3),
                new Point2D.Double(0, 1),
                new Point2D.Double(4,6)
        };
        Parameters parameters = Parameters.DEFAULT;
        LCM lcm = LCM.DEFAULT;
        PUV puv = PUV.DEFAULT;

        // ---------------------------- Compute and print the result ---------------------------- //
        boolean res = computeResult(numPoints, points, parameters, lcm, puv);
        if (res) System.out.println("YES");
        else System.out.println("NO");

    }

    public static boolean computeResult(int numPoints, Point2D[] points, Parameters parameters, LCM lcm, PUV puv){
        CMV cmv = new CMV(numPoints, points, parameters);
        PUM pum = new PUM(lcm, cmv);
        FUV fuv = new FUV(pum, puv);

        boolean[] vector = fuv.getVector();
        for (boolean elem : vector) {
            if (!elem) return false;
        }
        return true;
    }
}
