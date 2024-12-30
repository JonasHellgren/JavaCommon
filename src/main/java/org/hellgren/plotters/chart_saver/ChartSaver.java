package org.hellgren.plotters.chart_saver;

import lombok.SneakyThrows;
import org.hellgren.utilities.file_io.PathAndFile;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.HeatMapChart;
import org.knowm.xchart.XYChart;

import static org.knowm.xchart.BitmapEncoder.saveBitmapWithDPI;

public class ChartSaver {

    public static final int DPI = 300;

    @SneakyThrows
    public static void saveHeatMapChart(HeatMapChart chart, PathAndFile pathAndFile) {
        saveBitmapWithDPI(chart, pathAndFile.fullName(), BitmapEncoder.BitmapFormat.PNG, DPI);
    }


    @SneakyThrows
    public static void saveXYChart(XYChart chart, PathAndFile pathAndFile) {
        saveBitmapWithDPI(chart, pathAndFile.fullName(), BitmapEncoder.BitmapFormat.PNG, DPI);
    }



}
