import static org.junit.Assert.assertArrayEquals;

import java.awt.geom.Point2D;

import org.junit.Test;

import main.CMV;
import main.FUV;
import main.LCM;
import main.PUM;
import main.PUV;
import main.Parameters;

public class FUVTest {
    int numPoints = 5;
    Point2D[] points = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0)
    };
    Parameters parameters = Parameters.DEFAULT;
    LCM lcm = LCM.DEFAULT;
    PUV puv = PUV.DEFAULT;
    CMV cmv = new CMV(numPoints, points, parameters);
    boolean[][] matrix = new boolean[][] {
        {false, false, true, false, true, true, true, true, true, true, true, true, true, true, true},
        {false, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
        {true, true, false, true, true, true, true, true, true, true, true, true, true, true, true},
        {false, true, true, false, true, true, true, true, true, true, true, true, true, true, true},
        {true, true, true, true, false, true, true, true, true, true, true, true, true, true, true},
        {true, true, true, true, true, false, true, false, true, true, true, true, false, true, true},
        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
        {true, true, true, true, true, true, true, true, false, true, true, true, true, true, true},
        {true, true, true, true, true, true, true, true, true, false, true, true, true, true, true},
        {true, true, true, true, true, true, true, true, true, true, false, true, true, true, true},
        {true, true, true, true, true, true, true, true, true, true, true, false, true, true, true},
        {true, true, true, true, true, true, true, true, true, true, true, true, false, true, true},
        {true, true, true, true, true, true, true, true, true, true, true, true, true, false, true},
        {true, true, true, true, true, true, true, true, true, true, true, true, true, true, false},
    };
    PUM pum = new PUM(lcm, cmv, matrix);

    @Test
    public void PositiveFUVTest() {
        FUV fuv = new FUV(pum, puv);

        boolean[] expected = new boolean[] {false, true, true, true, true, true, false, true, true, true, true, true, true, true, true};

        assertArrayEquals(expected, fuv.getVector());
    }
}
