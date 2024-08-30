package utilities;

import org.hellgren.utilities.reinforcement_learning.MyRewardListUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyRewardListUtils {

    @Test
    public void testDiscountedSum() {
        List<Double> list = Arrays.asList(10.0, 10.0, 10.0);
        double discountFactor = 0.5;
        double expected = 10 + 5 + 2.5;
        double actual = MyRewardListUtils.discountedSum(list, discountFactor);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testDiscountedElements() {
        List<Double> list = Arrays.asList(1.0, 10.0, 10.0);
        double discountFactor = 0.5;
        List<Double> expected = Arrays.asList(1.0, 5.0, 2.5);
        List<Double> actual = MyRewardListUtils.discountedElements(list, discountFactor);
        assertEquals(expected, actual);
    }

    @Test
    public void testDiscountedElementsReverse() {
        List<Double> list = Arrays.asList(1.0, 10.0, 10.0);
        double discountFactor = 0.5;
        List<Double> expected = Arrays.asList(0.25, 5.0, 10.0);
        List<Double> actual = MyRewardListUtils.discountedElementsReverse(list, discountFactor);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetReturns() {
        List<Double> rewards = Arrays.asList(0.0, 1.0, 1.0);
        List<Double> expected = Arrays.asList(2.0, 2.0, 1.0);
        List<Double> actual = MyRewardListUtils.getReturns(rewards);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDiscountList() {
        int len = 3;
        double discountFactor = 0.5;
        List<Double> expected = Arrays.asList(1.0, 0.5, 0.25);
        List<Double> actual = MyRewardListUtils.getDiscountList(len, discountFactor);
        assertEquals(expected, actual);
    }

    @Test
    public void testCumulativeSum() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> expected = Arrays.asList(1.0, 3.0, 6.0);
        List<Double> actual = MyRewardListUtils.cumulativeSum(values);
        assertEquals(expected, actual);
    }

}
