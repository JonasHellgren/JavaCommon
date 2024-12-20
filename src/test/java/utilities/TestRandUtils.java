package utilities;

import org.hellgren.utilities.random.RandUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRandUtils {

    @Test
    public void testGetRandomItemFromStringList() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        String randomItem = RandUtils.getRandomItemFromStringList(list);
        assertTrue(list.contains(randomItem));
    }

    @Test
    public void testGetRandomItemFromList() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        var rand=new RandUtils<String>();
        String randomItem = rand.getRandomItemFromList(list);
        assertTrue(list.contains(randomItem));
    }

    @Test
    public void testGetRandomIntNumber() {
        int min = 1;
        int max = 10;
        int randomInt = RandUtils.getRandomIntNumber(min, max);
        assertTrue(randomInt >= min && randomInt < max);
    }

    @Test
    public void testGetRandomDouble() {
        double min = 1.0;
        double max = 10.0;
        double randomDouble = RandUtils.getRandomDouble(min, max);
        assertTrue(randomDouble >= min && randomDouble <= max);
    }

    @Test
    public void testRandomNumberBetweenZeroAndOne() {
        double randomDouble = RandUtils.randomNumberBetweenZeroAndOne();
        assertTrue(randomDouble >= 0.0 && randomDouble <= 1.0);
    }

    @Test
    public void testRandomIndices() {
        int nSelected = 5;
        int nGross = 10;
        Set<Integer> randomIndices = RandUtils.randomIndices(nSelected, nGross);
        assertEquals(nSelected, randomIndices.size());
        for (int index : randomIndices) {
            assertTrue(index >= 0 && index < nGross);
        }
    }


}
