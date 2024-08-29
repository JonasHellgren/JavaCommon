package org.hellgren.utilities.linear_regression_batch_fitting;

import com.google.common.base.Preconditions;
import lombok.extern.java.Log;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.conditionals.Conditionals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for creating a data set, which is a pair of a matrix (x) and a vector (y).
 * The matrix is a collection of rows, where each row is a feature vector (x).
 * The vector is a collection of response values (y)
 * The number of rows in the matrix must be equal to the length of the vector.
 * <p>
 * The data set is created by adding points, i.e. pairs of x and y values.
 * The data set can be cleared and a new one created.
 * <p>
 * The class is not thread safe.
 */

@Log
public class DataSetCreator {

    private final List<double[]> xMatList;
    private final List<Double> yVecList;

    public DataSetCreator() {
        xMatList = new ArrayList<>();
        yVecList = new ArrayList<>();
    }

    public void clear() {
        xMatList.clear();
        yVecList.clear();
    }

    public int size() {
        return xMatList.size();
    }

    /**
     * Adds a point to the data set.
     * A point is a pair of a feature vector (x) and a response value (y).
     * The number of features in x must be the same for all points in the data set.
     * The response value y is a scalar value.
     *
     * @param x the feature vector
     * @param y the response value
     */

    public void addPoint(double[] x, Double y) {
        Preconditions.checkNotNull(x, "Input vector x cannot be null");
        Preconditions.checkNotNull(y, "Output value y cannot be null");
        xMatList.add(x);
        yVecList.add(y);
    }


    /**
     * Creates a data set from the points added to this data set creator.
     * The data set is a pair of a matrix and a vector.
     * The matrix has one row per point in the data set, and the vector has one entry per point.
     * The number of columns in the matrix is the same as the length of the feature vector x in the points.
     * The elements of the matrix are the elements of the feature vectors x.
     * The elements of the vector are the response values y.
     *
     * @return the data set
     */

    public Pair<RealMatrix, RealVector> createDataSet() {
        Preconditions.checkArgument(size() == yVecList.size(), "Non correct data size");
        Conditionals.executeIfTrue(xMatList.isEmpty(),
                () -> log.warning("No data available for data set creation"));
        double[][] xMatArray = new double[xMatList.size()][];
        double[] yVecArray = new double[yVecList.size()];
        for (int i = 0; i < xMatList.size(); i++) {
            xMatArray[i] = xMatList.get(i);
            yVecArray[i] = yVecList.get(i);
        }
        return new Pair<>(new Array2DRowRealMatrix(xMatArray), new ArrayRealVector(yVecArray));
    }


    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < xMatList.size(); i++) {
            sb.append("i=").append(i).append(", x=")
                    .append(Arrays.toString(xMatList.get(i)))
                    .append(", y=").append(yVecList.get(i))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

}
