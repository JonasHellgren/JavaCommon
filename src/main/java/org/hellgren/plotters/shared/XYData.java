package org.hellgren.plotters.shared;

import org.nd4j.shade.guava.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public record XYData(List<Double> x, List<Double> y) {

    public static XYData of(List<Double> x, List<Double> y) {
        Preconditions.checkArgument(x.size() == y.size(),
                "x and y should have same length");
        return new XYData(x, y);
    }

    public static XYData empty() {
        return new XYData(new ArrayList<>(), new ArrayList<>());
    }

    public boolean isEmpty() {
        return x.isEmpty() && y.isEmpty();
    }

    @Override
    public String toString() {
        return "XYData{" +
                "x=" + x + System.lineSeparator() +
                ", y=" + y + System.lineSeparator() +
                '}'+ System.lineSeparator();
    }

}