package org.hellgren.plotters.scatter;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.With;
import org.hellgren.plotters.shared.XYData;
import org.hellgren.utilities.conditionals.Conditionals;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.awt.*;
import java.util.List;
import static org.hellgren.plotters.shared.FormattedAsString.getFormattedAsString;

/**
 * A utility class for creating scatter charts, a line can be added to the charts
 * It uses  the XChart library.
 */

@AllArgsConstructor
public class ScatterWithLineChartCreator {


    @Builder
    @With
    public record Settings(
            String title,
            String xAxisLabel,
            String yAxisLabel,
            int width,
            int height,
            boolean showGridLines,
            Color[] colorRangeSeries,
            String axisTicksDecimalFormat,
            Font axisTitleFont,
            Font axisTicksFont
    ) {


        public static Settings.SettingsBuilder defaultBuilder() {
            return Settings.builder().title("title").xAxisLabel("x").yAxisLabel("y")
                    .width(500).height(300)
                    .showGridLines(true)
                    .colorRangeSeries(new Color[]{Color.BLACK, Color.GRAY})
                    .axisTicksDecimalFormat("#")
                    .axisTitleFont(new Font("Arial", Font.BOLD, 12))
                    .axisTicksFont(new Font("Arial", Font.PLAIN, 12));
        }

        public static Settings ofDefaults() {
            return defaultBuilder().build();
        }
    }

    private final Settings settings;
    private XYData xyDataScatter, xyDataLine;

    public static ScatterWithLineChartCreator ofDefaults() {
        return ScatterWithLineChartCreator.of(Settings.ofDefaults());
    }

    public static ScatterWithLineChartCreator of(Settings settings) {
        return new ScatterWithLineChartCreator(settings, XYData.empty(), XYData.empty());
    }


    /**
     * Adds scatter data to the chart.
     *
     * @param xListScatter the list of x values for the scatter data
     * @param yListScatter the list of y values for the scatter data
     */
    public void addScatter(List<Double> xListScatter, List<Double> yListScatter) {
        this.xyDataScatter = XYData.of(xListScatter, yListScatter);
    }

    /**
     * Adds line data to the chart.
     *
     * @param xListLine the list of x values for the line data
     * @param yListLine the list of y values for the line data
     */
    public void addLine(List<Double> xListLine, List<Double> yListLine) {
        this.xyDataLine = XYData.of(xListLine, yListLine);
    }


    public void clear() {
        xyDataScatter = xyDataLine = XYData.empty();
    }

    public XYChart create() {
        Preconditions.checkArgument(!xyDataScatter.isEmpty() || !xyDataLine.isEmpty()
                , "scatter and line should not both be empty");
        var chart = createChart(settings);
        addScatterData(chart);
        addLineData(chart);
        styleChart(chart, settings);
        return chart;
    }

    private static XYChart createChart(Settings s) {
        return new XYChartBuilder()
                .xAxisTitle(s.xAxisLabel).yAxisTitle(s.yAxisLabel)
                .width(s.width).height(s.height).build();
    }

    private void styleChart(XYChart chart, Settings s) {
        var styler = chart.getStyler();
        styler.setxAxisTickLabelsFormattingFunction(value ->
                getFormattedAsString(value, settings.axisTicksDecimalFormat));
        styler.setyAxisTickLabelsFormattingFunction(value ->
                getFormattedAsString(value, settings.axisTicksDecimalFormat));
        styler.setLegendVisible(false);
        styler.setAxisTitleFont(s.axisTitleFont);
        styler.setAxisTickLabelsFont(s.axisTicksFont);
        styler.setChartBackgroundColor(Color.WHITE);
        Conditionals.executeIfTrue(s.colorRangeSeries != null, () ->
                styler.setSeriesColors(s.colorRangeSeries));
    }

    private void addLineData(XYChart chart) {
        if (!xyDataLine.isEmpty()) {
            XYSeries lineSeries = chart.addSeries("Line", xyDataLine.x(), xyDataLine.y());
            lineSeries.setMarker(SeriesMarkers.NONE);
            lineSeries.setLineStyle(SeriesLines.SOLID);
        }
    }

    private void addScatterData(XYChart chart) {
        if (!xyDataScatter.isEmpty()) {
            XYSeries scatterSeries = chart.addSeries("Scatter Points", xyDataScatter.x(), xyDataScatter.y());
            scatterSeries.setMarker(SeriesMarkers.CIRCLE);
            scatterSeries.setLineStyle(SeriesLines.NONE);
        }
    }

    @Override
    public String toString() {
        return "ScatterWithLineChartCreator{" +
                ", xyDataScatter=" + xyDataScatter +
                ", xyDataLine=" + xyDataLine +
                '}';
    }


}
