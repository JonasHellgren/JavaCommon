package utilities;


import org.hellgren.utilities.list_arrays.SetCreator;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSetCreator {

    @Test
    public void testCreateFromStartToEndWithStep() {
        double start = 1.0;
        double end = 5.0;
        double step = 1.0;
        Set<Double> result = SetCreator.createFromStartToEndWithStep(start, end, step);
        assertEquals(5, result.size());
        assertTrue(result.contains(1.0));
        assertTrue(result.contains(2.0));
        assertTrue(result.contains(3.0));
        assertTrue(result.contains(4.0));
        assertTrue(result.contains(5.0));
    }

    @Test
    public void testCreateFromStartToEndWithStep_StepLargerThanRange() {
        double start = 1.0;
        double end = 5.0;
        double step = 10.0;
        Set<Double> result = SetCreator.createFromStartToEndWithStep(start, end, step);
        assertEquals(1, result.size());
        assertTrue(result.contains(1.0));
    }

    @Test
    public void testCreateFromStartToEndWithStep_NegativeStep() {
        double start = 5.0;
        double end = 1.0;
        double step = -1.0;
        Set<Double> result = SetCreator.createFromStartToEndWithStep(start, end, step);
        System.out.println("result = " + result);
        assertEquals(5, result.size());
        assertTrue(result.contains(5.0));
        assertTrue(result.contains(4.0));
        assertTrue(result.contains(3.0));
        assertTrue(result.contains(2.0));
        assertTrue(result.contains(1.0));
    }

    @Test
    public void testCreateFromStartWithStepWithNofItems() {
        double start = 1.0;
        double step = 1.0;
        int nItems = 5;
        Set<Double> result = SetCreator.createFromStartWithStepWithNofItems(start, step, nItems);
        assertEquals(5, result.size());
        assertTrue(result.contains(1.0));
        assertTrue(result.contains(2.0));
        assertTrue(result.contains(3.0));
        assertTrue(result.contains(4.0));
        assertTrue(result.contains(5.0));
    }

    @Test
    public void testCreateFromStartWithStepWithNofItems_ZeroItems() {
        double start = 1.0;
        double step = 1.0;
        int nItems = 0;
        Set<Double> result = SetCreator.createFromStartWithStepWithNofItems(start, step, nItems);
        assertEquals(0, result.size());
    }
}