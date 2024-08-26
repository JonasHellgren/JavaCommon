package utilities;

import org.hellgren.utilities.conditionals.Counter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestCounter {


    @Test    // 1
     void whenCounting_thenCorrect() {
        Counter counter = Counter.empty();
        counter.increase();
        assertEquals(1, counter.getCount());
    }
}
