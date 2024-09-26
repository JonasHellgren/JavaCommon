package utilities;

import org.hellgren.utilities.math.MovingAverage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMovingAverage {

    @Test
    public void testEmptyList() {
        MovingAverage movingAverage = new MovingAverage(3, new ArrayList<>());
        assertTrue(movingAverage.getFiltered().isEmpty());
    }

    @Test
    public void testListShorterThanWindow() {
        List<Double> inList = List.of(1.0, 2.0);
        MovingAverage movingAverage = new MovingAverage(3, inList);
        List<Double> outList = movingAverage.getFiltered();
        assertEquals(2, outList.size());
        assertEquals(1.0, outList.get(0), 0.01);
        assertEquals(1.5, outList.get(1), 0.01);
    }

    @Test
    public void testListEqualToWindow() {
        List<Double> inList = List.of(1.0, 2.0, 3.0);
        MovingAverage movingAverage = new MovingAverage(3, inList);
        List<Double> outList = movingAverage.getFiltered();
        assertEquals(3, outList.size());
        assertEquals(1.0, outList.get(0), 0.01);
        assertEquals(1.5, outList.get(1), 0.01);
        assertEquals(2.0, outList.get(2), 0.01);
    }

    @Test
    public void testListLongerThanWindow() {
        List<Double> inList = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        MovingAverage movingAverage = new MovingAverage(3, inList);
        List<Double> outList = movingAverage.getFiltered();
        assertEquals(5, outList.size());
        assertEquals(1.0, outList.get(0), 0.01);
        assertEquals(1.5, outList.get(1), 0.01);
        assertEquals(2.0, outList.get(2), 0.01);
        assertEquals(3.0, outList.get(3), 0.01);
        assertEquals(4.0, outList.get(4), 0.01);
    }

    @Test
    public void testAddValue() {
        MovingAverage movingAverage = new MovingAverage(3, new ArrayList<>());
        assertEquals(1.0, movingAverage.addValue(1.0), 0.01);
        assertEquals(1.5, movingAverage.addValue(2.0), 0.01);
        assertEquals(2, movingAverage.addValue(3.0), 0.01);
        assertEquals((2+3+4)/3, movingAverage.addValue(4.0), 0.01);
    }

    @Test
    public void testAverage() {
        MovingAverage movingAverage = new MovingAverage(3, new ArrayList<>());
        movingAverage.addValue(1.0);
        movingAverage.addValue(2.0);
        movingAverage.addValue(3.0);
        assertEquals(2.0, movingAverage.average(), 0.01);
    }

    @Test
    public void testFilled() {
        MovingAverage movingAverage = new MovingAverage(3, new ArrayList<>());
        assertFalse(movingAverage.filled());
        movingAverage.addValue(1.0);
        assertFalse(movingAverage.filled());
        movingAverage.addValue(2.0);
        assertFalse(movingAverage.filled());
        movingAverage.addValue(3.0);
        assertTrue(movingAverage.filled());
    }

}
