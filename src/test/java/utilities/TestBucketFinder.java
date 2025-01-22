package utilities;

import com.google.common.collect.Range;
import org.hellgren.utilities.math.BucketFinder;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestBucketFinder {

    @Test
    public void testGetBucket_ValueWithinRange_ReturnsBucketIndex() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 0.5;
        Optional<Integer> bucketIndex = bucketFinder.getBucket(value);
        assertTrue(bucketIndex.isPresent());
        assertEquals(0, bucketIndex.get());
    }

    @Test
    public void testGetBucket_ValueOutsideRange_ReturnsEmpty() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 2.0;
        Optional<Integer> bucketIndex = bucketFinder.getBucket(value);
        assertFalse(bucketIndex.isPresent());
    }

    @Test
    public void testGetBucket_ValueAtLowerEndpoint_ReturnsBucketIndex() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 0.0;
        Optional<Integer> bucketIndex = bucketFinder.getBucket(value);
        assertTrue(bucketIndex.isPresent());
        assertEquals(0, bucketIndex.get());
    }

    @Test
    public void testGetBucket_ValueAtUpperEndpoint_ReturnsEmpty() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 2.0;
        Optional<Integer> bucketIndex = bucketFinder.getBucket(value);
        assertFalse(bucketIndex.isPresent());
    }

    @Test
    public void testGetBucketHalfSizeAbove_ValueWithinRange_ReturnsBucketIndex() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 0.1;
        Optional<Integer> bucketIndex = bucketFinder.getBucketHalfSizeAbove(value);
        assertTrue(bucketIndex.isPresent());
        assertEquals(0, bucketIndex.get());
    }

    @Test
    public void testGetBucketHalfSizeAbove_ValueOutsideRange_ReturnsEmpty() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 2.0;
        Optional<Integer> bucketIndex = bucketFinder.getBucketHalfSizeAbove(value);
        assertFalse(bucketIndex.isPresent());
    }

    @Test
    public void testGetBucketHalfSizeAbove_ValueAtLowerEndpoint_ReturnsBucketIndex() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 0.0;
        Optional<Integer> bucketIndex = bucketFinder.getBucketHalfSizeAbove(value);
        assertTrue(bucketIndex.isPresent());
        assertEquals(0, bucketIndex.get());
    }

    @Test
    public void testGetBucketHalfSizeAbove_ValueAtUpperEndpoint_ReturnsEmpty() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 1.0;
        var bucketFinder = BucketFinder.of(range, bucketSize);
        double value = 2.0;
        Optional<Integer> bucketIndex = bucketFinder.getBucketHalfSizeAbove(value);
        assertFalse(bucketIndex.isPresent());
    }

    @Test
    public void testOf_BucketSizeLessThanOrEqualToZero_ThrowsIllegalArgumentException() {
        var range = Range.closedOpen(0.0, 2.0);
        double bucketSize = 0.0;
        assertThrows(IllegalArgumentException.class, () -> BucketFinder.of(range, bucketSize));
    }
}