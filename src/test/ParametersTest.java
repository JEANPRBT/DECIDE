import main.Parameters;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class ParametersTest {
    @Test
    public void Numpointslessthan2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, -0, 1, 3, 2, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        });
    }
    @Test
    public void NumpointsGreaterthan100() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, -0, 1, 3, 2, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 101);
        });
    }
    @Test
    public void EpsilonLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, -0.01, 1, 3, 2, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }

    @Test
    public void KPtsOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 0.1, 1, 3, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void L1LessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(-1, 1, 0.1, 1, 3, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void A1LessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 0.1, -1, 3, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 5);
        });
    }
    @Test
    public void A2LessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 0.1, 1, 3, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, -1, 1, -1, 5);
        });
    }
    @Test
    public void R1LessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, -1, 0.1, -1, 3, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void R2LessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 0.1, 1, 3, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 5);
        });
    }
    @Test
    public void QptsLessThan2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 0.1, 1, 1, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void QptsgreaterThanNP() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 0.1, 1, 6, 2, 1, 3, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void KptsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, 1, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void KptsGreaterThanNP() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, 1, 3, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void CptsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, 1, 3, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void DptsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, 1, 3, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void EptsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, 1, 3, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void FptsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, 1, 3, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void NptsLessThan3() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, -0, 1, 3, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void NptsGreaterThanNP() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, -0, 1, 3, 2, 1, 6, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void SumOfC_PTSAndD_PTS() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 3, 3, 1, 3, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void SumOfE_PTSAndF_PTS() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 3, 3, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 5);
        });
    }
    @Test
    public void GptsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 3, 3, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 0, 1, 1, 1, 5);
        });
    }
    @Test
    public void GptsGreaterThan() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 3, 3, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 4, 1, 1, 1, 5);
        });
    }
    @Test
    public void QuadsLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 3, 3, 0, 3, 1, 1, 1, 2, 2, 2, 2, 2, 0, 1, 1, 1, 5);
        });
    }
    @Test
    public void QuadsGreaterThan3() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 3, 3, 4, 3, 1, 1, 1, 2, 2, 2, 2, 2, 0, 1, 1, 1, 5);
        });
    }
    @Test
    public void distanceLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parameters(1, 1, 1, 1, 3, 2, -1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5);
        });
    }
}