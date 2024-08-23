package org.hellgren.utilities.economics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import org.hellgren.utilities.math.MyMathUtils;

/**
 * The conversion between price (Є) and yearly cost (Є/year) is done by an annuity calculation
 * Useful for capital cost analysis
 *
 * Simple example: buying an item for 1000 Euro, using it for ten years, zero interest and rest value
 * Then the annuity is 100 Euro/year. Annuity increases by interest.
 * So 10% interest gives here 162 Euro/year.
 *
 * The calculation is based on price of the investment,  rest value, i=interest rate (-) and
 * lifeTimeInYears=the economic lifetime (years).
 *
 */

@Builder
@AllArgsConstructor
public  class AnnuityCalculator {

    @NonNull
    private Double price;

    @NonNull
    private Double restValue;

    @NonNull
    private Double i;

    @NonNull
    private Double lifeTimeInYears;

    public double annuity() {

        if (MyMathUtils.isZero(i)) {
            return (price - restValue) / lifeTimeInYears;
        }

        double t1 =price - restValue / pow(i, lifeTimeInYears);
        double t2 = i / (1 - pow(i, -lifeTimeInYears));
        return t1 * t2;
    }

    private double pow(double i, double n) {
        return Math.pow(1 + i, n);
    }


}
