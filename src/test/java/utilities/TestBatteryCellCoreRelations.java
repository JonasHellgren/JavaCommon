package utilities;

import org.hellgren.utilities.science.BatteryCellCoreRelations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBatteryCellCoreRelations {

    public static final double TOL = 0.01;

    @Test
    public void testPowerCore() {
        double voltage = 10.0;
        double current = 2.0;
        double expectedPower = voltage * current;
        double actualPower = BatteryCellCoreRelations.powerCore.apply(voltage, current);
        assertEquals(expectedPower, actualPower, TOL);
    }

    @Test
    public void testVoltageCellCore() {
        double ocv = 12.0;
        double current = 3.0;
        double resistance = 1.0;
        double expectedVoltage = ocv + current * resistance;
        double actualVoltage = BatteryCellCoreRelations.voltageCellCore.apply(ocv, current, resistance);
        assertEquals(expectedVoltage, actualVoltage, TOL);
    }

    @Test
    public void testCurrentCore() {
        double voltage = 10.0;
        double resistance = 2.0;
        double expectedCurrent = voltage / resistance;
        double actualCurrent = BatteryCellCoreRelations.currentCore.apply(voltage, resistance);
        assertEquals(expectedCurrent, actualCurrent, TOL);
    }

    @Test
    public void testPowerLossOhmic() {
        double current = 2.0;
        double resistance = 1.0;
        double expectedPowerLoss = Math.pow(current, 2) * resistance;
        double actualPowerLoss = BatteryCellCoreRelations.powerLossOhmic.apply(current, resistance);
        assertEquals(expectedPowerLoss, actualPowerLoss, TOL);
    }

}
