import main.CMV;
import main.LCM;
import main.PUM;
import static org.junit.Assert.*;

import main.Parameters;
import org.junit.Test;

import java.awt.geom.Point2D;

public class PUMTest {
    int numPoints = 3;
    Point2D[] points = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 0)
    };
    Parameters parameters = Parameters.DEFAULT;
    CMV cmv = new CMV(numPoints, points, parameters);
    LCM lcm = LCM.DEFAULT;
    @Test
    public void PositivePUMTest() {

        boolean[] vector = new boolean[]{true, false, true, true, false, true, false, true, true, false, true, false,
        false, true, false};
        cmv.setVector(vector);
        PUM pum = new PUM(lcm, cmv);

        boolean[][] expected = new boolean[][]{
                {true, true, true, true, true, true, false, true, true, false, true, true, false, true, false},
                {true, true, false, false, true, true, false, false, false, false, true, true, false, true, false},
                {true, false, true, true, true, true, false, true, true, false, true, true, true, true, true},
                {true, false, true, true, true, true, false, true, true, false, true, false, true, true, true},
                {true, true, true, true, false, false, false, true, true, false, false, false, false, true, false},
                {true, true, true, true, false, true, true, true, true, false, true, true, false, true, false},
                {false, false, false, false, false, true, false, true, true, false, true, false, false, false, false},
                {true, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, false, true, true, true, true, true, true, true, false, true, true, true, true, true},
                {false, false, false, false, false, false, false, true, false, false, true, true, true, false, false},
                {true, true, true, true, false, true, true, true, true, true, true, true, true, true, false},
                {true, true, true, false, false, true, false, true, true, true, true, false, false, true, true},
                {false, false, true, true, false, false, false, true, true, true, true, false, false, false, false},
                {true, true, true, true, true, true, false, true, true, false, true, true, false, true, true},
                {false, false, true, true, false, false, false, true, true, false, false, true, false, true, false}
        };

        for(int i = 0; i < expected.length; i++){
            assertArrayEquals(expected[i], pum.getMatrix()[i]);
        }
    }
}
