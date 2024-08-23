package utilities;

import org.hellgren.utilities.economics.AnnuityCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

 class TestAnnuityCalculator {

    @ParameterizedTest
    @CsvSource({
            "1000,0,0,10, 100",   //zero interest rate -> a=p/n
            "1000,0,0.000001,10, 100",  //same as above
            "1000,100,0,10, 90",   ////zero interest rate -> a=(p-r)/n
            "1000,100,0.000001,10, 90",  //same as above
            "1000,0,0.1,10, 162",   // non zero interest rate -> a>p/n
            "3650,0,0.1,3650, 162",   // non zero interest rate -> a>p/n
            "1000,500,0.1,10, 131",   // smaller than above due to rest value
            "0,100,0,10, -10",   ////zero price but rest value -> a=(0-r)/n<0
            "20e3,0,0.05,10, 2590",   //https://www.cuemath.com/annuity-formula/
            "150_000,0,0.0042,240, 994",   //https://www.wikihow.com/Calculate-Annual-Annuity-Payments
    })

    void whenAnnuity_thenCorrect1(ArgumentsAccessor arguments) {
        double price=arguments.getDouble(0);
        double restValue=arguments.getDouble(1);
        double i=arguments.getDouble(2);
        double lifeTimeInYears=arguments.getDouble(3);
        double expAnnuity=arguments.getDouble(4);

        AnnuityCalculator calculator= AnnuityCalculator.builder()
                .price(price).restValue(restValue).i(i).lifeTimeInYears(lifeTimeInYears)
                .build();

        Assertions.assertEquals(expAnnuity,calculator.annuity(),1);

    }

}
