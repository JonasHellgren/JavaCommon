package utilities;

import org.apache.commons.math3.util.Pair;
import org.hellgren.utilities.vector_algebra.ArrayMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArrayMatrix {

    @Test
    public void testTransposeMatrix_EmptyMatrix() {
        int[][] matrix = new int[0][0];
        int[][] expected = new int[0][0];
        int[][] actual = ArrayMatrix.transposeMatrix(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTransposeMatrix_SquareMatrix() {
        int[][] matrix = {{1, 2}, {3, 4}};
        int[][] expected = {{1, 3}, {2, 4}};
        int[][] actual = ArrayMatrix.transposeMatrix(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTransposeMatrix_RectangularMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        int[][] expected = {{1, 4}, {2, 5}, {3, 6}};
        int[][] actual = ArrayMatrix.transposeMatrix(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetDimensions_EmptyMatrix() {
        int[][] matrix = new int[0][0];
        Pair<Integer, Integer> expected = new Pair<>(0, 0);
        Pair<Integer, Integer> actual = ArrayMatrix.getDimensions(matrix);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDimensions_SquareMatrix() {
        int[][] matrix = {{1, 2}, {3, 4}};
        Pair<Integer, Integer> expected = new Pair<>(2, 2);
        Pair<Integer, Integer> actual = ArrayMatrix.getDimensions(matrix);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDimensions_RectangularMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        Pair<Integer, Integer> expected = new Pair<>(2, 3);
        Pair<Integer, Integer> actual = ArrayMatrix.getDimensions(matrix);
        assertEquals(expected, actual);
    }
}
