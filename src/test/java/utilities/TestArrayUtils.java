package utilities;

import org.hellgren.utilities.list_arrays.MyArrayUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hellgren.utilities.list_arrays.MyArrayUtil.createArrayFromStartAndEnd;

public class TestArrayUtils {

    @Test
    public void givenLengthStartAndIncrement_thenCorrect() {

        double[] array= MyArrayUtil.createArrayFromIncrement(3,0,0.5);

        System.out.println("array = " + Arrays.toString(array));

        Assertions.assertEquals(0,array[0]);
        Assertions.assertEquals(1,array[array.length-1]);

    }

    @Test
    public void givenLengthStartAndEnd_thenCorrect() {

        double[] array= createArrayFromStartAndEnd(3,0,1);

        System.out.println("array = " + Arrays.toString(array));

        Assertions.assertEquals(0,array[0]);
        Assertions.assertEquals(1,array[array.length-1]);

    }

    @Test
    public void givenLStartEndAndIncrement_thenCorrect() {

        double[] array= MyArrayUtil.createArrayFromStartEndAndIncrement(0,1,0.5);

        System.out.println("array = " + Arrays.toString(array));

        Assertions.assertEquals(0,array[0]);
        Assertions.assertEquals(1,array[array.length-1]);

    }

    @Test
    public void givenNotMatchingStartEndAndIncrement_thenTrows() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        MyArrayUtil.createArrayFromStartEndAndIncrement(0,1,0.3)
        );


    }

}
