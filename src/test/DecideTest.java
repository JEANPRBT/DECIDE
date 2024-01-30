import main.CMV;
import main.FUV;
import main.LCM;
import main.PUM;
import main.PUV;
import main.Decide;

import static org.junit.Assert.*;
import main.Parameters;
import org.junit.Test;
import java.awt.geom.Point2D;

public class DecideTest {

    int numPoints = 3;
    Parameters parameters = Parameters.DEFAULT;
    

    @Test
    public void NegativeDecideTest() {

        Point2D[] points = new Point2D[]{
            new Point2D.Double(10, 5),
            new Point2D.Double(2, 6),
            new Point2D.Double(2, 3),
            new Point2D.Double(0, 1),
            new Point2D.Double(4,6)
        };

        CMV cmv = new CMV(numPoints, points, parameters);
        LCM lcm = LCM.DEFAULT;
        PUM pum = new PUM(lcm,cmv);
        PUV puv = PUV.DEFAULT;
        FUV fuv = new FUV(pum, puv);
        Decide Launch = new Decide(fuv);
        
        assertFalse(Launch.getBoolean());
    }
}


