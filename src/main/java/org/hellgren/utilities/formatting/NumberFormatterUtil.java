package org.hellgren.utilities.formatting;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatterUtil {

    private NumberFormatterUtil() {
    }

    public static final DecimalFormatSymbols SYMBOLS = new DecimalFormatSymbols(Locale.US); //US <=> only dots
    public static final DecimalFormat formatterOneDigit = new DecimalFormat("#.#", SYMBOLS);
    public static final DecimalFormat formatterTwoDigits = new DecimalFormat("#.##", SYMBOLS);

    public static String getRoundedNumberAsString(Double value, int nofDigits)  {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);  //ENGLISH => point
        nf.setMaximumFractionDigits(nofDigits);
        return nf.format(value);
    }

}
