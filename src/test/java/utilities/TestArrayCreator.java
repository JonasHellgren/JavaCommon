package utilities;

import org.hellgren.utilities.list_arrays.ArrayCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestArrayCreator {
    

        @Test
         void testCreateArrayWithSameDoubleNumber() {
            double[] array = ArrayCreator.createArrayWithSameDoubleNumber(5, 10.0);
            assertEquals(5, array.length);
            for (double value : array) {
                assertEquals(10.0, value, 0.0);
            }
        }

        @Test
         void testCreateArrayFromStartAndEnd_Length1() {
            double[] array = ArrayCreator.createArrayFromStartAndEnd(1, 10.0, 20.0);
            assertEquals(1, array.length);
            assertEquals(10.0, array[0], 0.0);
        }

        @Test
         void testCreateArrayFromStartAndEnd_LengthMoreThan1() {
            double[] array = ArrayCreator.createArrayFromStartAndEnd(3, 10.0, 20.0);
            assertEquals(3, array.length);
            assertEquals(10.0, array[0], 0.0);
            assertEquals(15.0, array[1], 0.0);
            assertEquals(20.0, array[2], 0.0);
        }

        @Test
         void testCreateArrayInRange() {
            double[] array = ArrayCreator.createArrayInRange(10.0, 2.0, 20.0);
            assertEquals(6, array.length);
            assertEquals(10.0, array[0], 0.0);
            assertEquals(12.0, array[1], 0.0);
            assertEquals(14.0, array[2], 0.0);
            assertEquals(16.0, array[3], 0.0);
            assertEquals(18.0, array[4], 0.0);
            assertEquals(20.0, array[5], 0.0);
        }

        @Test
         void testCreateArrayFromIncrement() {
            double[] array = ArrayCreator.createArrayFromIncrement(3, 10.0, 2.0);
            assertEquals(3, array.length);
            assertEquals(10.0, array[0], 0.0);
            assertEquals(12.0, array[1], 0.0);
            assertEquals(14.0, array[2], 0.0);
        }

        @Test
         void testCreateArrayFromStartEndAndIncrement() {
            double[] array = ArrayCreator.createArrayFromStartEndAndIncrement(10.0, 20.0, 2.0);
            assertEquals(6, array.length);
            assertEquals(10.0, array[0], 0.0);
            assertEquals(12.0, array[1], 0.0);
            assertEquals(14.0, array[2], 0.0);
            assertEquals(16.0, array[3], 0.0);
            assertEquals(18.0, array[4], 0.0);
            assertEquals(20.0, array[5], 0.0);
        }


     @Test
     public void testDuplicateArray() {
         double[] arr = {1.0, 2.0, 3.0};
         int n = 2;
         double[] expected = {1.0, 2.0, 3.0, 1.0, 2.0, 3.0};
         double[] actual = ArrayCreator.duplicate(arr, n);
         assertArrayEquals(expected, actual, 1e-6);
     }


}
