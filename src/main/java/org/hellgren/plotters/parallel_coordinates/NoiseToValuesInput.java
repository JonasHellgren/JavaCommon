package org.hellgren.plotters.parallel_coordinates;

import org.hellgren.utilities.random.RandUtils;
import java.util.List;
import java.util.stream.Collectors;

public class NoiseToValuesInput {

    private NoiseToValuesInput() {
    }

    /**
     * Adds random noise to the input values of a list of LineData objects.
     *
     * @param inputData      the list of LineData objects to add noise to
     * @param noiseLevel    the amount of noise to add to the input values
     * @return              the list of LineData objects with noise added to their input values
     */
    public static List<LineData> addNoiseToValuesInput(List<LineData> inputData, double noiseLevel) {
        return inputData.stream()
                .map(data -> {
                    List<Double> noisyInputValues = data.valuesInput().stream()
                            .map(value -> value + generateNoise(noiseLevel))
                            .collect(Collectors.toList());
                    return LineData.of(noisyInputValues, data.valueOutput(), data.category());
                })
                .collect(Collectors.toList());
    }

    private static double generateNoise(double noiseLevel) {
        return RandUtils.getRandomDouble(-noiseLevel,noiseLevel);  // Generate noise in range [-noiseLevel, noiseLevel]
    }
}
