package org.hellgren.utilities.equation_solvers;

import org.hellgren.utilities.math.MyMathUtils;

import java.util.Optional;

/**
 *  a*x-b=0  => x=b/a
 */

public class FirstDegreeSolver {

    double a,b;

    public FirstDegreeSolver(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Solves the first-degree equation a*x - b = 0.
     *
     * @return an Optional containing the solution to the equation, or an empty Optional if the equation has no solution.
     */
    public Optional<Double> solve() {
        if (isCoefficientZero(b)) {
            return Optional.empty();
        }
        return Optional.of(getSolution());
    }

    private boolean isCoefficientZero(double coefficient) {
        return MyMathUtils.isZero(coefficient);
    }

    private double getSolution() {
        return b / a;
    }

}
