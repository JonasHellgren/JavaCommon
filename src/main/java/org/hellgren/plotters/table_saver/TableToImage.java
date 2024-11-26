package org.hellgren.plotters.table_saver;

import com.google.common.collect.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.With;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class TableToImage {

    public static final TableSettings DEFAULT_SETTINGS = new TableSettings(800, 600, 20, 100, 50, 10);

    @Builder
    public record TableSettings(
            @With int width,
            @With int height,
            @With int rowHeight,
            @With int colWidth,
            @With int yPos,
            @With int xPos
    ) {}

    private TableSettings settings;

    public static TableToImage createDefault() {
        return new TableToImage(DEFAULT_SETTINGS);
    }

    public void saveTableAsFigure(Table<Integer, Integer, String> table, String outputFile) throws IOException {
        BufferedImage image = new BufferedImage(settings.width(), settings.height(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        setFont(g2d);
        setBackgroundColor(g2d);
        setTextColor(g2d);
        drawTableHeader(table, g2d);
        drawTableRows(table, g2d);
        g2d.dispose();
        writeImageToFile(outputFile, image);
    }

    private static void setFont(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    private void setBackgroundColor(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, settings.width(), settings.height());
    }

    private static void setTextColor(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
    }


    private void drawTableHeader(Table<Integer, Integer, String> table, Graphics2D g2d) {
        for (Integer col : table.columnKeySet()) {
            g2d.drawString(col.toString(), settings.xPos() + settings.colWidth() * col, settings.yPos());
        }
    }


    private void drawTableRows(Table<Integer, Integer, String> table, Graphics2D g2d) {
        int yPosRow = settings.yPos() + settings.rowHeight();
        for (Integer row : table.rowKeySet()) {
            for (Integer col : table.columnKeySet()) {
                String value = table.get(row, col);
                g2d.drawString(value, settings.xPos() + settings.colWidth() * col, yPosRow);
            }
            yPosRow += settings.rowHeight();
        }
    }


    private static void writeImageToFile(String outputFile, BufferedImage image) throws IOException {
        ImageIO.write(image, "png", new File(outputFile+".png"));
    }

}
