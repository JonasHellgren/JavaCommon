package utilities;


import org.hellgren.utilities.linear_regression_batch_fitting.DataSetCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;


 class TestDataSetCreator {

    @Test
     void testConstructor() {
        DataSetCreator creator = new DataSetCreator();
        assertNotNull(creator);
        assertEquals(0, creator.size());
    }

    @Test
     void testClear() {
        DataSetCreator creator = new DataSetCreator();
        creator.addPoint(new double[]{1, 2}, 3.0);
        assertEquals(1, creator.size());
        creator.clear();
        assertEquals(0, creator.size());
    }

    @Test
     void testAddPoint() {
        DataSetCreator creator = new DataSetCreator();
        creator.addPoint(new double[]{1, 2}, 3.0);
        assertEquals(1, creator.size());
        assertArrayEquals(new double[]{1, 2}, creator.createDataSet().getFirst().getRow(0));
        assertEquals(3.0, creator.createDataSet().getSecond().getEntry(0), 1e-6);
    }

    @Test
     void testCreateDataSet() {
        DataSetCreator creator = new DataSetCreator();
        creator.addPoint(new double[]{1, 2}, 3.0);
        creator.addPoint(new double[]{4, 5}, 6.0);
        RealMatrix matrix = creator.createDataSet().getFirst();
        assertEquals(2, matrix.getRowDimension());
        assertEquals(2, matrix.getColumnDimension());
        assertArrayEquals(new double[]{1, 2}, matrix.getRow(0));
        assertArrayEquals(new double[]{4, 5}, matrix.getRow(1));
        RealVector vector = creator.createDataSet().getSecond();
        assertEquals(2, vector.getDimension());
        assertEquals(3.0, vector.getEntry(0), 1e-6);
        assertEquals(6.0, vector.getEntry(1), 1e-6);
    }


    @Test
     void testNullInput() {
        DataSetCreator creator = new DataSetCreator();
        assertThrows(NullPointerException.class, () -> creator.addPoint(null, 3.0));
        assertThrows(NullPointerException.class, () -> creator.addPoint(new double[]{1, 2}, null));
    }
}
    
