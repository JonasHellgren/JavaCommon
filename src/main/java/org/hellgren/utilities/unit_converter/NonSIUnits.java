package org.hellgren.utilities.unit_converter;

import tec.units.ri.unit.MetricPrefix;
import tec.units.ri.unit.Units;

import javax.measure.Unit;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;

public class NonSIUnits {
    public static Unit<Power> KILO_WATT = MetricPrefix.KILO(Units.WATT);
    public static Unit<Energy> MEGA_JOULE = MetricPrefix.MEGA(Units.JOULE);
}
