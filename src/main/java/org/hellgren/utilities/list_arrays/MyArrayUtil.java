package org.hellgren.utilities.list_arrays;

import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.math.MyMathUtils;

import java.math.BigDecimal;
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

    private static void throwIfZeroLength(int length) {
        if (length == 0 ) {
            throw new IllegalArgumentException("Length is zero");
        }
    }

    private static void throwIfBadLength(int length) {
        if (length <2) {
            throw new IllegalArgumentException("Length must be larger than 1");
        }
    }
    public static final double REST_TOL = 1e-3;

    private static void throwIfLargeRest(double rest) {
        if (Math.abs(rest)> REST_TOL) {
            throw new IllegalArgumentException("start, end and increment gives a rest, rest="+rest);
        }
    }


    public static double[] negate(double[] array) {
        double[] negatedArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            negatedArray[i] = -array[i];  // Negate each element
        }
        return negatedArray;
    }


    public static double[] createArrayFromStartAndEnd(int length, double start, double end) {
        throwIfZeroLength(length);
        double[] array = new double[length];

        if (length==1) {
            array[0]=start;
            return array;
        }

        double increment=(end-start)/(length-1);
        for (int i = 0; i < length; i++) {
            array[i] = start + i * increment;
        }
        return array;
    }


    /***
     *length=3, start=0, increment=0.5 => array = [0.0, 0.5, 1.0]
     */

    public static double[] createArrayFromIncrement(int length, double start, double increment) {
        throwIfBadLength(length);
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = start + i * increment;
        }
        return array;
    }


    /***
     *start=0, end=1, increment=0.5 => array = [0.0, 0.5, 1.0]
     */

    public static double[] createArrayFromStartEndAndIncrement(double start, double end, double increment) {
        int length= (int) ((end-start)/increment)+1;
        //double rest=(end-start) % increment;

        BigDecimal remainder = BigDecimal.valueOf(end-start).remainder(BigDecimal.valueOf(increment));
        throwIfBadLength(length);
        throwIfLargeRest(remainder.doubleValue());
        return createArrayFromIncrement(length,start,increment);
    }


}
