package utilities;

import org.hellgren.utilities.list_arrays.ArrayCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class TestArrayUtilsOld {

    @Test
    void givenLengthStartAndIncrement_thenCorrect() {
        double[] array = ArrayCreator.createArrayFromIncrement(3, 0, 0.5);
        System.out.println("array = " + Arrays.toString(array));

        Assertions.assertEquals(0, array[0]);
        Assertions.assertEquals(1, array[array.length - 1]);

    }

    @Test
    void givenLengthStartAndEnd_thenCorrect() {

        double[] array = ArrayCreator.createArrayFromStartAndEnd(3, 0, 1);

        System.out.println("array = " + Arrays.toString(array));

        Assertions.assertEquals(0, array[0]);
        Assertions.assertEquals(1, array[array.length - 1]);

    }

    @Test
    void givenLStartEndAndIncrement_thenCorrect() {

        double[] array = ArrayCreator.createArrayFromStartEndAndIncrement(0, 1, 0.5);

        System.out.println("array = " + Arrays.toString(array));

        Assertions.assertEquals(0, array[0]);
        Assertions.assertEquals(1, array[array.length - 1]);

    }

    @Test
    void givenNotMatchingStartEndAndIncrement_thenTrows() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                ArrayCreator.createArrayFromStartEndAndIncrement(0, 1, 0.3)
        );


    }

}
