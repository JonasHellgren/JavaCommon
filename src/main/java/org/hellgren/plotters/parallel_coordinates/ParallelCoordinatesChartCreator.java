package org.hellgren.plotters.parallel_coordinates;

import com.google.common.collect.Lists;
import lombok.Builder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.None;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/***
 * This Java code creates a parallel coordinates plot using the XChart library.
 *
 * Here's a succinct explanation:
 *
 * The ParallelCoordinatesChartCreator class is a builder that takes in input names, output name, and data
 * (a list of LineData objects).
 * The create() method creates a new parallel coordinates plot with the specified width, height, and title.
 * The fillChart() method populates the chart with data from the LineData objects, where each object represents
 * a line in the plot. The line color is determined by the category of the LineData object.
 * The styleChart() method customizes the chart's appearance, including hiding the plot grid lines and legend,
 * and setting the x-axis tick labels to the input and output names.
 * The plot is designed to visualize relationships between multiple variables, where each line represents a
 * single data point and the x-axis represents the different variables.
 */

@Builder
public class ParallelCoordinatesChartCreator {

    private static final Map<Integer, Color> COLOR_MAP = Map.of(
            1, Color.RED.brighter(),
            2, Color.ORANGE,
            3, Color.GREEN.darker()
    );
    public static final boolean IS_GRID_LINES = true;


    String[] inputNames;
    String outputName;
    List<LineData> data;
    @Builder.Default
    Map<Integer, Color> categoryColorMap = COLOR_MAP;
    @Builder.Default
    int width = 600;
    @Builder.Default
    int height = 300;
    @Builder.Default
    float lineWidth = 0.5f;
    @Builder.Default
    boolean isPlotGridLinesVisible = IS_GRID_LINES;
    @Builder.Default
    private Color backgroundColor=Color.WHITE;

    /**
     * Creates a new parallel coordinates plot with the specified width, height, and title.
     *
     * @return The created XYChart object representing the parallel coordinates plot.
     */
    public XYChart create() {
        XYChart chart = createChart();
        fillChart(chart);
        styleChart(chart);
        return chart;
    }

    private XYChart createChart() {
        return new XYChartBuilder()
                .width(width).height(height)
                .title("Parallel Coordinates Plot")
                .xAxisTitle("Variables")
                .yAxisTitle("Value")
                .build();
    }

    private void fillChart(XYChart chart) {
        int si=0;
        for (LineData r : data) {
            List<Double> xData = Lists.newArrayList();
            List<Double> yData = Lists.newArrayList();
            for (int i = 0; i < inputNames.length; i++) {
                xData.add((double) i);
                yData.add(r.valuesInput().get(i));
            }
            xData.add((double) inputNames.length);
            yData.add(r.valueOutput());
            String seriesName = "series" + si++;
            var series= chart.addSeries(seriesName, xData, yData);
            series.setLineColor(getCategoryColor(r.category()));
            series.setLineStyle(SeriesLines.SOLID);
            series.setLineStyle(new BasicStroke(lineWidth));
            series.setMarker(new None());
        }
    }

    private void styleChart(XYChart chart) {
        XYStyler styler = chart.getStyler();
        styler.setPlotGridLinesVisible(false);
        styler.setLegendVisible(false);
        styler.setPlotGridLinesVisible(isPlotGridLinesVisible);
        styler.setPlotBackgroundColor(backgroundColor);
        styler.setChartBackgroundColor(backgroundColor);
        String[] allNames=mergeArrays(inputNames,new String[]{outputName});
        styler.setxAxisTickLabelsFormattingFunction(x -> allNames[x.intValue()]);
    }

    private  Color getCategoryColor(int category) {
        return categoryColorMap.getOrDefault(category,Color.BLACK);
    }

    public static String[] mergeArrays(String[] arr1, String[] arr2) {
       return Stream.concat(Stream.of(arr1), Stream.of(arr2))
               .toArray(String[]::new);
    }

}
