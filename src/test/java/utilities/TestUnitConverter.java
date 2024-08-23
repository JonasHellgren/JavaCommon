package utilities;

import org.hellgren.utilities.unit_converter.MyUnitConverter;
import org.hellgren.utilities.unit_converter.NonSIUnits;
import org.junit.Assert;
import org.junit.Test;
import tec.units.ri.unit.Units;

public class TestUnitConverter {


    public static final double DELTA = 0.1;

    @Test
    public void giveOneMinute_then60Seconds() {
        double timeInSec= MyUnitConverter.convertTime(1d, Units.MINUTE, Units.SECOND);
        Assert.assertEquals(60,timeInSec, DELTA);
    }

    @Test
    public void give1000Watt_then1KiloWatt() {
        double powerInKw= MyUnitConverter.convertPower(1000d, Units.WATT, NonSIUnits.KILO_WATT);
        Assert.assertEquals(1,powerInKw, DELTA);
    }

    @Test
    public void give1r6Joule_then1MJ() {
        double enInMJ= MyUnitConverter.convertEnergy(1e6, Units.JOULE, NonSIUnits.MEGA_JOULE);
        Assert.assertEquals(1,enInMJ, DELTA);
    }


}
