package utilities;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.hellgren.utilities.vector_algebra.MatrixStacking;
import org.hellgren.utilities.vector_algebra.MyMatrixUtils;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyMatrixUtils {


    @Test
    public void testStackVectorsHorizontally() {
        RealVector v1 = new ArrayRealVector(new double[]{1, 2, 3});
        RealVector v2 = new ArrayRealVector(new double[]{4, 5, 6});
        RealVector vector = MatrixStacking.stackVectorsHorizontally(List.of(v1, v2));
        assertEquals(6, vector.getDimension());
        assertEquals(1, vector.getEntry(0), 1e-6);
        assertEquals(2, vector.getEntry(1), 1e-6);
        assertEquals(3, vector.getEntry(2), 1e-6);
        assertEquals(4, vector.getEntry(3), 1e-6);
        assertEquals(5, vector.getEntry(4), 1e-6);
        assertEquals(6, vector.getEntry(5), 1e-6);
    }

    @Test
    public void testCreateZeroVector() {
        RealVector vector = MyMatrixUtils.createZeroVector(5);
        assertEquals(5, vector.getDimension());
        for (int i = 0; i < 5; i++) {
            assertEquals(0, vector.getEntry(i), 1e-6);
        }
    }

    @Test
    public void testCreateOnesVector() {
        RealVector vector = MyMatrixUtils.createOnesVector(5);
        assertEquals(5, vector.getDimension());
        for (int i = 0; i < 5; i++) {
            assertEquals(1, vector.getEntry(i), 1e-6);
        }
    }

    @Test
    public void testCreateDiagonalMatrix() {
        double[] diagonal = new double[]{1, 2, 3};
        RealMatrix matrix = MyMatrixUtils.createDiagonalMatrix(diagonal);
        assertEquals(3, matrix.getRowDimension());
        assertEquals(3, matrix.getColumnDimension());
        for (int i = 0; i < 3; i++) {
            assertEquals(diagonal[i], matrix.getEntry(i, i), 1e-6);
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    assertEquals(0, matrix.getEntry(i, j), 1e-6);
                }
            }
        }
    }

    @Test
    public void testIdentity() {
        RealMatrix matrix = MyMatrixUtils.identity(5);
        assertEquals(5, matrix.getRowDimension());
        assertEquals(5, matrix.getColumnDimension());
        for (int i = 0; i < 5; i++) {
            assertEquals(1, matrix.getEntry(i, i), 1e-6);
            for (int j = 0; j < 5; j++) {
                if (i != j) {
                    assertEquals(0, matrix.getEntry(i, j), 1e-6);
                }
            }
        }
    }

    @Test
    public void testProperties() {
        RealMatrix matrix = MyMatrixUtils.createDiagonalMatrix(new double[]{1, 2, 3});
        MyMatrixUtils.Properties properties = MyMatrixUtils.properties(matrix);
        assertEquals(3, properties.nRows());
        assertEquals(3, properties.nColumns());
    }
}
