package runners_plotting;

import org.hellgren.plotters.chart_saver.ChartSaver;
import org.hellgren.plotters.many_lines.ManyLinesChartCreator;
import org.hellgren.utilities.file_io.PathAndFile;
import org.hellgren.utilities.list_arrays.ListCreator;
import org.knowm.xchart.SwingWrapper;

public class RunnerManyLinesChartCreator {

    public static void main(String[] args) {
        var xList = ListCreator.createFromStartToEndWithNofItems(0d,10.1d,100);
        var creator= ManyLinesChartCreator.of(ManyLinesChartCreator.Settings.ofDefaults()
                .withWidth(300).withHeight(200),xList);
        creator.addLine("line1", ListCreator.createFromStartToEndWithNofItems(0d,10.1d,100));
        var chart = creator.createChart();
        new SwingWrapper<>(chart).displayChart();

    }

}
