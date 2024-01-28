import main.CMV;
import main.Parameters;
import java.awt.geom.Point2D;
import static org.junit.Assert.*;
import org.junit.Test;

public class CMVTest {
    int numPoints = 3;
    Point2D[] points = new Point2D[numPoints];
    Parameters parameters = new Parameters(1);

    /* There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
    exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
    condition is not met when NUMPOINTS < 3. */
    @Test
    public void LIC11PositiveTest(){
      points[0] = new Point2D.Double(5, 0);
      points[1] = new Point2D.Double(0, 2);
      points[2] = new Point2D.Double(2, 0);
      CMV cmv = new CMV(numPoints, points, parameters);
      assertTrue(cmv.getVector()[11]);
    }

    // should be false when numPoints < 3
    @Test
    public void LIC11NegativeTest1(){
        int fewPoints = 2;
        Point2D[] lessPoints = new Point2D[fewPoints];
        lessPoints[0] = new Point2D.Double(5, 0);
        lessPoints[1] = new Point2D.Double(0, 2);
        CMV cmv = new CMV(fewPoints, lessPoints, parameters);
        assertFalse(cmv.getVector()[11]);
    }

    @Test
    public void LIC11NegativeTest2(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(5, 2);
        points[2] = new Point2D.Double(10, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[11]);
    }
    
}