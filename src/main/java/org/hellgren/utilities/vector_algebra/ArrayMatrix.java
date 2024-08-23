package org.hellgren.utilities.vector_algebra;

import org.apache.commons.math3.util.Pair;

public class ArrayMatrix {

    private ArrayMatrix() {
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }
        int numRows = matrix.length; // Original number of rows
        int numCols = matrix[0].length; // Original number of columns
        int[][] transposedMatrix = new int[numCols][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                transposedMatrix[j][i] = matrix[i][j]; // Swap the row and column indices for the transposition
            }
        }
        return transposedMatrix;
    }


    public static Pair<Integer, Integer> getDimensions(int[][] data) {
        // Check for a null or empty array to avoid NullPointerException
        if (data == null || data.length == 0 || data[0].length == 0) {
            return new Pair<>(0, 0); // Assuming you want to return (0, 0) for an empty or null array
        }
        int numberOfRows = data.length;
        int numberOfColumns = data[0].length; // Assuming a rectangular matrix
        return new Pair<>(numberOfRows, numberOfColumns);
    }

}
