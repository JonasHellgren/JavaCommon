package runners_plotting;

import org.hellgren.plotters.scatter.ScatterWithLineChartCreator;
import org.hellgren.utilities.list_arrays.ListCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerScatterAndLineChartCreator {

    public static void main(String[] args) {
        var chartCreator= ScatterWithLineChartCreator.ofDefaults();
        chartCreator.addScatter(
                ListCreator.createFromStartToEndWithNofItems(0,10,10),
                ListCreator.createFromStartToEndWithNofItems(1,11,10));
        chartCreator.addLine(
                ListCreator.createFromStartToEndWithNofItems(0,10,100),
                ListCreator.createFromStartToEndWithNofItems(0,10,100));
        var chart=chartCreator.create();
        new SwingWrapper<>(chart).displayChart();
    }

}
