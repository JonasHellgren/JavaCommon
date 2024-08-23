package org.hellgren.utilities.unit_converter;

import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;
import javax.measure.quantity.Time;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class MyUnitConverter {

    private MyUnitConverter() {
    }

    public static Function<Double,Double> w2kw=(p) -> MyUnitConverter.convertPower(p, Units.WATT, NonSIUnits.KILO_WATT);
    public static Function<Double,Double> toPercent=(n) -> n*100;

    public static final int JOULE_PER_KWH = 3_600_000;
    public static Function<Double,Double> joule2kWh=(e) -> e/ JOULE_PER_KWH;
    public static DoubleUnaryOperator kWh2joule=(e) -> e* JOULE_PER_KWH;

    public static Function<Double,Double> europkwh2europjoule=(p) -> p/JOULE_PER_KWH;
    public static Function<Double,Double> europjoule2europkwh=(p) -> p*JOULE_PER_KWH;


    public static final int WATT_PER_KW = 1_000;
    public static Function<Double,Double> europKw2europw=(p) -> p/ WATT_PER_KW;  //1 E/kW -> 1/1000 E/W


    public static Function<Double,Double> persec2perHour=(n) -> n*Math.pow(60,2);
    public static Function<Double,Double> perHour2perSec=(n) -> n/Math.pow(60,2);

    public static Function<Double,Double> persec2perday=(n) -> n*(Math.pow(60,2)*24);



    public static double convertTime(double value, Unit<Time> unitIn, Unit<Time> unitOut) {
        Quantity<Time> timeMeasure;
        timeMeasure = Quantities.getQuantity(value, unitIn);
        return (double) timeMeasure.to(unitOut).getValue();
    }

    public static double convertPower(double value, Unit<Power> unitIn, Unit<Power> unitOut) {
        Quantity<Power> powerMeasure;
        powerMeasure = Quantities.getQuantity(value, unitIn);
        return (double) powerMeasure.to(unitOut).getValue();
    }

    public static double convertEnergy(double value, Unit<Energy> unitIn, Unit<Energy> unitOut) {
        Quantity<Energy> energyMeasure;
        energyMeasure = Quantities.getQuantity(value, unitIn);
        return (double) energyMeasure.to(unitOut).getValue();
    }

}
