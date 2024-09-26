package org.hellgren.utilities.science;

import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;

public class BatteryCellCoreRelations {

    private BatteryCellCoreRelations() {
    }

    /**
     * Calculates the power of a battery cell given its voltage and current.
     *
     * @param voltage the voltage of the battery cell
     * @param current the current of the battery cell
     * @return the power of the battery cell
     */
    static double power(double voltage, double current) {
        // Multiply voltage and current to calculate power
        return voltage * current;
    }

    /**
     * A function that calculates the power of a battery cell given its voltage and current.
     *
     * @see #power(double, double)
     */
    public static BiFunction<Double, Double, Double> powerCore = (u, i) ->
            power(u, i);

    /**
     * Calculates the voltage of a battery cell given its open circuit voltage (OCV), current, and internal resistance.
     *
     * @param ocv the open circuit voltage of the battery cell
     * @param current the current of the battery cell
     * @param r the internal resistance of the battery cell
     * @return the voltage of the battery cell
     */
    public static TriFunction<Double, Double, Double, Double> voltageCellCore = (ocv, current, r) ->
            ocv + current * r;

    /**
     * Calculates the current of a battery cell given its voltage and internal resistance.
     *
     * @param voltage the voltage of the battery cell
     * @param r the internal resistance of the battery cell
     * @return the current of the battery cell
     */
    public static BiFunction<Double, Double, Double> currentCore = (voltage, r) ->
            voltage / r;


    /**
     * Calculates the current of a battery cell given its power and voltage.
     *
     * @param power the power of the battery cell
     * @param voltage the voltage of the battery cell
     * @return the current of the battery cell
     */

    public static BiFunction<Double, Double, Double> currentFromPowerAndVoltage = (power, voltage) ->
            power / voltage;


    /**
     * Calculates the power loss due to ohmic resistance in a battery cell given its current and internal resistance.
     *
     * @param current the current of the battery cell
     * @param r the internal resistance of the battery cell
     * @return the power loss due to ohmic resistance
     */
    public static BiFunction<Double, Double, Double> powerLossOhmic = (current, r) ->
            Math.pow(current, 2) * r;

}
