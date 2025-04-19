package org.hellgren.utilities.math;

import lombok.experimental.UtilityClass;
import org.hellgren.utilities.list_arrays.Array2ListConverter;
import org.hellgren.utilities.list_arrays.List2ArrayConverter;

import java.util.List;

@UtilityClass
public class SoftMax {

    public static List<Double> softmax(List<Double> input) {
        return Array2ListConverter.arrayToList(
                softmax(List2ArrayConverter.convertListToDoubleArr(input)));
    }

    public static double[] softmax(double[] input) {
        double max = Double.NEGATIVE_INFINITY;
        for (double x : input) {
            max = Math.max(max, x);
        }

        double sum = 0;
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = Math.exp(input[i] - max);
            sum += output[i];
        }

        for (int i = 0; i < input.length; i++) {
            output[i] /= sum;
        }

        return output;
    }

}
