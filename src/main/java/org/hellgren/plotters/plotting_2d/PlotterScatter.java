package org.hellgren.plotters.plotting_2d;

import org.apache.commons.lang3.tuple.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class PlotterScatter {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 400;
    private static final String DEFAULT_TITLE = "";
    private static final Color DEFAULT_COLOR = Color.BLACK;

    private final String xAxisLabel;
    private final String yAxisLabel;
    private final JFrame frame;

    public PlotterScatter(String xAxisLabel, String yAxisLabel) {
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.frame = new JFrame(DEFAULT_TITLE);
    }

    public void plot(List<Pair<Double, Double>> dataPoints) {
        JFreeChart chart = createScatterPlot(dataPoints);
        customizeChart(chart);
        displayChart(chart);
    }

    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, DEFAULT_COLOR);
    }

    private JFreeChart createScatterPlot(List<Pair<Double, Double>> dataPoints) {
        XYSeries series = new XYSeries(DEFAULT_TITLE);
        dataPoints.forEach(pair -> series.add(pair.getLeft(), pair.getRight()));

        XYDataset dataset = new XYSeriesCollection(series);

        return ChartFactory.createScatterPlot(
                DEFAULT_TITLE,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private void displayChart(JFreeChart chart) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}