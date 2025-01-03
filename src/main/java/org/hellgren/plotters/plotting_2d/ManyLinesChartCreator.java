package org.hellgren.plotters.plotting_2d;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.hellgren.plotters.shared.PlotSettings;
import org.hellgren.utilities.conditionals.Conditionals;
import org.hellgren.utilities.list_arrays.MyMatrixListUtils;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static org.hellgren.plotters.shared.FormattedAsString.getFormattedAsString;

@AllArgsConstructor
public class ManyLinesChartCreator {
    private final PlotSettings settings;
    List<Double> xList;
    List<List<Double>> yData;
    List<String> names;

    public static ManyLinesChartCreator defaultSettings(List<Double> xData) {
        return ManyLinesChartCreator.of(PlotSettings.ofDefaults(), xData);
    }

    public static ManyLinesChartCreator of(PlotSettings settings, List<Double> xData) {
        return new ManyLinesChartCreator(settings, xData, new ArrayList<>(), new ArrayList<>());
    }

    public void addLine(String name, List<Double> yList) {
        Preconditions.checkArgument(yList.size() == xList.size(), "yList and xData should have same size");
        this.yData.add(yList);
        this.names.add(name);
    }

    public XYChart create() {
        var s=settings;
        var chart = new XYChartBuilder()
                .xAxisTitle(s.xAxisLabel()).yAxisTitle(s.yAxisLabel())
                .width(s.width()).height(s.height()).build();
        var styler = chart.getStyler();
        styler.setYAxisMin(MyMatrixListUtils.findMin(yData));
        styler.setYAxisMax(MyMatrixListUtils.findMax(yData));
        styler.setChartTitleVisible(true);
        styler.setLegendVisible(s.showLegend()).setLegendPosition(s.legendPosition());
        styler.setLegendFont(s.legendTextFont());
        styler.setAxisTicksVisible(s.showAxisTicks());
        styler.setPlotGridLinesVisible(s.showGridLines());
        styler.setAxisTitleFont(s.axisTitleFont());
        styler.setAxisTickLabelsFont(s.axisTicksFont());
        styler.setxAxisTickLabelsFormattingFunction(value ->
                getFormattedAsString(value, settings.axisTicksDecimalFormat()));
        styler.setyAxisTickLabelsFormattingFunction(value ->
                getFormattedAsString(value, settings.axisTicksDecimalFormat()));
        styler.setChartBackgroundColor(Color.WHITE);
        Conditionals.executeIfTrue(s.colorRangeSeries() != null, () ->
                styler.setSeriesColors(s.colorRangeSeries()));
        for (String name : names) {
            int i = names.indexOf(name);
            XYSeries series = chart.addSeries(name, xList, yData.get(i));
            Conditionals.executeIfFalse(s.showMarker(), () -> series.setMarker(SeriesMarkers.NONE));
        }
        return chart;
    }

}
