package utilities;

import org.hellgren.utilities.list_arrays.MyListUtils;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class TestMyListUtils {


    @Test
     void testFindMin() {
        List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        OptionalDouble min = MyListUtils.findMin(list);
        assertTrue(min.isPresent());
        assertEquals(1.0, min.getAsDouble(), 0.01);
    }

    @Test
     void testFindMax() {
        List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        OptionalDouble max = MyListUtils.findMax(list);
        assertTrue(max.isPresent());
        assertEquals(5.0, max.getAsDouble(), 0.01);
    }

    @Test
     void testFindAverage() {
        List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        OptionalDouble average = MyListUtils.findAverage(list);
        assertTrue(average.isPresent());
        assertEquals(3.0, average.getAsDouble(), 0.01);
    }

    @Test
     void testSumList() {
        List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        double sum = MyListUtils.sumList(list);
        assertEquals(15.0, sum, 0.01);
    }

    @Test
     void testFindFirst() {
        List<String> list = Arrays.asList("a", "b", "c");
        Optional<String> first = MyListUtils.findFirst(list);
        assertTrue(first.isPresent());
        assertEquals("a", first.get());
    }

    @Test
     void testFindEnd() {
        List<String> list = Arrays.asList("a", "b", "c");
        Optional<String> end = MyListUtils.findEnd(list);
        assertTrue(end.isPresent());
        assertEquals("c", end.get());
    }

    @Test
     void testSumListElements() {
        List<Double> listA = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> listB = Arrays.asList(4.0, 5.0, 6.0);
        List<Double> sum = MyListUtils.sumListElements(listA, listB);
        assertEquals(Arrays.asList(5.0, 7.0, 9.0), sum);
    }

    @Test
     void testDotProduct() {
        List<Double> listA = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> listB = Arrays.asList(4.0, 5.0, 6.0);
        double dotProduct = MyListUtils.dotProduct(listA, listB);
        assertEquals(32.0, dotProduct, 0.01);
    }

     @Test
      void testFindMinInt() {
         List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
         OptionalInt min = MyListUtils.findMinInt(list);
         assertTrue(min.isPresent());
         assertEquals(1, min.getAsInt());
     }

     @Test
      void testFindMaxInt() {
         List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
         OptionalInt max = MyListUtils.findMaxInt(list);
         assertTrue(max.isPresent());
         assertEquals(5, max.getAsInt());
     }

     @Test
      void testFindAverageInt() {
         List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
         OptionalDouble average = MyListUtils.findAverageInt(list);
         assertTrue(average.isPresent());
         assertEquals(3.0, average.getAsDouble(), 0.01);
     }

     @Test
      void testSumIntegerList() {
         List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
         int sum = MyListUtils.sumIntegerList(list);
         assertEquals(15, sum);
     }

     @Test
      void testSumDoubleList() {
         List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
         double sum = MyListUtils.sumDoubleList(list);
         assertEquals(15.0, sum, 0.01);
     }

     @Test
      void testAddScalarToListElements() {
         List<Double> list = Arrays.asList(1.0, 2.0, 3.0);
         double scalar = 2.0;
         List<Double> result = MyListUtils.addScalarToListElements(list, scalar);
         assertEquals(Arrays.asList(3.0, 4.0, 5.0), result);
     }

     @Test
      void testMultiplyListElements() {
         List<Double> list = Arrays.asList(1.0, 2.0, 3.0);
         double scalar = 2.0;
         List<Double> result = MyListUtils.multiplyListElements(list, scalar);
         assertEquals(Arrays.asList(2.0, 4.0, 6.0), result);
     }

     @Test
      void testElementProduct() {
         List<Double> listA = Arrays.asList(1.0, 2.0, 3.0);
         List<Double> listB = Arrays.asList(4.0, 5.0, 6.0);
         List<Double> result = MyListUtils.elementProduct(listA, listB);
         assertEquals(Arrays.asList(4.0, 10.0, 18.0), result);
     }

     @Test
      void testElementSubtraction() {
         List<Double> listA = Arrays.asList(1.0, 2.0, 3.0);
         List<Double> listB = Arrays.asList(4.0, 5.0, 6.0);
         List<Double> result = MyListUtils.elementSubtraction(listA, listB);
         assertEquals(Arrays.asList(3.0, 3.0, 3.0), result);
     }

     @Test
      void testEveryItemAbsolute() {
         List<Double> list = Arrays.asList(-1.0, 2.0, -3.0);
         List<Double> result = MyListUtils.everyItemAbsolute(list);
         assertEquals(Arrays.asList(1.0, 2.0, 3.0), result);
     }

     @Test
      void testToArray() {
         List<Double> list = Arrays.asList(1.0, 2.0, 3.0);
         double[] array = MyListUtils.toArray(list);
         assertArrayEquals(new double[] {1.0, 2.0, 3.0}, array, 0.01);
     }

     @Test
      void testAreDoubleArraysEqual() {
         double[] arrayA = new double[] {1.0, 2.0, 3.0};
         double[] arrayB = new double[] {1.0, 2.0, 3.0};
         assertTrue(MyListUtils.areDoubleArraysEqual(arrayA, arrayB, 0.01));
     }

     @Test
      void testMerge() {
         List<String> listA = Arrays.asList("a", "b");
         List<String> listB = Arrays.asList("c", "d");
         List<String> result = MyListUtils.merge(listA, listB);
         assertEquals(Arrays.asList("a", "b", "c", "d"), result);
     }

     @Test
      void testDiff() {
         List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0);
         List<Double> result = MyListUtils.diff(list);
         assertEquals(Arrays.asList(1.0, 1.0, 1.0), result);
     }

    
}
