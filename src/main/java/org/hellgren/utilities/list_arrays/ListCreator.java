package org.hellgren.utilities.list_arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * A utility class for creating lists of doubles with various properties.
 */
public class ListCreator {

    /**
     * Creates a list of doubles with the specified length, all initialized to 0.
     *
     * @param len the length of the list to create
     * @return a list of doubles with the specified length, all initialized to 0
     */
    public static List<Double> createListWithZeroElements(int len) {
        // Delegate to createListWithEqualElementValues with value 0
        return createListWithEqualElementValues(len, 0);
    }

    /**
     * Creates a list of doubles with the specified length, all initialized to the specified value.
     *
     * @param len   the length of the list to create
     * @param value the value to initialize all elements to
     * @return a list of doubles with the specified length, all initialized to the specified value
     */
    public static List<Double> createListWithEqualElementValues(int len, double value) {
        // Use Collections.nCopies to create a list with the specified length and value
        return new ArrayList<>(Collections.nCopies(len, value));
    }

    /**
     * Creates a list of doubles starting from the specified start value, incrementing by the specified step,
     * and ending at or below the specified end value.
     *
     * @param start the starting value of the list
     * @param end   the ending value of the list (inclusive)
     * @param step  the increment between each element
     * @return a list of doubles starting from the specified start value, incrementing by the specified step,
     * and ending at or below the specified end value
     */
    public static List<Double> createFromStartToEndWithStep(double start, double end, double step) {
        // Use DoubleStream.iterate to generate the sequence of values
        return DoubleStream.iterate(start, d -> d <= end, d -> d + step)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of doubles starting from the specified start value, incrementing by the specified step,
     * and containing the specified number of elements.
     *
     * @param start    the starting value of the list
     * @param step     the increment between each element
     * @param nItems   the number of elements to generate
     * @return a list of doubles starting from the specified start value, incrementing by the specified step,
     * and containing the specified number of elements
     */
    public static List<Double> createFromStartWithStepWithNofItems(double start, double step, int nItems) {
        // Use Stream.iterate to generate the sequence of values
        return Stream.iterate(start, value -> value + step) // Start with 'start' and add 'step' for each subsequent element
                .limit(nItems)                        // Limit the sequence to 'nItems' elements
                .toList();
    }

}