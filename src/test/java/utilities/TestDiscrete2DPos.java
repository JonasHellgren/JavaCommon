package utilities;

import org.hellgren.utilities.vector_algebra.Discrete2DPos;
import org.hellgren.utilities.vector_algebra.Discrete2DVector;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDiscrete2DPos {


    @Test
    public void testOf() {
        Discrete2DPos pos = Discrete2DPos.of(1, 2);
        assertEquals(1, pos.x());
        assertEquals(2, pos.y());
    }

    @Test
    public void testCopy() {
        Discrete2DPos pos = Discrete2DPos.of(1, 2);
        Discrete2DPos copy = pos.copy();
        assertEquals(1, copy.x());
        assertEquals(2, copy.y());
    }

    @Test
    public void testMove() {
        Discrete2DPos pos = Discrete2DPos.of(1, 2);
        Discrete2DVector vector = Discrete2DVector.of(3,4);
        Discrete2DPos movedPos = pos.move(vector);
        assertEquals(4, movedPos.x());
        assertEquals(6, movedPos.y());
    }

    @Test
    public void testClip() {
        Discrete2DPos pos = Discrete2DPos.of(5, 6);
        Discrete2DPos minPos = Discrete2DPos.of(1, 2);
        Discrete2DPos maxPos = Discrete2DPos.of(3, 4);
        Discrete2DPos clippedPos = pos.clip(minPos, maxPos);
        assertEquals(3, clippedPos.x());
        assertEquals(4, clippedPos.y());
    }

    @Test
    public void testMidPos() {
        Discrete2DPos posA = Discrete2DPos.of(1, 2);
        Discrete2DPos posB = Discrete2DPos.of(3, 4);
        Optional<Discrete2DPos> midPos = posA.midPos(posB);
        assertTrue(midPos.isPresent());
        Discrete2DPos midPosValue = midPos.get();
        assertEquals(2, midPosValue.x());
        assertEquals(3, midPosValue.y());
    }

    @Test
    public void testDistance() {
        Discrete2DPos posA = Discrete2DPos.of(1, 2);
        Discrete2DPos posB = Discrete2DPos.of(3, 4);
        double distance = posA.distance(posB);
        assertEquals(Math.sqrt(4+4), distance);
    }

    @Test
    public void testVector() {
        Discrete2DPos posA = Discrete2DPos.of(1, 2);
        Discrete2DPos posB = Discrete2DPos.of(3, 4);
        Discrete2DVector vector = posA.vector(posB);
        assertEquals(2, vector.dx());
        assertEquals(2, vector.dy());
    }

    @Test
    public void testGetDy() {
        Discrete2DPos posA = Discrete2DPos.of(1, 2);
        Discrete2DPos posB = Discrete2DPos.of(3, 4);
        int dy = posA.vector(posB).dy();
        assertEquals(2, dy);
    }

    @Test
    public void testGetDx() {
        Discrete2DPos posA = Discrete2DPos.of(1, 2);
        Discrete2DPos posB = Discrete2DPos.of(3, 4);
        int dx = posA.vector(posB).dx();
        assertEquals(2, dx);
    }

}
