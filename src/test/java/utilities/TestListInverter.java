package utilities;

import lombok.extern.java.Log;
import org.hellgren.utilities.list_arrays.ListInverter;
import org.hellgren.utilities.list_arrays.MyArrayUtil;
import org.hellgren.utilities.list_arrays.MyListUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log
class TestListInverter {
    public static final double TOL = 1e-3;

    @Test
    void givenList_thenReversing_thenCorrect() {
        List<Double> list = List.of(0.2, 0.5, 0.3);
        ArrayList<Double> listMutable = new ArrayList<>(list);
        List<Double> listReversed = ListInverter.invert(listMutable);
        double[] arrReversed = ListInverter.invert(MyListUtils.doubleListToArray(list));

        logging(listReversed, arrReversed);

        Assertions.assertTrue(listReversed.get(0) > listReversed.get(1));
        Assertions.assertTrue(listReversed.get(0) > listReversed.get(2));
        Assertions.assertTrue(arrReversed[0] > arrReversed[1]);
        Assertions.assertTrue(arrReversed[0] > arrReversed[2]);
        assertSumIsOne(listReversed, arrReversed);

    }


    @Test
    void givenListNotUniqueElements_thenReversing_thenCorrect() {
        List<Double> list = List.of(0.2, 0.6, 0.2);
        ArrayList<Double> listMutable = new ArrayList<>(list);
        List<Double> listReversed = ListInverter.invert(listMutable);
        double[] arrReversed = ListInverter.invert(MyListUtils.doubleListToArray(list));

        logging(listReversed, arrReversed);

        Assertions.assertTrue(listReversed.get(0) > listReversed.get(1));
        Assertions.assertTrue(listReversed.get(2) > listReversed.get(1));
        Assertions.assertTrue(arrReversed[0] > arrReversed[1]);
        Assertions.assertTrue(arrReversed[2] > arrReversed[1]);
        assertSumIsOne(listReversed, arrReversed);
    }

    @Test
    void givenListOneElement_thenReversing_thenCorrect() {
        List<Double> list = List.of(1d);
        List<Double> listReversed = ListInverter.invert(list);
        double[] arrReversed = ListInverter.invert(MyListUtils.doubleListToArray(list));

        logging(listReversed, arrReversed);

        Assertions.assertEquals(List.of(1d), listReversed);
        Assertions.assertArrayEquals(new double[]{1}, arrReversed);
        assertSumIsOne(listReversed, arrReversed);

    }

    @Test
    void givenListAgain_thenReversing_thenCorrect() {
        List<Double> list = List.of(0.1, 0.2, 0.3, 0.9);
        List<Double> listReversed = ListInverter.invert(list);
        double[] arrReversed = ListInverter.invert(MyListUtils.doubleListToArray(list));

        logging(listReversed, arrReversed);

        double smallest = Collections.min(listReversed);
        double largest = Collections.max(listReversed);
        int idxSmallest = listReversed.indexOf(smallest);
        int idxLargest = listReversed.indexOf(largest);
        Assertions.assertEquals(3, idxSmallest);
        Assertions.assertEquals(0, idxLargest);
        assertSumIsOne(listReversed, arrReversed);

    }

    private static void assertSumIsOne(List<Double> listReversed, double[] arrReversed) {
        Assertions.assertEquals(1, MyArrayUtil.sum(arrReversed), TOL);
        Assertions.assertEquals(1, MyListUtils.sumList(listReversed), TOL);
    }


    private static void logging(List<Double> listReversed, double[] arrReversed) {
        log.info("listReversed = " + listReversed);
        log.fine("arrReversed = " + Arrays.toString(arrReversed));
    }


}
