package org.hellgren.utilities.list_arrays;

import com.google.common.base.Preconditions;

import java.util.Arrays;

public class MyMatrixArrayUtils {

    private MyMatrixArrayUtils() {
    }

    /**
     * Creates a diagonal matrix from a given array of diagonal values.
     *
     * @param diagonalValues the array of values to be placed on the diagonal
     * @return the resulting diagonal matrix
     */

    public static double[][] createDiagonalMatrix(double[] diagonalValues) {
        Preconditions.checkArgument(diagonalValues.length > 0, "Array must not be null or empty");
        int nDim=diagonalValues.length;
        double[][] matrix = new double[nDim][nDim];
        for (int i = 0; i < nDim; i++) {
            matrix[i][i] = diagonalValues[i];
        }
        return matrix;
    }

    public static double findMin(double[][] data) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] < min) {
                    min = data[i][j];
                }
            }
        }
        return min;
    }



    public static double findMax(double[][] data) {
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] > max) {
                    max = data[i][j];
                }
            }
        }
        return max;
    }
}
