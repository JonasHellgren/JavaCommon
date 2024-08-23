package utilities;

import org.hellgren.utilities.set_utlis.SetUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestSetUtils {

    List<String> list1=List.of("A","B","C");
    List<String> list2=List.of("A","B");


    @Test
    public void whenGettingAllSamet_thenCorrect() {
        assertFalse(SetUtils.allSame(list1));
    }

    @Test
    public void whenGettingUnionInTwoList_thenCorrect() {
        List<String> same = SetUtils.union(list1, list2);
        assertEquals(List.of("A","B","C"),same);
    }

    @Test
    public void whenGettingDifferenceInTwoList_thenCorrect() {
        List<String> difference = SetUtils.difference(list1, list2);
        assertEquals(List.of("C"),difference);
    }


    @Test
    public void whenGettingIntersectionInTwoList_thenCorrect() {
        List<String> same = SetUtils.intersection(list1, list2);
        assertEquals(List.of("A","B"),same);
    }

}
