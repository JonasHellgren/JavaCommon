package runners_plotting;

import org.hellgren.plotters.chart_saver.ChartSaver;
import org.hellgren.plotters.many_lines.ManyLinesChartCreator;
import org.hellgren.utilities.file_io.PathAndFile;
import org.hellgren.utilities.list_arrays.ListCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerManyLinesChartCreator {

    public static void main(String[] args) {
        var xList = ListCreator.createFromStartWithStepWithNofItems(0d,10d,10);
        var creator= ManyLinesChartCreator.defaultSettings(xList);
        creator.addLine("line1", ListCreator.createFromStartWithStepWithNofItems(0d,1d,10));
        var chart = creator.createChart();

        new SwingWrapper<>(chart).displayChart();

    }

}
