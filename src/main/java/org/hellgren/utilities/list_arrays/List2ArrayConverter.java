package org.hellgren.utilities.list_arrays;

import java.util.List;

public class List2ArrayConverter {

    private List2ArrayConverter() {
    }

    public static double[] convertListToDoubleArr(List<Double> inList) {
        double[] outArray = new double[inList.size()];
        for (int i = 0; i < inList.size(); i++) {
            outArray[i] = inList.get(i); // Auto-unboxing converts Double to double
        }
        return outArray;
    }

        public static double[][] convertListWithListToDoubleMat(List<List<Double>> inList) {
        if (inList.isEmpty()) {
            return new double[0][0]; // Return an empty 2D array if the input list is empty
        }
        double[][] outArray = new double[inList.size()][];
        for (int i = 0; i < inList.size(); i++) {
            List<Double> innerList = inList.get(i);
            outArray[i] = new double[innerList.size()];
            for (int j = 0; j < innerList.size(); j++) {
                outArray[i][j] = innerList.get(j); // Auto-unboxing converts Double to double
            }
        }
        return outArray;
    }
}
