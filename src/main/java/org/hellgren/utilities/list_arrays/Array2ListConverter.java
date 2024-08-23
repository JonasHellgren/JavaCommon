package org.hellgren.utilities.list_arrays;

import java.util.ArrayList;
import java.util.List;

public class Array2ListConverter {

    private Array2ListConverter() {
    }

    public static List<Double> convertDoubleArrToList(double[] inVec) {
        List<Double> outList = new ArrayList<>(inVec.length);
        for (double val : inVec) {
            outList.add(val);
        }
        return outList;
    }

    public static List<List<Double>> convertDoubleMatToListOfLists(double[][] inMat) {
        List<List<Double>> outList = new ArrayList<>();
        for (double[] row : inMat) {
            List<Double> innerList = new ArrayList<>();
            for (double val : row) {
                innerList.add(val);
            }
            outList.add(innerList);
        }
        return outList;
    }


}
