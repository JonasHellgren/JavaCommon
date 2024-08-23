package utilities;

import org.hellgren.utilities.list_arrays.ListTransposer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestListTransposer {

    @Test
    void whenTranspoeList_thenCorrect() {

        var listOfList= List.of(List.of(1d, 2d, 3d),List.of(1d, 2d, 3d));
        var newListOfList= ListTransposer.transpose(listOfList);
        Assertions.assertEquals(List.of(1d,1d),newListOfList.get(0));
        Assertions.assertEquals(List.of(2d,2d),newListOfList.get(1));

    }
}
