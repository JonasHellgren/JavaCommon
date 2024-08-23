package org.hellgren.utilities.gradient;

import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.Pair;

import static org.hellgren.utilities.conditionals.MyFunctions.sqr2;

@AllArgsConstructor
public final class NormalDistributionGradientCalculator {

    public static final double SMALLEST_DENOM = 1e-2;

    double smallestDenom;

    public NormalDistributionGradientCalculator() {
        this.smallestDenom=SMALLEST_DENOM;
    }

    public Pair<Double, Double> gradient(double action, Pair<Double, Double> meanAndStd) {
        double mean = meanAndStd.getFirst();
        double std = meanAndStd.getSecond();
        double denom = Math.max(sqr2.apply(std), smallestDenom);
        double gradMean = 1 / denom * (action - mean);
        double gradLogStd = sqr2.apply(action - mean) /denom - 1d;
        return Pair.create(gradMean, gradLogStd);
    }

}
