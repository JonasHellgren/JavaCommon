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

/**
 *     This class implements a lower bound constraint on a variable x.

 *     The constraint is:   xi>lowerBound
 */

@Builder
public class LowerBoundConstraint implements ConvexMultivariateRealFunction {

    @NonNull Integer nDim;
    @NonNull Integer variableIndex;
    @NonNull Double lowerBound;
    @Builder.Default
    @Getter  int nIter =0;

    public static LowerBoundConstraint ofSingle(double lb) {
        return LowerBoundConstraint.builder()
                .nDim(1).variableIndex(0).lowerBound(lb)
                .build();
    }

    @Override
    public double value(DoubleMatrix1D input) {
        nIter++;
        return -input.toArray()[variableIndex]+ lowerBound;  //xi>lb
    }

    @Override
    public DoubleMatrix1D gradient(DoubleMatrix1D input) {
        double[] arr= new double[nDim];
        Arrays.fill(arr, 0);
        arr[variableIndex] = -1;
        return DoubleFactory1D.dense.make(arr);
    }

    @Override
    public DoubleMatrix2D hessian(DoubleMatrix1D input) {
        return  new DenseDoubleMatrix2D(new double[nDim][nDim]);
    }

    @Override
    public int getDim() {
        return nDim;
    }
}