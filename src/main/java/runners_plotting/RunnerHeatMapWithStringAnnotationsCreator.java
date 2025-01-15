package runners_plotting;

import org.hellgren.plotters.plotting_3d.HeatMapWithStringTextInCellsCreator;
import org.hellgren.plotters.shared.PlotSettings;
import org.hellgren.utilities.list_arrays.ArrayCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerHeatMapWithStringAnnotationsCreator {

    public static void main(String[] args) {
        var data = getData();
        var settings= PlotSettings.stringTextInHeatMap().withWidth(400).withHeight(200);
        var creator = HeatMapWithStringTextInCellsCreator.ofStringData(settings,data, getXData(),getYData());
        //var creator = HeatMapWithStringAnnotationsCreator.ofStringData(settings,data); //works as well
        new SwingWrapper<>(creator.create()).displayChart();
    }

    private static String[][] getData() {
        return new String[][]{
                {"A", "B", "50", "D", "10"},  //(0,0), (1,0),...
                {"20", "30", "50", "30", "10"},
                {"2", "4", "3", "2", "10"},
                {"30", "2", "4", "32", "-10"}   //x(0,3), x(1,3),...
        };
    }

    public static double[] getXData() {
        return ArrayCreator.createArrayFromStartAndEnd(5,0, 5);
    }

    public static double[] getYData() {
        return ArrayCreator.createArrayFromStartAndEnd(4, 0, 4);
    }

}
