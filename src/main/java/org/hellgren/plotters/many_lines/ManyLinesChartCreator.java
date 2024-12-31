package org.hellgren.plotters.many_lines;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.With;
import org.hellgren.utilities.conditionals.Conditionals;
import org.hellgren.utilities.list_arrays.MyMatrixListUtils;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hellgren.plotters.shared.FormattedAsString.getFormattedAsString;

@AllArgsConstructor
public class ManyLinesChartCreator {

    @Builder
    @With
    public record Settings(
            String title,
            String xAxisLabel,
            String yAxisLabel,
            int width,
            int height,
            boolean showLegend,
            Styler.LegendPosition legendPosition,
            boolean showAxisTicks,
            boolean showGridLines,
            boolean showMarker,
            Color[] colorRangeSeries,
            String axisTicksDecimalFormat,
            Font axisTitleFont,
            Font axisTicksFont
    ) {


        public static SettingsBuilder defaultBuilder() {
            return Settings.builder().title("title").xAxisLabel("x").yAxisLabel("y")
                    .width(500).height(300)
                    .showLegend(true).legendPosition(Styler.LegendPosition.OutsideE)
                    .showGridLines(true)
                    .colorRangeSeries(null)
                    .showAxisTicks(true).axisTicksDecimalFormat("#").showMarker(false)
                    .axisTitleFont(new Font("Arial", Font.BOLD, 12))
                    .axisTicksFont(new Font("Arial", Font.PLAIN, 12));
        }

        public static Settings ofDefaults() {
            return defaultBuilder().build();
        }
    }

    private final Settings settings;
    List<Double> xList;
    List<List<Double>> yData;
    List<String> names;

    public static ManyLinesChartCreator defaultSettings(List<Double> xData) {
        return ManyLinesChartCreator.of(ManyLinesChartCreator.Settings.ofDefaults(), xData);
    }

    public static ManyLinesChartCreator of(ManyLinesChartCreator.Settings settings, List<Double> xData) {
        return new ManyLinesChartCreator(settings, xData, new ArrayList<>(), new ArrayList<>());
    }

    public void addLine(String name, List<Double> yList) {
        Preconditions.checkArgument(yList.size() == xList.size(), "yList and xData should have same size");
        this.yData.add(yList);
        this.names.add(name);
    }

    public XYChart createChart() {
        var s=settings;
        var chart = new XYChartBuilder()
                .xAxisTitle(s.xAxisLabel).yAxisTitle(s.yAxisLabel)
                .width(s.width).height(s.height).build();
        var styler = chart.getStyler();
        styler.setYAxisMin(MyMatrixListUtils.findMin(yData));
        styler.setYAxisMax(MyMatrixListUtils.findMax(yData));
        styler.setChartTitleVisible(true);
        styler.setLegendVisible(s.showLegend).setLegendPosition(s.legendPosition);
        styler.setAxisTicksVisible(s.showAxisTicks);
        styler.setPlotGridLinesVisible(s.showGridLines);
        styler.setAxisTitleFont(s.axisTitleFont);
        styler.setAxisTickLabelsFont(s.axisTicksFont);
        styler.setxAxisTickLabelsFormattingFunction(value ->
                getFormattedAsString(value, settings.axisTicksDecimalFormat));
        styler.setyAxisTickLabelsFormattingFunction(value ->
                getFormattedAsString(value, settings.axisTicksDecimalFormat));
        styler.setChartBackgroundColor(Color.WHITE);
        Conditionals.executeIfTrue(s.colorRangeSeries != null, () ->
                styler.setSeriesColors(s.colorRangeSeries));
        for (String name : names) {
            int i = names.indexOf(name);
            XYSeries series = chart.addSeries(name, xList, yData.get(i));
            Conditionals.executeIfFalse(s.showMarker, () -> series.setMarker(SeriesMarkers.NONE));
        }
        return chart;
    }

}
