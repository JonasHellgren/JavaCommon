package org.hellgren.utilities.list_arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetCreator {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SetCreator() {
        // Prevent instantiation of this class
    }

    /**
     * Creates a set of doubles starting from the specified start value, incrementing by the specified step,
     * and ending at or below the specified end value.
     *
     * @param start the starting value of the set
     * @param end   the ending value of the set (inclusive)
     * @param step  the increment between each element
     * @return a set of doubles starting from the specified start value, incrementing by the specified step,
     * and ending at or below the specified end value
     */
    public static Set<Double> createFromStartToEndWithStep(double start, double end, double step) {
        return new HashSet<>(ListCreator.createFromStartToEndWithStep(start, end, step));
    }

    /**
     * Creates a set of doubles starting from the specified start value, incrementing by the specified step,
     * and containing the specified number of elements.
     *
     * @param start    the starting value of the set
     * @param step     the increment between each element
     * @param nItems   the number of elements to generate
     * @return a set of doubles starting from the specified start value, incrementing by the specified step,
     * and containing the specified number of elements
     */
    public static Set<Double> createFromStartWithStepWithNofItems(double start, double step, int nItems) {
        return new HashSet<>(ListCreator.createFromStartWithStepWithNofItems(start, step, nItems));
    }

    public static Set<Integer> createFromStartWithStepWithNofItems(int start, int step, int nItems) {
        return new HashSet<>(ListCreator.createFromStartWithStepWithNofItems(start, step, nItems));
    }


    public static Set<Double> createFromStartToEndWithNofTimes(double start, double end, int nItems) {
        return new HashSet<>(ListCreator.createFromStartToEndWithNofItems(start, end, nItems));
    }

}
