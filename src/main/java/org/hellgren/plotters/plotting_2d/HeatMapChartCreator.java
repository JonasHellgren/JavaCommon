package org.hellgren.plotters.plotting_2d;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.java.Log;
import org.hellgren.utilities.conditionals.Conditionals;
import org.hellgren.utilities.formatting.NumberFormatterUtil;
import org.hellgren.utilities.list_arrays.MyArrayUtil;
import org.hellgren.utilities.math.ScalerLinear;
import org.hellgren.utilities.vector_algebra.ArrayMatrix;
import org.jetbrains.annotations.NotNull;
import org.knowm.xchart.AnnotationText;
import org.knowm.xchart.HeatMapChart;
import org.knowm.xchart.HeatMapChartBuilder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.stream.IntStream;
import static org.hellgren.utilities.conditionals.Conditionals.executeIfTrue;
import static org.hellgren.utilities.conditionals.Conditionals.secDoubleIfFalse;
import static org.hellgren.utilities.formatting.NumberFormatterUtil.formatterOneDigit;
import static org.hellgren.utilities.list_arrays.MyMatrixArrayUtils.findMax;
import static org.hellgren.utilities.list_arrays.MyMatrixArrayUtils.findMin;

/**
 * This class is used to create a HeatMapChart.
 * It uses the Lombok @Builder annotation to generate a builder for the Settings class.
 * The Settings class is a record that holds the configuration for the HeatMapChart.
 * The HeatMapChartCreator class has a private constructor and is created using the ofSettings() or defaultSettings() methods.
 * The create() method is used to create the HeatMapChart.
 * The addData() method is used to add the data to the HeatMapChart.
 * The addCellText() method is used to add the cell text to the HeatMapChart.
 * The addTextToChart() method is used to add the text to the HeatMapChart.
 * <p>
 * chart.addSeries only works with integer arrays, hence data must be converted to integer arrays.
 * Therefore, data shall be wide, for example is data between 0 and 1 not appropriate
 */

@AllArgsConstructor
@Log
public class HeatMapChartCreator {
    static final int MANY_ROWS = 10;
    static final int MANY_COLS = 10;

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
            boolean showAxisTicks,
            String axisTicksDecimalFormat,
            Font axistTicksTextFont,
            Font annotationTextFont,
            Color annotationTextFontColor,
            int nDigitsAnnotationText,
            double minCellMargin,
            double maxCellMargin
    ) {

        public static SettingsBuilder defaultBuilder() {
            return Settings.builder().title("title").xAxisLabel("x").yAxisLabel("y")
                    .colorRange(new Color[]{Color.BLACK, Color.WHITE})
                    .width(500).height(300)
                    .showLegend(true).showDataValues(true)
                    .showAxisTicks(true).axisTicksDecimalFormat("#")
                    .axistTicksTextFont(new Font("Arial", Font.PLAIN, 12))
                    .annotationTextFont(new Font("Arial", Font.BOLD, 12))
                    .annotationTextFontColor(Color.BLUE)
                    .nDigitsAnnotationText(0)
                    .minCellMargin(-0.5).maxCellMargin(0.5);
        }

        public static Settings ofDefaults() {
            return defaultBuilder().build();
        }
    }

    private final Settings settings;
    private final double[][] data;  //looked up by data[y][x]
    private final int[] xData0;
    private final int[] yData0;

    public static HeatMapChartCreator defaultSettings(double[][] data) {
        return new HeatMapChartCreator(Settings.ofDefaults(), data, null, null);
    }

    public static HeatMapChartCreator of(Settings settings, double[][] data) {
        return new HeatMapChartCreator(settings, data, null, null);
    }

    public static HeatMapChartCreator of(Settings settings, double[][] data, double[] xData, double[] yData) {
        return new HeatMapChartCreator(
                settings,
                data,
                MyArrayUtil.doubleToInteger(xData),
                MyArrayUtil.doubleToInteger(yData));
    }

    /**
     * Creates a new HeatMapChart.
     *
     * @return The HeatMapChart.
     */
    public HeatMapChart create() {
        validate();
        var chart = createChart();
        addData(chart);
        executeIfTrue(settings.showDataValues, () -> addCellText(chart));
        return chart;
    }

    private void validate() {
        Preconditions.checkArgument(nRows() > 0, "data must have at least one row");
        Preconditions.checkArgument(nCols() > 0, "data must have at least one column");
        Conditionals.executeIfTrue(settings.showAxisTicks && manyRowsOrColumns(), () ->
                log.warning("To many rows or columns for axis ticks "));
        Conditionals.executeIfTrue(settings.showDataValues && manyRowsOrColumns(), () ->
                log.warning("To many rows or columns for showing data values "));
    }


    private boolean manyRowsOrColumns() {
        return nRows() > MANY_ROWS || nCols() > MANY_COLS;
    }

    private HeatMapChart createChart() {
        var chart = new HeatMapChartBuilder()
                .title(settings.title)
                .xAxisTitle(settings.xAxisLabel).yAxisTitle(settings.yAxisLabel)
                .width(settings.width).height(settings.height)
                .build();
        double minValue = Double.parseDouble(formatterOneDigit.format(findMin(data)));
        double maxValue = Double.parseDouble(formatterOneDigit.format(findMax(data)));
        var styler = chart.getStyler();
        styler.setChartTitleVisible(true).setLegendVisible(settings.showLegend);
        styler.setAxisTicksVisible(settings.showAxisTicks);
        styler.setAxisTickLabelsFont(settings.axistTicksTextFont);
        styler.setMin(minValue).setMax(maxValue).setRangeColors(settings.colorRange());
        styler.setAnnotationTextFontColor(settings.annotationTextFontColor);
        styler.setAnnotationTextFont(settings.annotationTextFont);
        styler.setxAxisTickLabelsFormattingFunction(value -> getFormattedAsString(value));
        styler.setyAxisTickLabelsFormattingFunction(value -> getFormattedAsString(value));
        return chart;
    }

    private String getFormattedAsString(Double value) {
        DecimalFormat df = new DecimalFormat(settings.axisTicksDecimalFormat);
        return df.format(value);
    }

    private void addData(HeatMapChart chart) {
        double[][] dataRot = ArrayMatrix.transposeMatrix(data);
        int[][] dataRotInt = ArrayMatrix.doubleToInt(dataRot);
        int[] xData = getXData(xData0, nCols());
        int[] yData = getYData(yData0, nRows());
        chart.addSeries("seriesname", xData, yData, dataRotInt);
    }

    /**
     * The scalers are needed to place the text in the right place, mid of the cells
     */

    private void addCellText(HeatMapChart chart) {
        double minMargin = settings.minCellMargin();
        double maxMargin = settings.maxCellMargin();
        int[] xData = getXData(xData0, nCols());
        int[] yData = getYData(yData0, nRows());
        var xScaler = new ScalerLinear(minMargin, nCols() - 1 + maxMargin, xData[0], xData[xData.length - 1]);
        var yScaler = new ScalerLinear(minMargin, nRows() - 1 + maxMargin, yData[0], yData[yData.length - 1]);
        for (int y = 0; y < nRows(); y++) {
            for (int x = 0; x < nCols(); x++) {
                String text = NumberFormatterUtil.getRoundedNumberAsString(data[y][x], settings.nDigitsAnnotationText());
                addTextToChart(chart, xScaler.calcOutDouble(x), yScaler.calcOutDouble(y), text);
            }
        }
    }

    private void addTextToChart(HeatMapChart chart, double xPos, double yPos, String text) {
        AnnotationText annotation = new AnnotationText(text, xPos, yPos, false);
        chart.addAnnotation(annotation);
    }

    private int[] getYData(int[] yData0, int nRows) {
        return (yData0 != null) ? yData0 : IntStream.rangeClosed(0, nRows - 1).toArray();
    }

    private int[] getXData(int[] xData0, int nCols) {
        return (xData0 != null) ? xData0 : IntStream.rangeClosed(0, nCols - 1).toArray();
    }

    private int nRows() {
        return ArrayMatrix.getDimensions(data).getFirst();
    }

    private int nCols() {
        return ArrayMatrix.getDimensions(data).getSecond();

    }

}
