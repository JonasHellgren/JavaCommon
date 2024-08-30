package utilities;

import org.hellgren.utilities.unit_converter.MyUnitConverter;
import org.hellgren.utilities.unit_converter.NonSIUnits;
import org.junit.jupiter.api.Test;
import tec.units.ri.unit.Units;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;
import javax.measure.quantity.Time;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static tec.units.ri.unit.Units.JOULE;

public class TestMyUnitConverter {

    @Test
    public void testW2KW() {
        double input = 1000;
        double expected = 1;
        double actual = MyUnitConverter.w2kw.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testToPercent() {
        double input = 0.5;
        double expected = 50;
        double actual = MyUnitConverter.toPercent.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testJoule2KWH() {
        double input = 3600000;
        double expected = 1;
        double actual = MyUnitConverter.joule2kWh.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testKWH2Joule() {
        double input = 1;
        double expected = 3600000;
        double actual = MyUnitConverter.kWh2joule.applyAsDouble(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testEuropKWH2EuropJoule() {
        double input = 1;
        double expected = 1d/3600000;
        double actual = MyUnitConverter.europkwh2europjoule.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testEuropJoule2EuropKWH() {
        double input = 1;
        double expected = 3600000;
        double actual = MyUnitConverter.europjoule2europkwh.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testEuropKW2EuropW() {
        double input = 1;
        double expected = 1d/1000;
        double actual = MyUnitConverter.europKw2europw.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testPerSec2PerHour() {
        double input = 1;
        double expected = 3600;
        double actual = MyUnitConverter.persec2perHour.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testPerHour2PerSec() {
        double input = 1;
        double expected = 1d/3600;
        double actual = MyUnitConverter.perHour2perSec.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testPerSec2PerDay() {
        double input = 1;
        double expected = 86400;
        double actual = MyUnitConverter.persec2perday.apply(input);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testConvertTime() {
        double input = 3600;
        double expected = 1;
        double actual = MyUnitConverter.convertTime(input, Units.SECOND, Units.HOUR);
        assertEquals(expected, actual, 0.01);
    }


    @Test
    public void testConvertPower_WattToKilowatt() {
        double value = 1000; // 1000 Watts
        Unit<Power> unitIn = Units.WATT;
        Unit<Power> unitOut = NonSIUnits.KILO_WATT;
        double expected = 1; // 1 Kilowatt
        double actual = MyUnitConverter.convertPower(value, unitIn, unitOut);
        assertEquals(expected, actual, 0.01); // delta 0.01 for floating point comparison
    }

    @Test
    public void testConvertPower_KilowattToWatt() {
        double value = 1; // 1 Kilowatt
        Unit<Power> unitIn = NonSIUnits.KILO_WATT;
        Unit<Power> unitOut = Units.WATT;
        double expected = 1000; // 1000 Watts
        double actual = MyUnitConverter.convertPower(value, unitIn, unitOut);
        assertEquals(expected, actual, 0.01); // delta 0.01 for floating point comparison
    }

    @Test
    public void testConvertEnergy_JouleToKilojoule() {
        double value = 1e3; // 1000 Joules
        Unit<Energy> unitIn = JOULE;
        Unit<Energy> unitOut = NonSIUnits.KILO_JOULE;
        double expected = 1; // 1 Kilojoule
        double actual = MyUnitConverter.convertEnergy(value, unitIn, unitOut);
        assertEquals(expected, actual, 0.01); // delta 0.01 for floating point comparison
    }

    @Test
    public void testConvertEnergy_JouleToMegajoule() {
        double value = 1e6; // 1000_000 Joules
        Unit<Energy> unitIn = JOULE;
        Unit<Energy> unitOut = NonSIUnits.MEGA_JOULE;
        double expected = 1; // 1 Kilojoule
        double actual = MyUnitConverter.convertEnergy(value, unitIn, unitOut);
        assertEquals(expected, actual, 0.01); // delta 0.01 for floating point comparison
    }

    @Test
    public void testConvertEnergy_KilojouleToJoule() {
        double value = 1; // 1 KiloJoule
        Unit<Energy> unitIn =  NonSIUnits.KILO_JOULE;
        Unit<Energy> unitOut = JOULE;
        double expected = 1e3; // 1000 Joules
        double actual = MyUnitConverter.convertEnergy(value, unitIn, unitOut);
        assertEquals(expected, actual, 0.01); // delta 0.01 for floating point comparison
    }


    @Test
    public void testConvertEnergy_MegajouleToJoule() {
        double value = 1; // 1 MegaJoule
        Unit<Energy> unitIn =  NonSIUnits.MEGA_JOULE;
        Unit<Energy> unitOut = JOULE;
        double expected = 1e6; // 1000_000 Joules
        double actual = MyUnitConverter.convertEnergy(value, unitIn, unitOut);
        assertEquals(expected, actual, 0.01); // delta 0.01 for floating point comparison
    }

}
