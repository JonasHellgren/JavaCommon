package utilities;

import org.hellgren.utilities.equation_solvers.FirstDegreeSolver;
import org.hellgren.utilities.equation_solvers.SecondDegreeSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class TestEquationsSolvers {

     private FirstDegreeSolver firstDegreeSolver;
     private SecondDegreeSolver secondDegreeSolver;

     @BeforeEach
     void setUp() {
         double firstDegreeA = 1;
         double firstDegreeB = 2;
         firstDegreeSolver = new FirstDegreeSolver(firstDegreeA, firstDegreeB);

         double secondDegreeA = 1;
         double secondDegreeB = -5;
         double secondDegreeC = 6;
         secondDegreeSolver = SecondDegreeSolver.builder()
                 .a(secondDegreeA).b(secondDegreeB).c(secondDegreeC)
                 .build();
     }

     @Test
     void testFirstDegreeSolver() {
         double expectedResult = 2;
         double actualResult = firstDegreeSolver.solve().orElseThrow();
         Assertions.assertEquals(expectedResult, actualResult);
     }

     @Test
     void testSecondDegreeSolver() {
         Assertions.assertTrue(secondDegreeSolver.solve().isPresent());
         Double expectedResult = 3.0;
         Double actualResult = secondDegreeSolver.solve().orElseThrow().getFirst();
         Assertions.assertEquals(expectedResult, actualResult);

         expectedResult = 2.0;
         actualResult = secondDegreeSolver.solve().orElseThrow().getSecond();
         Assertions.assertEquals(expectedResult, actualResult);
     }

}
