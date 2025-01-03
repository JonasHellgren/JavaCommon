package org.hellgren.plotters.deprecated;

import lombok.extern.java.Log;
import org.hellgren.plotters.shared.PlotPanel;
import org.jfree.data.xy.XYSeries;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
public final class PlotterMultiplePanelsTrajectory {

    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 220;

    private int numberOfPanels;
    private List<PlotPanel> panels;
    private JFrame frame;

    public PlotterMultiplePanelsTrajectory(List<String> titleList, String xAxisTitle) {
        this("", titleList, xAxisTitle);
    }

    public PlotterMultiplePanelsTrajectory(String title, List<String> titleList, String xAxisTitle) {
        numberOfPanels = titleList.size();
        panels = new ArrayList<>();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, numberOfPanels));
        frame.setTitle(title);
        frame.setSize(PANEL_WIDTH * numberOfPanels, PANEL_HEIGHT);

        for (int i = 0; i < numberOfPanels; i++) {
            PlotPanel panel = new PlotPanel("", xAxisTitle, titleList.get(i));
            panels.add(panel);
            frame.add(panel);
        }

        frame.setVisible(true);
    }

    /**
     * Plots the given trajectories on the panels. The number of trajectories must match the number of panels.
     *
     * @param trajectories A list of lists of doubles representing the trajectories.
     */
    public void plot(List<List<Double>> trajectories) {
        // Check if the number of trajectories matches the number of panels
        if (trajectories.size() != numberOfPanels) {
            // Log a warning if the numbers don't match
            log.warning("Number of trajectories does not match number of plot titles");
            return;
        }

        // Iterate over each panel
        for (int i = 0; i < numberOfPanels; i++) {
            // Define a data series from the current trajectory
            XYSeries series = defineDataSeries(trajectories.get(i));
            // Set the chart data from the series on the current panel
            panels.get(i).setChartDataFromSeries(series);
        }
    }

    private static XYSeries defineDataSeries(List<Double> values) {
        XYSeries series = new XYSeries("");
        int x = 0;
        for (Double value : values) {
            series.add(x++, value);
        }
        return series;
    }

    public void saveImage(String fileName) {
        try {
            BufferedImage image = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            frame.paint(graphics2D);
            ImageIO.write(image, "jpeg", new File(fileName + ".jpeg"));
        } catch (IOException e) {
            log.severe("Unable to save image: " + e.getMessage());
        }
    }
}
