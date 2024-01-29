import main.CMV;
import main.Parameters;
import java.awt.geom.Point2D;
import static org.junit.Assert.*;
import org.junit.Test;
public class CMVTest {
    int numPoints = 5;
    Point2D[] points = new Point2D[numPoints];
    Parameters parameters = Parameters.DEFAULT;
    @Test
    public void LIC3PositiveTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(0, 2);
        points[2] = new Point2D.Double(2, 0);
        points[3] = new Point2D.Double(0, 1);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[3]);
    }
    @Test
    public void LIC3NegativeTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 1);
        points[2] = new Point2D.Double(2, 2);
        points[3] = new Point2D.Double(3, 3);
        points[4] = new Point2D.Double(4, 4);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[3]);
    }

    @Test
    public void LIC4PositiveTest(){
        points[0] = new Point2D.Double(1, 1);
        points[1] = new Point2D.Double(-1, -1);
        points[2] = new Point2D.Double(-1, 1);
        points[3] = new Point2D.Double(0, 1);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[4]);
    }

    @Test
    public void LIC4NegativeTest(){
        points[0] = new Point2D.Double(-8, 1);
        points[1] = new Point2D.Double(2, 2);
        points[2] = new Point2D.Double(3, 3);
        points[3] = new Point2D.Double(-1, -10);
        points[4] = new Point2D.Double(4, 8);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[4]);
    }

    @Test
    public void LIC5PositiveTest(){
        points[0] = new Point2D.Double(1, 1);
        points[1] = new Point2D.Double(-1, -1);
        points[2] = new Point2D.Double(-1, 1);
        points[3] = new Point2D.Double(0, 1);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[5]);
    }

    @Test
    public void LIC5NegativeTest(){
        points[0] = new Point2D.Double(1, 0);
        points[1] = new Point2D.Double(2, 0);
        points[2] = new Point2D.Double(3, 0);
        points[3] = new Point2D.Double(4, 0);
        points[4] = new Point2D.Double(5, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[5]);
    }
    @Test 
    public void LIC10PositiveTest(){
        points[0] = new Point2D.Double(0,0);
        points[1] = new Point2D.Double(1,1);
        points[2] = new Point2D.Double(3,10);
        points[3] = new Point2D.Double(15, 30);
        points[4] = new Point2D.Double(400,40);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[10]);
    }
    @Test 
    public void LIC10NegativeTest(){
        points[0] = new Point2D.Double(0,0);
        points[1] = new Point2D.Double(1,1);
        points[2] = new Point2D.Double(2,2);
        points[3] = new Point2D.Double(0.25,0.25);
        points[4] = new Point2D.Double(0.3,0.3);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[10]);
    }
    
    @Test
    public void LIC14PositiveTest() {
        parameters.e_pts = 1;
        parameters.f_pts = 1;
        parameters.area1 = 1.0; 
        parameters.area2 = 10.0; 
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 0); 
        points[2] = new Point2D.Double(0, 1); 
        points[3] = new Point2D.Double(1, 1); 
        points[4] = new Point2D.Double(10, 0); 
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[14]);
    }
    @Test
    public void LIC14NegativeTest() {
        parameters.e_pts = 1;
        parameters.f_pts = 1;
        parameters.area1 = 5.0; 
        parameters.area2 = 0.5; 
        numPoints = 5;
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 0); 
        points[2] = new Point2D.Double(0, 2); 
        points[3] = new Point2D.Double(1, 2); 
        points[4] = new Point2D.Double(2, 0); 
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[14]);
    }

}