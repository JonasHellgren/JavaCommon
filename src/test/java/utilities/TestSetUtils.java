package utilities;

import org.hellgren.utilities.set_utlis.SetUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class TestSetUtils {

    List<String> list1=List.of("A","B","C");
    List<String> list2=List.of("A","B");


    @Test
     void whenGettingAllSamet_thenCorrect() {
        assertFalse(SetUtils.allSame(list1));
    }

    @Test
     void whenGettingUnionInTwoList_thenCorrect() {
        List<String> same = SetUtils.union(list1, list2);
        assertEquals(List.of("A","B","C"),same);
    }

    @Test
     void whenGettingDifferenceInTwoList_thenCorrect() {
        List<String> difference = SetUtils.difference(list1, list2);
        assertEquals(List.of("C"),difference);
    }


    @Test
     void whenGettingIntersectionInTwoList_thenCorrect() {
        List<String> same = SetUtils.intersection(list1, list2);
        assertEquals(List.of("A","B"),same);
    }

    @Test
     void testAllSame() {
        List<Integer> list1 = Arrays.asList(1, 1, 1);
        assertTrue(SetUtils.allSame(list1));

        List<Integer> list2 = Arrays.asList(1, 2, 3);
        assertTrue(!SetUtils.allSame(list2));
    }

    @Test
    void testUnion() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        assertEquals(expected, SetUtils.union(list1, list2));
    }

    @Test
    void testDifference() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> expected = Arrays.asList(1, 2,6,7);

        assertEquals(expected, SetUtils.difference(list1, list2));
    }

    @Test
    void testIntersection() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> expected = Arrays.asList(3, 4, 5);

        assertEquals(expected, SetUtils.intersection(list1, list2));
    }

     @Test
     public void testGetAnyFromSet_MultipleElementSet_ReturnsRandomElement() {
         Set<String> multipleElementSet = new HashSet<>();
         multipleElementSet.add("Test Element 1");
         multipleElementSet.add("Test Element 2");
         multipleElementSet.add("Test Element 3");

         String result = SetUtils.getAnyFromSet(multipleElementSet);
         assertTrue(multipleElementSet.contains(result));
     }

}
