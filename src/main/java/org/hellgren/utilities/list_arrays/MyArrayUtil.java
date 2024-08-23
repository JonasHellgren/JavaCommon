package org.hellgren.utilities.list_arrays;

import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.math.MyMathUtils;
import java.util.*;
import java.util.stream.DoubleStream;

public class MyArrayUtil {

    private MyArrayUtil() {
    }

    public static double sum(double[] array) {
        return Arrays.stream(array).sum();
    }

    public static double[] createArrayWithSameDoubleNumber(int length, double value) {
        double[] array = new double[length];
        Arrays.fill(array, value);
        return array;
    }

    public static double[] createArrayInRange(double start,double step, double end) {
        long count = (long) Math.ceil((end - start) / step) + 1;
        return DoubleStream.iterate(start, n -> n + step).limit(count).toArray();
    }

    public static double[] clip(double[] x, double min, double max) {
        for (int i = 0; i < x.length; i += 1) {
            x[i] = MyMathUtils.clip(x[i], min, max);
        }
        return x;
    }

    public static boolean isDoubleArraysEqual(double[] x, double[] y, double tol) {
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; i += 1) {
            if (Math.abs((y[i] - x[i])) > tol) {
                return false;
            }
        }
        return true;
    }


    public static double[]  multWithValue(double[] x,  double multiplier) {
       return   DoubleStream.of(x)
                .map(v -> v * multiplier)
                .toArray();
    }

    public static Double findMin(Double[][] data) {
        return Arrays.stream(data)
                .flatMap(Arrays::stream)
                .filter(n -> !Objects.isNull(n))
                .min(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("The array must not be null or empty"));
    }

    public static Double findMax(Double[][] data) {
        return Arrays.stream(data)
                .flatMap(Arrays::stream)
                .filter(n -> !Objects.isNull(n))
                .max(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("The array must not be null or empty"));
    }

    public static Pair<double[], double[]> convertMapToArrays(Map<Double, Double> map) {
         List<Double> xList = new ArrayList<>(map.keySet());
        Collections.sort(xList);

        List<Double> yList = new ArrayList<>();
        for (Double x : xList) {
            yList.add(map.get(x));  // Arrange y values according to the sorted x values
        }

        double[] xArray = xList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArray = yList.stream().mapToDouble(Double::doubleValue).toArray();
        return Pair.create(xArray, yArray);
    }
}
