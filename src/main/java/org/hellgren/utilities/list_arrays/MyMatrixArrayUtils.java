package org.hellgren.utilities.list_arrays;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyMatrixArrayUtils {

    /**
     * Finds the minimum value in a 2D array of doubles.
     *
     * @param data the 2D array of doubles
     * @return the minimum value in the array
     */
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

    /**
     * Finds the maximum value in a 2D array of doubles.
     *
     * @param data the 2D array of doubles
     * @return the maximum value in the array
     */
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
