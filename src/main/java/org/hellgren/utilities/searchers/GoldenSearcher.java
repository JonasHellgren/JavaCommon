package org.hellgren.utilities.searchers;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.conditionals.Conditionals;
import org.hellgren.utilities.list_arrays.MyListUtils;

import java.util.List;

import static org.hellgren.utilities.list_arrays.MyListUtils.findMax;
import static org.hellgren.utilities.list_arrays.MyListUtils.findMin;

/**
 * https://en.wikipedia.org/wiki/Golden-section_search
 *     Parameters:
 *     - f: a strictly unimodal function on [a, b]
 *     - a: the left endpoint of the interval
 *     - b: the right endpoint of the interval
 *     - tolerance: the precision tolerance (default: 1e-5)
 *
 *     Returns:
 *     - The x-coordinate of the minimum point of f in the interval [a, b]
 *     """
 *     invphi = (sqrt(5) - 1) / 2  # Inverse of the golden ratio

 *     while abs(b - a) > tolerance:
 *         c = b - (b - a) * invphi  # Calculate the interior point c
 *         d = a + (b - a) * invphi  # Calculate the interior point d

 *         if f(c) < f(d):
 *             # Narrow the interval to [a, d] and skip points to the right of d
 *             b = d
 *         else:
 *             # Narrow the interval to [c, b] and skip points to the left of c
 *             a = c

 *     # Return the midpoint of the final interval as the best estimate
 *     return (b + a) / 2
 *
 */

@Log
@AllArgsConstructor
public final class GoldenSearcher {
    private static final double PHI = (1 + Math.sqrt(5)) / 2;
    private static final double INVPHI = 1/PHI;

    FunctionWrapperI function;
    SearchSettings settings;

    /**
     * Searches for the minimum value of the function within the given interval.
     *
     * @return the x-value that corresponds to the minimum function value
     */
    public double searchMin() {
        // Delegate to the goldenSectionSearch method with isMinSearch set to true
        return goldenSectionSearch(true).getFirst();
    }

    /**
     * Searches for the maximum value of the function within the given interval.
     *
     * @return the x-value that corresponds to the maximum function value
     */
    public double searchMax() {
        // Delegate to the goldenSectionSearch method with isMinSearch set to false
        return goldenSectionSearch(false).getFirst();
    }

    /**
     * Searches for the minimum value of the function within the given interval and returns both the x-value and the corresponding function value.
     *
     * @return a pair containing the x-value and the minimum function value
     */
    public Pair<Double, Double> searchMinWithFunctionValue() {
        // Delegate to the goldenSectionSearch method with isMinSearch set to true
        return goldenSectionSearch(true);
    }

    /**
     * Searches for the maximum value of the function within the given interval and returns both the x-value and the corresponding function value.
     *
     * @return a pair containing the x-value and the maximum function value
     */
    public Pair<Double, Double> searchMaxWithFunctionValue() {
        // Delegate to the goldenSectionSearch method with isMinSearch set to false
        return goldenSectionSearch(false);
    }

    Pair<Double, Double> goldenSectionSearch(boolean isMinSearch) {
        double fBest=isMinSearch ? Double.MAX_VALUE: -Double.MAX_VALUE;
        double a=settings.xMin();
        double b=settings.xMax();
        Preconditions.checkArgument(a<b,"Bad interval");
        double tol=settings.tol();
        double c = calcC(a, b);
        double d = calcD(a, b);

        log.info("Starting search");
        int i=0;
        while (isTolViolated(a, b, tol) && isNofIterationsNotExceeded(i)) {
            double fC0 = function.f(c);
            double fC1 = isMinSearch? fC0 :-fC0;
            double fD0 = function.f(d);
            double fD1 = isMinSearch? fD0 :-fD0;
            log.fine("Interval, (c,d)=" + "(" + c + "," + d + ")");
            log.info("Function values, (fC,fD)=" + "(" + fC0 + "," + fD0 + ")");
            if (fC1 < fD1) {
                b = d;
            } else {
                a = c;
            }
            log.info("New interval, (a,b)=" + "(" + a + "," + b + ")");
            c = calcC(a, b);
            d = calcD(a, b);
            i++;
            var list=List.of(fBest,fC0,fD0);
            fBest=isMinSearch
                    ? findMin(list).orElseThrow()
                    : findMax(list).orElseThrow();

        }

        double sol = (b + a) / 2;
        fBest=(i==0) ? function.f(sol) : fBest;
        log.info("Gold search finished in "+i+" iterations"+", fBest="+fBest+", solution="+sol);
        Conditionals.executeIfTrue(isTolViolated(a, b, tol), () -> log.warning("Tolerance not fulfilled"));
        return Pair.create(sol,fBest);
    }

    private static boolean isTolViolated(double a, double b, double tol) {
        return Math.abs(b - a) > tol;
    }

    private boolean isNofIterationsNotExceeded(int i) {
        return i < settings.nIterMax();
    }

    private static double calcD(double a, double b) {
        return a + INVPHI * (b - a);
    }

    private static double calcC(double a, double b) {
        return b - INVPHI * (b - a);
    }


}
