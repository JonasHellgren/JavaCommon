package runners_plotting;

import org.hellgren.plotters.many_lines.ManyLinesChartCreator;
import org.hellgren.plotters.shared.PlotSettings;
import org.hellgren.utilities.list_arrays.ListCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerManyLinesChartCreator {

    public static void main(String[] args) {
        var xList = ListCreator.createFromStartToEndWithNofItems(0d,10.1d,100);
        var creator= ManyLinesChartCreator.of(PlotSettings.ofDefaults()
                .withWidth(300).withHeight(200),xList);
        creator.addLine("line1", ListCreator.createFromStartToEndWithNofItems(0d,10.1d,100));
        var chart = creator.create();
        new SwingWrapper<>(chart).displayChart();

    }

}
