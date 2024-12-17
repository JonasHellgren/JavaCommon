package org.hellgren.plotters.plotting_2d;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hellgren.utilities.conditionals.Conditionals;
import org.hellgren.utilities.list_arrays.MyMatrixArrayUtils;
import org.hellgren.utilities.math.ScalerLinear;
import org.hellgren.utilities.vector_algebra.ArrayMatrix;
import org.knowm.xchart.AnnotationText;
import org.knowm.xchart.HeatMapChart;
import org.knowm.xchart.HeatMapChartBuilder;
import org.knowm.xchart.style.HeatMapStyler;

import java.awt.*;
import java.util.stream.IntStream;

@AllArgsConstructor
public class HeatMapChartCreator {

    @Builder
    public record Settings(
            String title,
            String xAxisLabel,
            String yAxisLabel,
            Color[] colorRange,
            int width,
            int height,
            boolean showLegend,
            boolean showDataValues,
            Font annotationTextFont,
            Color annotationTextFontColor,
            double minCellMargin,
            double maxCellMargin
    ) {

        public static SettingsBuilder defaultBuilder() {
            return Settings.builder().title("title").xAxisLabel("x").yAxisLabel("y")
                    .colorRange(new Color[]{Color.BLACK, Color.WHITE})
                    .width(500).height(300)
                    .showLegend(true).showDataValues(false)
                    .annotationTextFont(new Font("Arial", Font.BOLD, 12))
                    .annotationTextFontColor(Color.BLUE)
                    .minCellMargin(-0.5).maxCellMargin(0.8);
        }

        public static Settings ofDefaults() {
            return defaultBuilder().build();
        }
    }

    Settings settings;
    int[][] data;  //looked up by data[y][x]

    public static HeatMapChartCreator ofSettings(Settings settings, int[][] data) {
        return new HeatMapChartCreator(settings, data);
    }

    public static HeatMapChartCreator defaultSettings(int[][] data) {
        return new HeatMapChartCreator(Settings.ofDefaults(), data);
    }

    public HeatMapChart create() {
        HeatMapChart chart = createChart();
        addData(chart);
        Conditionals.executeIfTrue(settings.showDataValues, () -> addCellText(chart));
        return chart;
    }

    private HeatMapChart createChart() {
        var chart = new HeatMapChartBuilder()
                .title(settings.title)
                .xAxisTitle(settings.xAxisLabel)
                .yAxisTitle(settings.yAxisLabel)
                .width(settings.width).height(settings.height)
                .build();
        double minValue = MyMatrixArrayUtils.findMin(data);
        double maxValue = MyMatrixArrayUtils.findMax(data);
        HeatMapStyler styler = chart.getStyler();
        styler.setChartTitleVisible(true).setLegendVisible(settings.showLegend);
        styler.setMin(minValue).setMax(maxValue).setRangeColors(settings.colorRange());
        styler.setAnnotationTextFontColor(settings.annotationTextFontColor);
        styler.setAnnotationTextFont(settings.annotationTextFont);
        return chart;
    }

    private void addData(HeatMapChart chart) {
        int[][] dataRot = ArrayMatrix.transposeMatrix(data);
        int[] xData = IntStream.rangeClosed(0, nCols() - 1).toArray();
        int[] yData = IntStream.rangeClosed(0, nRows() - 1).toArray();
        chart.addSeries("a", xData, yData, dataRot);
    }

    private int nRows() {
        return ArrayMatrix.getDimensions(data).getFirst();
    }

    private int nCols() {
        return ArrayMatrix.getDimensions(data).getSecond();

    }

    private void addCellText(HeatMapChart chart) {
        double minMargin= settings.minCellMargin();
        double maxMargin= settings.maxCellMargin();
        var xScaler = new ScalerLinear(minMargin, nCols() + maxMargin, 0, nCols());
        var yScaler = new ScalerLinear(minMargin, nRows() + maxMargin, 0, nRows());
        for (int y = 0; y < nRows(); y++) {
            for (int x = 0; x < nCols(); x++) {
                String text = String.valueOf(data[y][x]);
                addTextToChart(chart, xScaler.calcOutDouble(x), yScaler.calcOutDouble(y), text);
            }
        }
    }

    private void addTextToChart(HeatMapChart chart, double xPos, double yPos, String text) {
        AnnotationText annotation = new AnnotationText(text, xPos, yPos, false);
        chart.addAnnotation(annotation);
    }


}
