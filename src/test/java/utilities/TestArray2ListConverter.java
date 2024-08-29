package utilities;

import org.hellgren.utilities.list_arrays.Array2ListConverter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestArray2ListConverter {


    @Test
    void testConvertDoubleArrToList() {
        double[] inputArray = {1.0, 2.0, 3.0};
        List<Double> expectedList = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> actualList = Array2ListConverter.arrayToList(inputArray);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testConvertDoubleArrToList_EmptyArray() {
        double[] inputArray = {};
        List<Double> expectedList = List.of();
        List<Double> actualList = Array2ListConverter.arrayToList(inputArray);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testConvertDoubleMatToListOfLists() {
        double[][] inputMatrix = {{1.0, 2.0}, {3.0, 4.0}};
        List<List<Double>> expectedList = Arrays.asList(
                Arrays.asList(1.0, 2.0),
                Arrays.asList(3.0, 4.0)
        );
        List<List<Double>> actualList = Array2ListConverter.matrixToListOfLists(inputMatrix);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testConvertDoubleMatToListOfLists_EmptyMatrix() {
        double[][] inputMatrix = {};
        List<List<Double>> expectedList = List.of();
        List<List<Double>> actualList = Array2ListConverter.matrixToListOfLists(inputMatrix);
        assertEquals(expectedList, actualList);
    }
}

