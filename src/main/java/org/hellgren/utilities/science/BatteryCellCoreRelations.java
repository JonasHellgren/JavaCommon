package org.hellgren.utilities.science;

import org.apache.commons.lang3.function.TriFunction;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;

public class BatteryCellCoreRelations {

    private BatteryCellCoreRelations() {
    }

    static double power(double voltage, double current) {
        return voltage*current;
    }

    public static BiFunction<Double,Double, Double> powerCore = (u, i) ->
            power(u,i);

    public static TriFunction<Double,Double,Double,Double> voltageCellCore = (ocv, current, r) ->
            ocv + current * r;

    public static BiFunction<Double,Double,Double> currentCore = (voltage,r) ->
            voltage/r;

    public static final DoubleBinaryOperator powerLoss = (current, r) ->
            Math.pow(current,2) * r;


    public static BiFunction<Double, Double, Double> powerLossOhmic = (current, r) ->
            Math.pow(current,2) * r;

}
