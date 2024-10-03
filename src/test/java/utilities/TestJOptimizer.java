package utilities;

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleMatrix1D;
import org.hellgren.utilities.joptimizer.SumConstraint;
import org.hellgren.utilities.math.MyMathUtils;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class TestJOptimizer {


    @Test
     void testValue_SumLessThanLimit() {
        SumConstraint constraint = SumConstraint.ofSumIsMaxLimited(2, 10.0);
        DoubleMatrix1D input = DoubleFactory1D.dense.make(new double[] {3.0, 4.0});
        notViolated(constraint, input);
    }


    @Test
     void testValue_SumGreaterThanLimit() {
        SumConstraint constraint = SumConstraint.ofSumIsMinLimited(2, 0.0);
        DoubleMatrix1D input = DoubleFactory1D.dense.make(new double[] {3.0, 4.0});
        notViolated(constraint, input);
    }


    private static void notViolated(SumConstraint constraint, DoubleMatrix1D input) {
        double result = constraint.value(input);
        assertTrue(MyMathUtils.isNeg(result));
    }

}
