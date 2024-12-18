package runners_plotting;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.*;

import javax.swing.*;
import java.awt.*;

public class RunnerErrorChartCreator {


    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("XYErrorRenderer Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // Create dataset
                YIntervalSeriesCollection dataset = createDataset();

                // Create chart
                JFreeChart chart = ChartFactory.createScatterPlot(
                        "Scatter Plot with Error Bars",
                        "X-Axis",
                        "Y-Axis",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );

                // Customize renderer for error bars
                XYPlot plot = chart.getXYPlot();
               /* XYErrorRenderer renderer = new XYErrorRenderer();
                renderer.setDrawYError(true);  // Show error bar lines
                renderer.setDefaultLinesVisible(true);*/
  //              renderer.setBaseShapesVisible(true); // Show data points
                DeviationRenderer renderer = new DeviationRenderer(true, true);
                renderer.setSeriesFillPaint(0, new Color(255, 200, 200)); // Set color for error band
                renderer.setSeriesPaint(0, Color.RED); // Line color
                renderer.setSeriesShapesVisible(0, false); // Disable markers
                plot.setRenderer(renderer);

                // Add chart to panel
                ChartPanel chartPanel = new ChartPanel(chart);
                frame.add(chartPanel, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            });
        }

        private static YIntervalSeriesCollection createDataset() {
            YIntervalSeries series = new YIntervalSeries("Series 1");

            // Add data points with error margins (X, Y, Y-low, Y-high)
            series.add(1.0, 5.0, 4.5, 5.5);  // X = 1.0, Y = 5.0, error = ±0.5
            series.add(2.0, 7.0, 6.5, 7.5);  // X = 2.0, Y = 7.0, error = ±0.5
            series.add(3.0, 6.0, 5.5, 6.5);  // X = 3.0, Y = 6.0, error = ±0.5
            series.add(4.0, 8.0, 7.8, 8.2);  // X = 4.0, Y = 8.0, error = ±0.2
            series.add(5.0, 10.0, 9.5, 10.5); // X = 5.0, Y = 10.0, error = ±0.5

            YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
            dataset.addSeries(series);

            return dataset;
        }

}
