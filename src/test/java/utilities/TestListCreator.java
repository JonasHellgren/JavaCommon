package utilities;

import org.hellgren.utilities.list_arrays.ListCreator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestListCreator {


    @Test
     void testCreateListWithZeroElements() {
        int len = 5;
        List<Double> result = ListCreator.createListWithZeroElements(len);
        assertEquals(len, result.size());
        for (Double value : result) {
            assertEquals(0.0, value, 0.01);
        }
    }

    @Test
     void testCreateListWithEqualElementValues() {
        int len = 5;
        double value = 2.5;
        List<Double> result = ListCreator.createListWithEqualElementValues(len, value);
        assertEquals(len, result.size());
        for (Double val : result) {
            assertEquals(value, val, 0.01);
        }
    }

    @Test
     void testCreateFromStartToEndWithStep() {
        double start = 1.0;
        double end = 5.0;
        double step = 1.0;
        List<Double> result = ListCreator.createFromStartToEndWithStep(start, end, step);
        assertEquals(5, result.size());
        assertEquals(start, result.get(0), 0.01);
        assertEquals(end, result.get(result.size() - 1), 0.01);
        for (int i = 1; i < result.size(); i++) {
            assertEquals(result.get(i - 1) + step, result.get(i), 0.01);
        }
    }


    @Test
     void testCreateFromStartWithStepWithNofItems() {
        double start = 1.0;
        double step = 1.0;
        int nItems = 5;
        List<Double> result = ListCreator.createFromStartWithStepWithNofItems(start, step, nItems);
        assertEquals(nItems, result.size());
        assertEquals(start, result.get(0), 0.01);
        for (int i = 1; i < result.size(); i++) {
            assertEquals(result.get(i - 1) + step, result.get(i), 0.01);
        }
    }

     @Test
      void testCreateFromStartToEndWithNofItems_ZeroItems() {
         double start = 1.0;
         double end = 10.0;
         int nItems = 0;
         List<Double> result = ListCreator.createFromStartToEndWithNofItems(start, end, nItems);
         assertTrue(result.isEmpty());
     }

     @Test
      void testCreateFromStartToEndWithNofItems_OneItem() {
         double start = 1.0;
         double end = 10.0;
         int nItems = 1;
         List<Double> result = ListCreator.createFromStartToEndWithNofItems(start, end, nItems);
         assertEquals(1, result.size());
         assertEquals(start, result.get(0), 0.01);
     }

     @Test
      void testCreateFromStartToEndWithNofItems_MultipleItems() {
         double start = 1.0;
         double end = 10.0;
         int nItems = 5;
         List<Double> result = ListCreator.createFromStartToEndWithNofItems(start, end, nItems);
         assertEquals(nItems, result.size());
         assertEquals(start, result.get(0), 0.01);
         assertEquals(end, result.get(result.size() - 1), 0.01);
         for (int i = 1; i < result.size(); i++) {
             double expectedValue = start + (i * (end - start) / (nItems - 1));
             assertEquals(expectedValue, result.get(i), 0.01);
         }
     }

    @Test
    void testCreateFromStartToEndWithNofItems0to1_MultipleItems() {
        double start = 0.0;
        double end = 1.0;
        int nItems = 10;
        List<Double> result = ListCreator.createFromStartToEndWithNofItems(start, end, nItems);
        assertEquals(nItems, result.size());
    }


     @Test
      void testCreateFromStartToEndWithNofItems_StartEqualsEnd() {
         double start = 5.0;
         double end = 5.0;
         int nItems = 5;
         List<Double> result = ListCreator.createFromStartToEndWithNofItems(start, end, nItems);
         assertEquals(nItems, result.size());
         for (int i = 0; i < result.size(); i++) {
             assertEquals(start, result.get(i), 0.01);
         }
     }

     @Test
      void testCreateFromStartToEndWithNofItems_NegativeRange() {
         double start = 10.0;
         double end = -10.0;
         int nItems = 5;
         List<Double> result = ListCreator.createFromStartToEndWithNofItems(start, end, nItems);
         System.out.println("result = " + result);
         assertEquals(nItems, result.size());
         assertEquals(start, result.get(0), 0.01);
         assertEquals(end, result.get(result.size() - 1), 0.01);
         for (int i = 1; i < result.size(); i++) {
             double expectedValue = start + (i * (end - start) / (nItems - 1));
             assertEquals(expectedValue, result.get(i), 0.01);
         }
     }

}
