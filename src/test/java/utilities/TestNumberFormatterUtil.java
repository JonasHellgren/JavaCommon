package utilities;

import org.hellgren.utilities.formatting.NumberFormatterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestNumberFormatterUtil {

    @Test
     void testGetRoundedNumberAsString_OneDigit() {
        Double value = 12.345;
        int nofDigits = 1;
        String expected = "12.3";
        String actual = NumberFormatterUtil.getRoundedNumberAsString(value, nofDigits);
        assertEquals(expected, actual);
    }

    @Test
     void testGetRoundedNumberAsString_TwoDigits() {
        Double value = 12.345;
        int nofDigits = 2;
        String expected = "12.35";
        String actual = NumberFormatterUtil.getRoundedNumberAsString(value, nofDigits);
        assertEquals(expected, actual);
    }

    @Test
     void testGetRoundedNumberAsString_NoDigits() {
        Double value = 12.345;
        int nofDigits = 0;
        String expected = "12";
        String actual = NumberFormatterUtil.getRoundedNumberAsString(value, nofDigits);
        assertEquals(expected, actual);
    }

    @Test
     void testGetRoundedNumberAsString_NullValue() {
        Double value = 1.33;
        int nofDigits = 1;
        String valRounded=NumberFormatterUtil.getRoundedNumberAsString(value, nofDigits);
        Assertions.assertEquals("1.3", valRounded);
    }

    @Test
     void testGetRoundedNumberAsString_NegativeValue() {
        Double value = -12.345;
        int nofDigits = 1;
        String expected = "-12.3";
        String actual = NumberFormatterUtil.getRoundedNumberAsString(value, nofDigits);
        assertEquals(expected, actual);
    }

    @Test
     void testGetRoundedNumberAsString_FormatterOneDigit() {
        Double value = 12.345;
        String expected = "12.3";
        String actual = NumberFormatterUtil.formatterOneDigit.format(value);
        assertEquals(expected, actual);
    }

    @Test
     void testGetRoundedNumberAsString_FormatterTwoDigits() {
        Double value = 12.345;
        String expected = "12.35";
        String actual = NumberFormatterUtil.formatterTwoDigits.format(value);
        assertEquals(expected, actual);
    }

}
