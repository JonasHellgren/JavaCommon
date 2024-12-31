package org.hellgren.plotters.three_dim;

import lombok.Builder;
import org.hellgren.utilities.math.MyMathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.IntStream;

@Builder
public final class SwingHeatMapPlotter {

    private static final int MIN_DARKNESS = 100;
    @Builder.Default
    private String valueFormat = "%.2f";
    @Builder.Default
    private int plotWidth = 300;
    @Builder.Default
    private int plotHeight = 200;
    @Builder.Default
    private boolean showLabels = true;
    private String[] xAxisLabels;
    private String[] yAxisLabels;
    @Builder.Default
    private int margin = 50;

    /**
     * Displays a heat map representation of the provided 2D data.
     *
     * @param data  the 2D data to be visualized
     * @param title the title of the heat map
     */

    public void showHeatMap(double[][] data, String title) {
        BufferedImage image = createHeatMapImage(data, title);
        displayImage(image);
    }

    private BufferedImage createHeatMapImage(double[][] data, String title) {
        BufferedImage image = new BufferedImage(plotWidth, plotHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int usableWidth = plotWidth - margin;
        int usableHeight = plotHeight - margin;
        int cellWidth = usableWidth / data[0].length;
        int cellHeight = usableHeight / data.length;

        drawHeatMap(data, graphics, cellWidth, cellHeight);
        drawLabels(data, graphics, usableHeight, cellWidth, cellHeight);
        drawTitle(title, graphics);
        graphics.dispose();
        return image;
    }

    private void drawHeatMap(double[][] data, Graphics2D graphics, int cellWidth, int cellHeight) {
        double minValue = Arrays.stream(data).flatMapToDouble(Arrays::stream).min().orElse(0);
        double maxValue = Arrays.stream(data).flatMapToDouble(Arrays::stream).max().orElse(1);

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                double value = data[row][col];
                Color color = calculateColor(value, minValue, maxValue);
                graphics.setColor(color);
                int x = col * cellWidth + margin;
                int y = (data.length - 1 - row) * cellHeight;
                graphics.fillRect(x, y, cellWidth, cellHeight);

                graphics.setColor(Color.BLACK);
                String text = String.format(valueFormat, value);
                FontMetrics metrics = graphics.getFontMetrics();
                int textX = x + (cellWidth - metrics.stringWidth(text)) / 2;
                int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent();
                graphics.drawString(text, textX, textY);
            }
        }
    }

    private void drawTitle(String title, Graphics2D graphics) {
        graphics.setFont(new Font("Serif", Font.BOLD, 20));
        FontMetrics titleMetrics = graphics.getFontMetrics();
        int titleX = (plotWidth - titleMetrics.stringWidth(title)) / 2;
        int titleY = titleMetrics.getAscent();
        graphics.drawString(title, titleX, titleY);
    }

    private void drawLabels(double[][] data, Graphics2D graphics, int usableHeight, int cellWidth, int cellHeight) {
        if (!showLabels) {
            return;
        }

        if (xAxisLabels == null) {
            xAxisLabels = IntStream.range(0, data[0].length).mapToObj(Integer::toString).toArray(String[]::new);
        }
        for (int i = 0; i < xAxisLabels.length; i++) {
            String label = xAxisLabels[i];
            graphics.drawString(label, i * cellWidth + cellWidth / 2 + margin - graphics.getFontMetrics().stringWidth(label) / 2, usableHeight + 20);
        }

        if (yAxisLabels == null) {
            yAxisLabels = IntStream.range(0, data.length).mapToObj(Integer::toString).toArray(String[]::new);
        }
        for (int i = 0; i < yAxisLabels.length; i++) {
            String label = yAxisLabels[i];
            graphics.drawString(label, 5, (yAxisLabels.length - 1 - i) * cellHeight + cellHeight / 2 + graphics.getFontMetrics().getAscent() / 2);
        }
    }

    private Color calculateColor(double value, double minValue, double maxValue) {
        double ratio = (value - minValue) / (maxValue - minValue);
        int r = MyMathUtils.clip((int) (ratio * 255) + MIN_DARKNESS, 0, 255);
        return new Color(r, r, r);
    }

    private void displayImage(BufferedImage image) {
        SwingUtilities.invokeLater(() -> {
            ImageIcon icon = new ImageIcon(image);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new JLabel(icon), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
