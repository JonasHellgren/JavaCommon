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

   public Optional<Pair<Double, Double>> solve() {
        double d=Math.pow(b,2)-4*a*c;
        return (MyMathUtils.isNeg(d))
                ? Optional.empty()
                : Optional.of(new Pair<>((-b+Math.sqrt(Math.abs(d)))/(2*a),(-b-Math.sqrt(Math.abs(d)))/(2*a)));
    }


}
