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

    // Private constructor to prevent instantiation
    private MyUnitConverter() {}

    /**
     * Converts watts to kilowatts.
     *
     * @param p the power value in watts
     * @return the power value in kilowatts
     */
    public static Function<Double, Double> w2kw = (p) ->
            MyUnitConverter.convertPower(p, Units.WATT, NonSIUnits.KILO_WATT);

    /**
     * Converts a value to a percentage.
     *
     * @param n the value to convert
     * @return the value as a percentage
     */
    public static Function<Double, Double> toPercent = (n) -> n * 100;

    /**
     * The number of joules in a kilowatt-hour.
     */
    public static final int JOULE_PER_KWH = 3_600_000;

    /**
     * Converts joules to kilowatt-hours.
     *
     * @param e the energy value in joules
     * @return the energy value in kilowatt-hours
     */
    public static Function<Double, Double> joule2kWh = (e) -> e / JOULE_PER_KWH;

    /**
     * Converts kilowatt-hours to joules.
     *
     * @param e the energy value in kilowatt-hours
     * @return the energy value in joules
     */
    public static DoubleUnaryOperator kWh2joule = (e) -> e * JOULE_PER_KWH;

    /**
     * Converts euros per kilowatt-hour to euros per joule.
     *
     * @param p the price value in euros per kilowatt-hour
     * @return the price value in euros per joule
     */
    public static Function<Double, Double> europkwh2europjoule = (p) -> p / JOULE_PER_KWH;

    /**
     * Converts euros per joule to euros per kilowatt-hour.
     *
     * @param p the price value in euros per joule
     * @return the price value in euros per kilowatt-hour
     */
    public static Function<Double, Double> europjoule2europkwh = (p) -> p * JOULE_PER_KWH;

    /**
     * The number of watts in a kilowatt.
     */
    public static final int WATT_PER_KW = 1_000;

    /**
     * Converts euros per kilowatt to euros per watt.
     *
     * @param p the price value in euros per kilowatt
     * @return the price value in euros per watt
     */
    public static Function<Double, Double> europKw2europw = (p) -> p / WATT_PER_KW;

    /**
     * Converts values per second to values per hour.
     *
     * @param n the value to convert
     * @return the value per hour
     */
    public static Function<Double, Double> persec2perHour = (n) -> n * Math.pow(60, 2);

    /**
     * Converts values per hour to values per second.
     *
     * @param n the value to convert
     * @return the value per second
     */
    public static Function<Double, Double> perHour2perSec = (n) -> n / Math.pow(60, 2);

    /**
     * Converts values per second to values per day.
     *
     * @param n the value to convert
     * @return the value per day
     */
    public static Function<Double, Double> persec2perday = (n) -> n * (Math.pow(60, 2) * 24);

    /**
     * Converts a time value from one unit to another.
     *
     * @param value the time value to convert
     * @param unitIn the unit of the input value
     * @param unitOut the unit to convert to
     * @return the converted time value
     */
    public static double convertTime(double value, Unit<Time> unitIn, Unit<Time> unitOut) {
        Quantity<Time> timeMeasure;
        timeMeasure = Quantities.getQuantity(value, unitIn);
        return (double) timeMeasure.to(unitOut).getValue();
    }

    /**
     * Converts a power value from one unit to another.
     *
     * @param value the power value to convert
     * @param unitIn the unit of the input value
     * @param unitOut the unit to convert to
     * @return the converted power value
     */
    public static double convertPower(double value, Unit<Power> unitIn, Unit<Power> unitOut) {
        Quantity<Power> powerMeasure;
        powerMeasure = Quantities.getQuantity(value, unitIn);
        return (double) powerMeasure.to(unitOut).getValue();
    }

    /**
     * Converts an energy value from one unit to another.
     *
     * @param value the energy value to convert
     * @param unitIn the unit of the input value
     * @param unitOut the unit to convert to
     * @return the converted energy value
     */
    public static double convertEnergy(double value, Unit<Energy> unitIn, Unit<Energy> unitOut) {
        Quantity<Energy> energyMeasure;
        energyMeasure = Quantities.getQuantity(value, unitIn);
        return (double) energyMeasure.to(unitOut).getValue();
    }

}
