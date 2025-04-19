package utilities;

import org.hellgren.utilities.math.SoftMax;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestSoftMax {

    @Test
     void testSoftmax_DoubleArray() {
        double[] input = {1.0, 2.0};
        double[] expectedOutput = {0.2689414213699951, 0.7310585786300049};
        double[] actualOutput = SoftMax.softmax(input);
        assertArrayEquals(expectedOutput, actualOutput, 1e-6);
    }

    @Test
     void testSoftmax_List() {
        List<Double> input = Arrays.asList(1.0, 2.0);
        List<Double> expectedOutput = Arrays.asList(0.2689414213699951, 0.7310585786300049);
        List<Double> actualOutput = SoftMax.softmax(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
