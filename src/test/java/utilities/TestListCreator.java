package utilities;

import org.hellgren.utilities.list_arrays.ListCreator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListCreator {


    @Test
    public void testCreateListWithZeroElements() {
        int len = 5;
        List<Double> result = ListCreator.createListWithZeroElements(len);
        assertEquals(len, result.size());
        for (Double value : result) {
            assertEquals(0.0, value, 0.01);
        }
    }

    @Test
    public void testCreateListWithEqualElementValues() {
        int len = 5;
        double value = 2.5;
        List<Double> result = ListCreator.createListWithEqualElementValues(len, value);
        assertEquals(len, result.size());
        for (Double val : result) {
            assertEquals(value, val, 0.01);
        }
    }

    @Test
    public void testCreateFromStartToEndWithStep() {
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
    public void testCreateFromStartWithStepWithNofItems() {
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

}
