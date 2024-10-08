package org.hellgren.plotters.parallel_coordinates;
import org.apache.commons.lang3.ArrayUtils;
import org.hellgren.utilities.list_arrays.MyArrayUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NormalizeLineData {

    private NormalizeLineData() {
    }
    /**
     * Normalizes a list of LineData objects by scaling their input values and output values to a common range.
     *
     * @param inputData	a list of LineData objects to be normalized
     * @return         	a new list of LineData objects with normalized input and output values
     */
    public static List<LineData> normalize(List<LineData> inputData) {
        int numInputVariables = inputData.get(0).valuesInput().size();

        double[] minInputValues = new double[numInputVariables];
        double[] maxInputValues = new double[numInputVariables];
        initializeMinMaxArrays(minInputValues, maxInputValues);

        double[] outputValues = new double[inputData.size()];
        for (LineData data : inputData) {
            for (int i = 0; i < numInputVariables; i++) {
                double inputValue = data.valuesInput().get(i);
                minInputValues[i] = Math.min(minInputValues[i], inputValue);
                maxInputValues[i] = Math.max(maxInputValues[i], inputValue);
            }
            double outputValue = data.valueOutput();
            outputValues[inputData.indexOf(data)]=outputValue;
        }
        double minOutputValue= MyArrayUtil.findMinInPrimitiveArray(outputValues);
        double maxOutputValue= MyArrayUtil.findMaxInPrimitiveArray(outputValues);
        return normalize(inputData, minInputValues, maxInputValues, minOutputValue, maxOutputValue);
    }

    public static List<LineData> normalize(List<LineData> inputData,
                                           double[] minInputValues,
                                           double[] maxInputValues,
                                           double minOutputValue,
                                           double maxOutputValue) {
        return inputData.stream()
                .map(data -> new LineData(
                        normalizeValues(data.valuesInput(), minInputValues, maxInputValues),
                        normalizeValue(data.valueOutput(), minOutputValue, maxOutputValue),
                        data.category()
                ))
                .toList();
    }

    private static void initializeMinMaxArrays(double[] minInputs, double[] maxInputs) {
        for (int i = 0; i < minInputs.length; i++) {
            minInputs[i] = Double.MAX_VALUE;
            maxInputs[i] = Double.MIN_VALUE;
        }
    }

    private static List<Double> normalizeValues(List<Double> values, double[] minInputs, double[] maxInputs) {
        List<Double> normalizedValues = new ArrayList<>(values.size());
        for (int i = 0; i < values.size(); i++) {
            normalizedValues.add(normalizeValue(values.get(i), minInputs[i], maxInputs[i]));
        }
        return normalizedValues;
    }

    private static double normalizeValue(double value, double min, double max) {
        return (value - min) / (max - min);
    }
}
