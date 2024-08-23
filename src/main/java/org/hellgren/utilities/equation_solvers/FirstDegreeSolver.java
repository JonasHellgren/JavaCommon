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

    public Optional<Double> solve() {

        return  (MyMathUtils.isZero(b))
                ? Optional.empty()
                : Optional.of(b/a);

    }

}
