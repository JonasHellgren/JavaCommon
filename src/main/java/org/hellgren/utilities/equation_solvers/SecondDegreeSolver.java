package org.hellgren.utilities.equation_solvers;

import lombok.Builder;
import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.math.MyMathUtils;

import java.util.Optional;

/**
 * <a href="https://www.javatpoint.com/java-program-to-solve-quadratic-equation">...</a>
 */

@Builder
public class SecondDegreeSolver {

    double a;
    double b;
    double c;

    /**
     * Solves the quadratic equation ax^2 + bx + c = 0 and returns the roots.
     *
     * @return an Optional containing a Pair of Double roots if the equation has real roots,
     *         otherwise an empty Optional
     */
    public Optional<Pair<Double, Double>> solve() {
        double determinant = calculateDeterminant(a, b, c);
        return isNegative(determinant)
                ? Optional.empty()
                : Optional.of(calculateRoots(a, b, determinant));
    }

    private double calculateDeterminant(double a, double b, double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }

    private boolean isNegative(double value) {
        return MyMathUtils.isNeg(value);
    }

    private Pair<Double, Double> calculateRoots(double a, double b, double determinant) {
        double sqrtDeterminant = Math.sqrt(Math.abs(determinant));
        double root1 = (-b + sqrtDeterminant) / (2 * a);
        double root2 = (-b - sqrtDeterminant) / (2 * a);
        return new Pair<>(root1, root2);
    }

}
