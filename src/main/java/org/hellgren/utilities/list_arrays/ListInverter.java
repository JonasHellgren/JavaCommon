package org.hellgren.utilities.list_arrays;

import java.util.ArrayList;
import java.util.List;

public class ListInverter {

    public static final double SMALL = 1e-3;


    private ListInverter() {
    }


    /**
     * Reverses the magnitudes of elements in the list by inverting each element, then normalizing.
     *
     * @param list The original list of doubles.
     * @return A new list with magnitudes reversed based on the inversion and normalization process.
     */

    public static List<Double> invert(List<Double> list) {
        return invert(list,SMALL);
    }

    public static List<Double> invert(List<Double> list, double small) {
        List<Double> invertedValues = new ArrayList<>();
        for (Double value : list) {
            double inverted = 1.0 / Math.max(value, small);
            invertedValues.add(inverted);
        }
        double sum = MyListUtils.sumList(invertedValues);
        return MyListUtils.multiplyListElements(invertedValues, 1 / sum);

    }

    /**
     * Reverses the magnitudes of elements in the array by inverting each element, then normalizing.
     *
     * @param array The original array of doubles.
     * @return A new array with magnitudes reversed based on the inversion and normalization process.
     */

    public static double[] invert(double[] array) {
        return invert(array,SMALL);
    }

        public static double[] invert(double[] array, double small) {
        int len = array.length;
        double[] invertedValues = getInvertedValues(array, len,small);
        double sum = MyArrayUtil.sum(invertedValues);
        return getNormalizedValues(len, invertedValues, sum);
    }

    static double[] getNormalizedValues(int len, double[] invertedValues, double sum) {
        double[] normalizedValues = new double[len];
        for (int i = 0; i < len; i++) {
            normalizedValues[i] = invertedValues[i] / sum;
        }
        return normalizedValues;
    }

    static double[] getInvertedValues(double[] array, int len, double small) {
        double[] invertedValues = new double[len];
        for (int i = 0; i < len; i++) {
            double inverted = 1.0 / Math.max(array[i], small);
            invertedValues[i] = inverted;
        }
        return invertedValues;
    }

}
