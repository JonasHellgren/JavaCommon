package org.hellgren.utilities.math;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class SigmoidFunctions {

    public static DoubleUnaryOperator sigmoid=(x) ->  1.0 / (1.0 + Math.exp(-x));

    public DoubleUnaryOperator derSigmoid = (x) ->
    {
        double sig = sigmoid.applyAsDouble(x);
        return sig * (1 - sig);
    };


}
