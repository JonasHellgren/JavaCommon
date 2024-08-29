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
 * xi-ub<0
 */

/**
 *     This class implements an upper bound constraint on a variable x.
 *     The constraint is:   xi<ub
 */

@Builder

public class UpperBoundConstraint implements ConvexMultivariateRealFunction {

    @NonNull Integer nDim;
    @NonNull Integer variableIndex;
    @NonNull Double upperBound;
    @Builder.Default
    @Getter  int nIter =0;

    public static UpperBoundConstraint ofSingle(double ub) {
        return UpperBoundConstraint.builder().nDim(1).variableIndex(0).upperBound(ub).build();
    }

    @Override
    public double value(DoubleMatrix1D dm) {
        nIter++;
        return dm.toArray()[variableIndex]- upperBound;  //xi-ub<0
    }

    @Override
    public DoubleMatrix1D gradient(DoubleMatrix1D input) {
        double[] arr= new double[nDim];
        Arrays.fill(arr, 0);
        arr[variableIndex] = 1;
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