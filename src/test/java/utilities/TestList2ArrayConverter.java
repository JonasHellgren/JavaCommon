package utilities;

import org.hellgren.utilities.list_arrays.List2ArrayConverter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

 class TestList2ArrayConverter {


    @Test
     void testConvertListToDoubleArr() {
        // Test with a non-empty list
        List<Double> inList = new ArrayList<>();
        inList.add(1.0);
        inList.add(2.0);
        inList.add(3.0);
        double[] expected = {1.0, 2.0, 3.0};
        double[] actual = List2ArrayConverter.convertListToDoubleArr(inList);
        assertArrayEquals(expected, actual);

        // Test with an empty list
        inList = new ArrayList<>();
        expected = new double[0];
        actual = List2ArrayConverter.convertListToDoubleArr(inList);
        assertArrayEquals(expected, actual);
    }

    @Test
     void testConvertListWithListToDoubleMat() {
        // Test with a non-empty list
        List<List<Double>> inList = new ArrayList<>();
        List<Double> innerList1 = new ArrayList<>();
        innerList1.add(1.0);
        innerList1.add(2.0);
        List<Double> innerList2 = new ArrayList<>();
        innerList2.add(3.0);
        innerList2.add(4.0);
        inList.add(innerList1);
        inList.add(innerList2);
        double[][] expected = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] actual = List2ArrayConverter.convertListWithListToDoubleMat(inList);
        assertArrayEquals(expected, actual);

        // Test with an empty list
        inList = new ArrayList<>();
        expected = new double[0][0];
        actual = List2ArrayConverter.convertListWithListToDoubleMat(inList);
        assertArrayEquals(expected, actual);
    }

}
