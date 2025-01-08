package utilities;

import org.hellgren.utilities.list_arrays.MatrixArrayCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class TestMatrixArrayCreator {

    @Test
     void testCreateDiagonalMatrix_SimpleCase() {
        double[] diagonalValues = {1.0, 2.0, 3.0};
        double[][] expectedMatrix = {
                {1.0, 0.0, 0.0},
                {0.0, 2.0, 0.0},
                {0.0, 0.0, 3.0}
        };

        double[][] actualMatrix = MatrixArrayCreator.createDiagonalMatrix(diagonalValues);

        assertArrayEquals(expectedMatrix, actualMatrix);
    }


    @Test
     void testCreateDiagonalMatrix_InvalidInput_NDimZero() {
        double[] diagonalValues = {};

        assertThrows(IllegalArgumentException.class, () -> MatrixArrayCreator.createDiagonalMatrix(diagonalValues));
    }

     @Test
      void testCreateAllZeroMatrix_SimpleCase() {
         int nDim = 3;
         double[][] expectedMatrix = {
                 {0.0, 0.0, 0.0},
                 {0.0, 0.0, 0.0},
                 {0.0, 0.0, 0.0}
         };

         double[][] actualMatrix = MatrixArrayCreator.createAllZeroMatrix(nDim);

         assertArrayEquals(expectedMatrix, actualMatrix);
     }

     @Test
      void testCreateAllWithValueMatrix_SimpleCase() {
         int nDim = 3;
         double value = 5.0;
         double[][] expectedMatrix = {
                 {5.0, 5.0, 5.0},
                 {5.0, 5.0, 5.0},
                 {5.0, 5.0, 5.0}
         };

         double[][] actualMatrix = MatrixArrayCreator.createAllWithValueMatrix(nDim, value);

         assertArrayEquals(expectedMatrix, actualMatrix);
     }

}
