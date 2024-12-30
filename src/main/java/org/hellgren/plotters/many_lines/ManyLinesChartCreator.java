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
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
            boolean showAxisTicks,
            boolean showGridLines,
            Color[] colorRangeSeries,
            String axisTicksDecimalFormat,
            Font axisTitleFont,
            Font axisTicksFont
    ) {


        public static SettingsBuilder defaultBuilder() {
            return Settings.builder().title("title").xAxisLabel("x").yAxisLabel("y")
                    .width(500).height(300)
                    .showLegend(true).showGridLines(true)
                    .colorRangeSeries(null)
                    .showAxisTicks(true).axisTicksDecimalFormat("#")
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
        var chart = new XYChartBuilder()
                .xAxisTitle(settings.xAxisLabel).yAxisTitle(settings.yAxisLabel)
                .width(settings.width).height(settings.height).build();
        var styler = chart.getStyler();
        styler.setYAxisMin(MyMatrixListUtils.findMin(yData));
        styler.setYAxisMax(MyMatrixListUtils.findMax(yData));
        styler.setChartTitleVisible(true).setLegendVisible(settings.showLegend);
        styler.setAxisTicksVisible(settings.showAxisTicks);
        styler.setPlotGridLinesVisible(settings.showGridLines);
        styler.setAxisTitleFont(settings.axisTitleFont);
        styler.setAxisTickLabelsFont(settings.axisTicksFont);
        styler.setMarkerSize(5);
        styler.setxAxisTickLabelsFormattingFunction(value -> getFormattedAsString(value));
        styler.setyAxisTickLabelsFormattingFunction(value -> getFormattedAsString(value));
        styler.setChartBackgroundColor(Color.WHITE);
        Conditionals.executeIfTrue(settings.colorRangeSeries != null, () ->
                styler.setSeriesColors(settings.colorRangeSeries));
        for (String name : names) {
            int i = names.indexOf(name);
            XYSeries series = chart.addSeries(name, xList, yData.get(i));
            series.setMarker(SeriesMarkers.NONE);
        }

        return chart;
    }

    private String getFormattedAsString(Double value) {
        DecimalFormat df = new DecimalFormat(settings.axisTicksDecimalFormat);
        return df.format(value);
    }


}
