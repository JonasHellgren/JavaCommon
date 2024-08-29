package utilities;

import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.list_arrays.MyArrayUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyArrayUtil {
    @Test
    public void testSum() {
        double[] array = {1, 2, 3, 4, 5};
        double expected = 15;
        double actual = MyArrayUtil.sum(array);
        assertEquals(expected, actual);
    }

    @Test
    public void testAverage() {
        double[] array = {1, 2, 3, 4, 5};
        double expected = 3;
        double actual = MyArrayUtil.average(array);
        assertEquals(expected, actual);
    }

    @Test
    public void testAverage_EmptyArray() {
        double[] array = {};
        assertThrows(IllegalArgumentException.class, () -> MyArrayUtil.average(array));
    }

    @Test
    public void testFindMin() {
        Double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Double expected = 1.0;
        Double actual = MyArrayUtil.findMin(data);
        assertEquals(expected, actual);
    }



    @Test
    public void testClip() {
        double[] array = {1, 2, 3, 4, 5};
        double min = 2;
        double max = 4;
        double[] expected = {2, 2, 3, 4, 4};
        double[] actual = MyArrayUtil.clip(array, min, max);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIsDoubleArraysEqual() {
        double[] array1 = {1, 2, 3};
        double[] array2 = {1, 2, 3};
        boolean expected = true;
        boolean actual = MyArrayUtil.isDoubleArraysEqual(array1, array2, 0.1);
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyWithValue() {
        double[] array = {1, 2, 3};
        double multiplier = 2;
        double[] expected = {2, 4, 6};
        double[] actual = MyArrayUtil.multiplyWithValue(array, multiplier);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testNegate() {
        double[] array = {1, 2, 3};
        double[] expected = {-1, -2, -3};
        double[] actual = MyArrayUtil.negate(array);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testConvertMapToArrays() {
        Map<Double, Double> map = new HashMap<>();
        map.put(1.0, 2.0);
        map.put(3.0, 4.0);
        Pair<double[], double[]> expected = Pair.create(new double[]{1, 3}, new double[]{2, 4});
        Pair<double[], double[]> actual = MyArrayUtil.convertMapToArrays(map);
        assertArrayEquals(expected.getFirst(), actual.getFirst());
        assertArrayEquals(expected.getSecond(), actual.getSecond());
    }

}
