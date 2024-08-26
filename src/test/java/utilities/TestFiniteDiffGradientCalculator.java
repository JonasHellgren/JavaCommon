package utilities;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunctionGradient;
import org.hellgren.utilities.gradient.FiniteDiffGradientCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFiniteDiffGradientCalculator {


    public static final double TOL = 0.1;

    /***
     * https://www.cuemath.com/calculus/derivative-of-sin-x/
     */

    @Test
    public void whenDerSinus_thenCosinus() {
            MultivariateFunction mf= point -> Math.sin(point[0]);
            ObjectiveFunction of=new ObjectiveFunction(mf);
            var diffGradientCalculator=FiniteDiffGradientCalculator.of(of);
            ObjectiveFunctionGradient gradient=diffGradientCalculator.getFiniteDiffGradient();
            double[] derSinPI=gradient.getObjectiveFunctionGradient().value(new double[]{Math.PI});
            Assertions.assertEquals(Math.cos(Math.PI),derSinPI[0], TOL);
        }

}
