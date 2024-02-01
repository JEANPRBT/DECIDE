import main.*;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.geom.Point2D;

public class MainTest {

    @Test
    public void NegativeDecideTest() {
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

        assertFalse(Main.computeResult(numPoints, points, parameters, lcm, puv));
    }

    @Test
    public void PositiveDecideTest() {
        int numPoints = 75;
        Point2D[] points = new Point2D[]{
            new Point2D.Double(0, 0), new Point2D.Double(0, 10),
            new Point2D.Double(1, 0), new Point2D.Double(2, 2),
            new Point2D.Double(1, 0), new Point2D.Double(0, 0),
            new Point2D.Double(6, 6), new Point2D.Double(-10, -10),
            new Point2D.Double(2, 3), new Point2D.Double(1, 1),
            new Point2D.Double(0, 0), new Point2D.Double(1, 0),
            new Point2D.Double(1, 1), new Point2D.Double(2, 0),
            new Point2D.Double(3, 0), new Point2D.Double(0, 0),                                           
            new Point2D.Double(0, 2), new Point2D.Double(2, 0),
            new Point2D.Double(0, 1), new Point2D.Double(1, 0),
            new Point2D.Double(1, 1), new Point2D.Double(-1, -1),
            new Point2D.Double(-1, 1), new Point2D.Double(0, 1),
            new Point2D.Double(1, 0), new Point2D.Double(1, 1),
            new Point2D.Double(-1, -1), new Point2D.Double(-1, 1),
            new Point2D.Double(0, 1), new Point2D.Double(1, 0),
            new Point2D.Double(0, 0), new Point2D.Double(0, 2),
            new Point2D.Double(2, 0), new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Point2D.Double(3, 0),
            new Point2D.Double(4, 5), new Point2D.Double(1, 1),
            new Point2D.Double(0, 0), new Point2D.Double(0, 1),
            new Point2D.Double(-2, -1), new Point2D.Double(1, 1),
            new Point2D.Double(3, 4), new Point2D.Double(0, 1),
            new Point2D.Double(2, 0), new Point2D.Double(0, 0),
            new Point2D.Double(4, 0), new Point2D.Double(1, 0),
            new Point2D.Double(0,0), new Point2D.Double(1,1),
            new Point2D.Double(3,10), new Point2D.Double(15, 30),
            new Point2D.Double(400,40), new Point2D.Double(5, 0),
            new Point2D.Double(0, 2), new Point2D.Double(2, 0),
            new Point2D.Double(2, 12), new Point2D.Double(2, 12),
            new Point2D.Double(0, 0), new Point2D.Double(0.5, 0), 
            new Point2D.Double(3,0), new Point2D.Double(1, 0),
            new Point2D.Double(2, 0), new Point2D.Double(0, 0), 
            new Point2D.Double(2, 0), new Point2D.Double(4, 0), 
            new Point2D.Double(1, 1), new Point2D.Double(3, 1), 
            new Point2D.Double(0, 0), new Point2D.Double(1, 0), 
            new Point2D.Double(0, 1), new Point2D.Double(1, 1), 
            new Point2D.Double(10, 0)
        };

        Parameters parameters = Parameters.DEFAULT;

        String[][] lcmVector = new String[][]{
            {"OR", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "OR", "AND", "AND", "NOTUSED", "AND", "OR", "NOTUSED", "OR", "OR", "OR"},
            {"NOTUSED", "NOTUSED", "AND", "AND", "NOTUSED", "OR", "AND", "AND", "AND", "OR", "OR", "NOTUSED", "OR", "OR", "OR"},
            {"NOTUSED", "AND", "AND", "NOTUSED", "OR", "OR", "AND", "OR", "NOTUSED", "AND", "AND", "OR", "OR", "OR", "NOTUSED"},
            {"OR", "AND", "NOTUSED", "NOTUSED", "OR", "OR", "AND", "OR", "NOTUSED", "AND", "AND", "AND", "OR", "NOTUSED", "OR"},
            {"NOTUSED", "NOTUSED", "OR", "OR", "OR", "AND", "AND", "OR", "OR", "AND", "AND", "AND", "OR", "OR", "OR"},
            {"OR", "OR", "OR", "OR", "AND", "OR", "NOTUSED", "AND", "NOTUSED", "AND", "OR", "NOTUSED", "OR", "NOTUSED", "OR"},
            {"AND", "AND", "AND", "AND", "AND", "NOTUSED", "OR", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "AND", "OR", "OR", "OR"},
            {"AND", "AND", "OR", "OR", "OR", "AND", "NOTUSED", "AND", "NOTUSED", "OR", "AND", "NOTUSED", "OR", "OR", "OR"},
            {"NOTUSED", "AND", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "NOTUSED", "NOTUSED", "NOTUSED", "AND", "AND", "OR", "NOTUSED", "OR", "NOTUSED"},
            {"AND", "OR", "AND", "AND", "AND", "AND", "OR", "OR", "AND", "AND", "NOTUSED", "NOTUSED", "NOTUSED", "OR", "OR"},
            {"OR", "OR", "AND", "AND", "AND", "OR", "NOTUSED", "AND", "AND", "NOTUSED", "AND", "OR", "OR", "NOTUSED", "OR"},
            {"NOTUSED", "NOTUSED", "OR", "AND", "AND", "NOTUSED", "AND", "NOTUSED", "OR", "NOTUSED", "OR", "AND", "OR", "NOTUSED", "NOTUSED"},
            {"OR", "OR", "NOTUSED", "OR", "OR", "OR","OR", "OR", "NOTUSED", "NOTUSED", "NOTUSED", "OR", "OR", "OR", "OR"},
            {"OR", "OR", "OR", "NOTUSED", "OR", "NOTUSED", "OR", "OR", "OR","OR", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "OR"},
            {"OR", "OR", "NOTUSED", "OR", "OR", "OR", "OR", "NOTUSED", "NOTUSED", "OR", "OR", "NOTUSED", "OR", "OR", "OR"}
        };
        LCM lcm = new LCM(lcmVector);

        boolean[] puvVector = new boolean[]{
                true, true, false, true, true, false, true, true, true, true, true, true, false, false, false
        };
        PUV puv = new PUV(puvVector);

        assertTrue(Main.computeResult(numPoints, points, parameters, lcm, puv));
    }
}


