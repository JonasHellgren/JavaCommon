package utilities;


import org.hellgren.utilities.list_arrays.MyMatrixArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


 class TestMyMatrixArrayUtils {

    @Test
     void testFindMin() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        double expected = 1.0;
        double actual = MyMatrixArrayUtils.findMin(data);
        assertEquals(expected, actual, 0.01);
    }

    @Test
     void testFindMin_EmptyArray() {
        double[][] data = {};
        double expected = Double.POSITIVE_INFINITY;
        double actual = MyMatrixArrayUtils.findMin(data);
        assertEquals(expected, actual, 0.01);
    }

    @Test
     void testFindMin_NullArray() {
        double[][] data = null;
        try {
            MyMatrixArrayUtils.findMin(data);
            assert false;
        } catch (NullPointerException e) {
            assert true;
        }
    }

    @Test
     void testFindMax() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        double expected = 4.0;
        double actual = MyMatrixArrayUtils.findMax(data);
        assertEquals(expected, actual, 0.01);
    }

    @Test
     void testFindMax_EmptyArray() {
        double[][] data = {};
        double expected = Double.NEGATIVE_INFINITY;
        double actual = MyMatrixArrayUtils.findMax(data);
        assertEquals(expected, actual, 0.01);
    }

    @Test
     void testFindMax_NullArray() {
        double[][] data = null;
        try {
            MyMatrixArrayUtils.findMax(data);
            assert false;
        } catch (NullPointerException e) {
            assert true;
        }
    }


}

