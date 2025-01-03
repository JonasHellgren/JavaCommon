package runners_plotting;

import org.hellgren.plotters.plotting_3d.HeatMapChartCreator;
import org.hellgren.plotters.shared.PlotSettings;
import org.hellgren.utilities.list_arrays.ArrayCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerHeatMapChartCreator {

    public static final int LENGTH = 100;
    public static final double F_MAX = 100.0;

    public static void main(String[] args) {
        var data = getData();
        var settings = PlotSettings.defaultBuilder()
                .title("Sample HeatMap").showDataValues(true).showAxisTicks(true).build();
        var creator = HeatMapChartCreator.of(settings, data);
        new SwingWrapper<>(creator.create()).displayChart();

        var data1 = getData1();
        settings = PlotSettings.defaultBuilder()
                .title("Sample HeatMap").showDataValues(false)
                .showAxisTicks(true).build();
        creator = HeatMapChartCreator.of(settings, data1, getXData(),getYData());
        new SwingWrapper<>(creator.create()).displayChart();
    }

    private static double[][] getData() {
        return new double[][]{
                {0, 10, 50, 100, 10},  //(0,0), (1,0),...
                {20, 30, 50, 30, 10},
                {2, 4, 3, 2, 10},
                {30, 2, 4, 3, -10}   //x(0,3), x(1,3),...
        };
    }

    public static double[] getXData() {
        return ArrayCreator.createArrayFromStartAndEnd(LENGTH,-3, 3);
    }

    public static double[] getYData() {
        return ArrayCreator.createArrayFromStartAndEnd(LENGTH, 0, 7 );
    }

    private static double[][] getData1() {
        double[][] data = new double[LENGTH][LENGTH];
        for (int xi = 0; xi < getXData().length; xi++) {
            for (int yi = 0; yi < getYData().length; yi++) {
                double x = getXData()[xi];
                double y = getYData()[yi];
                double yMax=getYData()[getYData().length-1];
                data[yi][xi] = F_MAX * Math.sin((Math.PI / 3) * x * (yMax-y)) ;
            }
        }
        return data;
    }

}
