package runners;

import org.hellgren.plotters.plotting_2d.HeatMapChartCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerHeatMapChartCreator {

    public static void main(String[] args) {

        var data = getData();
        var settings=HeatMapChartCreator.Settings.defaultBuilder().showDataValues(true).build();
        var creator = HeatMapChartCreator.defaultSettings(data);
        var chart = creator.create();
        new SwingWrapper<>(chart).displayChart();

    }

    private static int[][] getData() {
        return new int[][]{
                {0, 1, 50, 100, 10},  //(0,0), (1,0),...
                {2, 3, 5, 3, 10},
                {2, 4, 3, 2, 10},
                {30, 2, 4, 3, -10}   //x(0,3), x(1,3),...
        };
    }

}
