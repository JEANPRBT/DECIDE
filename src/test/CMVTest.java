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
    public void LIC0PositiveTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(0, 10);
        points[2] = new Point2D.Double(1, 0);
        points[3] = new Point2D.Double(2, 2);
        points[4] = new Point2D.Double(1, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[0]);
    }
    @Test
    public void LIC0NegativeTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 2);
        points[2] = new Point2D.Double(2, 4);
        points[3] = new Point2D.Double(3, 6);
        points[4] = new Point2D.Double(4, 8);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[0]);
    }
    @Test
    public void LIC1NegativeTest1(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(0,1);
        points[2] = new Point2D.Double(0, 2);
       
        
        CMV cmv = new CMV(numPoints - 2, points, parameters);
        assertFalse(cmv.getVector()[1]);
    }
    @Test
    public void LIC1NegativeTest2(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1,1);
        points[2] = new Point2D.Double(-1, -1);
        points[3] = new Point2D.Double(0, 4);
        points[4] = new Point2D.Double(0, 2);
        
        CMV cmv = new CMV(numPoints , points, parameters);
        assertFalse(cmv.getVector()[1]);
    }
    @Test
    public void LIC1PositiveTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(6, 6);
        points[2] = new Point2D.Double(-10, -10);
   
        CMV cmv = new CMV(numPoints - 2, points, parameters);
        assertTrue(cmv.getVector()[1]);
    }
    @Test
    public void LIC2PositiveTest1(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 0);
        points[2] = new Point2D.Double(1, 1);
        points[3] = new Point2D.Double(2, 0);
        points[4] = new Point2D.Double(3, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[2]);
    }
    @Test
    public void LIC2PositiveTest2(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 0);
        points[2] = new Point2D.Double(2, 2);
        points[3] = new Point2D.Double(3, 0);
        points[4] = new Point2D.Double(4, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertTrue(cmv.getVector()[2]);
    }
    @Test
    public void LIC2NegativeTest(){
        points[0] = new Point2D.Double(0, 0);
        points[1] = new Point2D.Double(1, 0);
        points[2] = new Point2D.Double(2, 0);
        points[3] = new Point2D.Double(3, 0);
        points[4] = new Point2D.Double(4, 0);
        CMV cmv = new CMV(numPoints, points, parameters);
        assertFalse(cmv.getVector()[2]);
    }
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

}