import main.CMV;
import main.Parameters;
import java.awt.geom.Point2D;
import static org.junit.Assert.*;
import org.junit.Test;
public class CMVTest {
    int numPoints = 5;
    Point2D[] points = new Point2D[numPoints];
    Parameters parameters = new Parameters();
   
    @Test
    public void LIC3PositiveTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(0, 2);
        points[2] = new Point2D.Double(2, 0);
        points[3] = new Point2D.Double(0, 1);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getCMV()[3]);
    }
    @Test
    public void LIC3NegativeTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 1);
        points[2] = new Point2D.Double(2, 2);
        points[3] = new Point2D.Double(3, 3);
        points[4] = new Point2D.Double(4, 4);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getCMV()[3]);
    }

    @Test
    public void LIC4PositiveTest(){
        points[0] = new Point2D.Double(1, 1);
        points[1] = new Point2D.Double(-1, -1);
        points[2] = new Point2D.Double(-1, 1);
        points[3] = new Point2D.Double(0, 1);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getCMV()[4]);
    }

    @Test
    public void LIC4NegativeTest(){
        points[0] = new Point2D.Double(-8, 1);
        points[1] = new Point2D.Double(2, 2);
        points[2] = new Point2D.Double(3, 3);
        points[3] = new Point2D.Double(-1, -10);
        points[4] = new Point2D.Double(4, 8);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getCMV()[4]);
    }

    @Test
    public void LIC5PositiveTest(){
        points[0] = new Point2D.Double(1, 1);
        points[1] = new Point2D.Double(-1, -1);
        points[2] = new Point2D.Double(-1, 1);
        points[3] = new Point2D.Double(0, 1);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getCMV()[5]);
    }

    @Test
    public void LIC5NegativeTest(){
        points[0] = new Point2D.Double(1, 0);
        points[1] = new Point2D.Double(2, 0);
        points[2] = new Point2D.Double(3, 0);
        points[3] = new Point2D.Double(4, 0);
        points[4] = new Point2D.Double(5, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getCMV()[5]);
    }
    @Test
    public void LIC12PositiveTest() {
        parameters.k_pts = 1;  
        parameters.length1 = 2.0;  
        parameters.length2 = 1.0;  
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(0.5, 0); 
        points[2] = new Point2D.Double(3,0);  
        points[3] = new Point2D.Double(1, 0); 
        points[4] = new Point2D.Double(2, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getCMV()[12]);
    }
    @Test
    public void LIC12NegativeTest() {
        parameters.k_pts = 1;  
        parameters.length1 = 2.0;  
        parameters.length2 = 1.0;  
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1.5, 0); 
        points[2] = new Point2D.Double(2, 0);   
        points[3] = new Point2D.Double(3, 0);   
        points[4] = new Point2D.Double(4, 0);  
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getCMV()[12]);  
    }
    @Test
    public void LIC13PositiveTest() {
        parameters.a_pts = 1;
        parameters.b_pts = 1;
        parameters.radius1 = 1.0; 
        parameters.radius2 = 10.0;
        points[0] = new Point2D.Double(0, 0); 
        points[1] = new Point2D.Double(2, 0); 
        points[2] = new Point2D.Double(4, 0); 
        points[3] = new Point2D.Double(1, 1); 
        points[4] = new Point2D.Double(3, 1); 
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getCMV()[13]);
    }

    @Test
    public void LIC13NegativeTest() {
    parameters.a_pts = 1;
    parameters.b_pts = 1;
    parameters.radius1 = 5.0; 
    parameters.radius2 = 2.0; 
    points[0] = new Point2D.Double(0, 0);
    points[1] = new Point2D.Double(1, 1); 
    points[2] = new Point2D.Double(2, 2); 
    points[3] = new Point2D.Double(3, 3); 
    points[4] = new Point2D.Double(4, 4); 
    CMV cmv = new CMV(numPoints, points, parameters);
    assertFalse(cmv.getCMV()[13]);
    }

}