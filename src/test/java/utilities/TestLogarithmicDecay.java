package utilities;

import org.hellgren.utilities.math.LogarithmicDecay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestLogarithmicDecay {
    static final double OUT_START = 1.0;
    static final double OUT_END = 0.5;
    static final double TIME_END = 10.0;
    static final double TOL = 1e-6;
    LogarithmicDecay decay;

    @BeforeEach
    void init() {
        decay = LogarithmicDecay.of(OUT_START, OUT_END, TIME_END);
    }

    @Test
    void testDefineParameters() {
        double expectedC = Math.log(OUT_START);
        double expectedGamma = -Math.log(Math.max(1e-20, OUT_END) / Math.exp(expectedC)) / TIME_END;
        assertEquals(expectedC, decay.getC(), TOL);
        assertEquals(expectedGamma, decay.getGamma(), TOL);
    }

    @Test
    void testTime0() {
        double time = 0.0;
        double out = decay.calcOut(time);
        assertEquals(OUT_START, out, TOL);  //decaying to 0.5
    }

    @Test
    void testTime5() {
        double time = 5.0;
        double out = decay.calcOut(time);
        assertTrue(out >= OUT_END);  //decaying to 0.5
        assertTrue(decay.calcOut(time) <= OUT_START);  //starting at 1.0 and decaying
    }

    @Test
    void testTime9_thenAlmostOutEnd() {
        double time = 9.0;
        double out = decay.calcOut(time);
        assertEquals(OUT_END, out, 0.1);  //decaying to 0.5
    }

    @Test
    void testTime10() {
        double time = 10.0;
        double out = decay.calcOut(time);
        assertEquals(OUT_END, out, TOL);  //decaying to 0.5
    }

    @Test
    void testTimeInt10() {
        int time = 10;
        double out = decay.calcOut(time);
        assertEquals(OUT_END, out, TOL);  //decaying to 0.5
    }

}
