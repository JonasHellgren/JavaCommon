package org.hellgren.utilities.joptimizer;

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

@Builder

/**
 * Constraints the sum of the variables. The sum can be limited either to be below a certain limit or
 * above a certain limit. The constraint is linear and the Hessian is zero.
 */

public class SumConstraint implements ConvexMultivariateRealFunction {

    @NonNull Integer nDim;
    @NonNull Boolean isSumMaxLimited;
    @NonNull Double limit;

    @Builder.Default
    @Getter
    int nIter = 0;

    /**
     * Creates a new SumConstraint that limits the sum of variables to be below a certain limit.
     *
     * @param nDim the number of dimensions (i.e., variables) in the constraint
     * @param limit the maximum allowed sum of the variables
     * @return a new SumConstraint instance
     */

    public static SumConstraint ofSumIsMaxLimited(int nDim, double limit) {
        return SumConstraint.builder()
                .nDim(nDim)
                .isSumMaxLimited(true)
                .limit(limit)
                .build();
    }

    /**
     * Creates a new SumConstraint that limits the sum of variables to be above a certain limit.
     *
     * @param nDim the number of dimensions (i.e., variables) in the constraint
     * @param limit the minimum allowed sum of the variables
     * @return a new SumConstraint instance
     */

    public static SumConstraint ofSumIsMinLimited(int nDim, double limit) {
        return SumConstraint.builder()
                .nDim(nDim)
                .isSumMaxLimited(false)
                .limit(limit)
                .build();
    }


    @Override
    public double value(DoubleMatrix1D dm) {
        nIter++;
        return Boolean.TRUE.equals(isSumMaxLimited)
                ? Arrays.stream(dm.toArray()).sum() - limit   //sum<limit  <=> sum-limit<0
                : limit-Arrays.stream(dm.toArray()).sum();    //sum>limit <=> limit-sum<0
    }

    @Override
    public DoubleMatrix1D gradient(DoubleMatrix1D input) {
        double[] arr = new double[nDim];
        double gradElem=Boolean.TRUE.equals(isSumMaxLimited)?1:-1;
        Arrays.fill(arr, gradElem);
        return DoubleFactory1D.dense.make(arr);
    }

    @Override
    public DoubleMatrix2D hessian(DoubleMatrix1D input) {
        return new DenseDoubleMatrix2D(new double[nDim][nDim]);
    }

    @Override
    public int getDim() {
        return nDim;
    }
}
