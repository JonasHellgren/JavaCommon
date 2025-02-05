package runners_plotting;

import org.hellgren.plotters.plotting_2d.ManyLinesChartCreator;
import org.hellgren.plotters.shared.PlotSettings;
import org.hellgren.utilities.list_arrays.ListCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerManyLinesChartCreator {

    public static final int N_ITEMS = 101;

    public static void main(String[] args) {
        var xList = ListCreator.createFromStartWithStepWithNofItems(0d, 1.000d, N_ITEMS);
        System.out.println("xList = " + xList);
        var creator = ManyLinesChartCreator.of(PlotSettings.ofDefaults()
                        .withWidth(300).withHeight(200)
                        .withSpaceBetweenXTicks(10d)
                , xList);
        creator.addLine("line1", ListCreator.createFromStartToEndWithNofItems(0d, 10.1d, N_ITEMS));
        var chart = creator.create();
        new SwingWrapper<>(chart).displayChart();

    }

}
