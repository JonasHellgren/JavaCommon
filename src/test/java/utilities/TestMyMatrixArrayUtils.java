package utilities;


import org.hellgren.utilities.list_arrays.MyMatrixArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestMyMatrixArrayUtils {


    @Test
    public void testCreateDiagonalMatrix_SimpleCase() {
        double[] diagonalValues = {1.0, 2.0, 3.0};
        double[][] expectedMatrix = {
                {1.0, 0.0, 0.0},
                {0.0, 2.0, 0.0},
                {0.0, 0.0, 3.0}
        };

        double[][] actualMatrix = MyMatrixArrayUtils.createDiagonalMatrix(diagonalValues);

        assertArrayEquals(expectedMatrix, actualMatrix);
    }


    @Test
    public void testCreateDiagonalMatrix_InvalidInput_NDimZero() {
        double[] diagonalValues = {};

        assertThrows(IllegalArgumentException.class, () -> MyMatrixArrayUtils.createDiagonalMatrix(diagonalValues));
    }

}

