package org.hellgren.plotters.shared;

import lombok.Builder;
import lombok.With;
import org.knowm.xchart.style.Styler;
import java.awt.*;

/**
 * A record class representing the settings for a plot. Used by multiple chart creators.
 * It provides default values for various plot settings and allows for customization.
 */

@Builder
@With
public record PlotSettings (
        String title,
        String xAxisLabel,
        String yAxisLabel,
        int width,
        int height,
        boolean showLegend,
        Styler.LegendPosition legendPosition,
        Font legendTextFont,
        boolean showAxisTicks,
        boolean showGridLines,
        boolean showMarker,
        Color[] colorRangeSeries,
        String axisTicksDecimalFormat,
        Font axisTitleFont,
        Font axisTicksFont,
        Color[] colorRange,  //this and below is specific for HeatMapChartCreator
        boolean showDataValues,
        Font annotationTextFont,
        Color annotationTextFontColor,
        int nDigitsAnnotationText,
        double minCellMargin,
        double maxCellMargin,
        Color bandPlotBackGroundColor, //this and below is specific for ErrorBandCreator
        Color gridLineColor,
        Color lineColorBand,
        Color bandFillColor
) {

    public static PlotSettings.PlotSettingsBuilder defaultBuilder() {
        return PlotSettings.builder()
                .title("").xAxisLabel("x").yAxisLabel("y")
                .width(500).height(300)
                .showLegend(true).legendPosition(Styler.LegendPosition.OutsideE)
                .legendTextFont(new Font("Arial", Font.PLAIN, 10))
                .showGridLines(true)
                .colorRangeSeries(new Color[]{Color.BLACK, Color.GRAY})
                .showAxisTicks(true).axisTicksDecimalFormat("#").showMarker(false)
                .axisTitleFont(new Font("Arial", Font.BOLD, 12))
                .axisTicksFont(new Font("Arial", Font.PLAIN, 12))

                .colorRange(new Color[]{Color.BLACK, Color.WHITE})
                .showDataValues(true)
                .annotationTextFont(new Font("Arial", Font.BOLD, 12))
                .annotationTextFontColor(Color.BLUE)
                .nDigitsAnnotationText(0)
                .minCellMargin(-0.5).maxCellMargin(0.5)
                .bandPlotBackGroundColor(Color.WHITE)
                .gridLineColor(Color.GRAY)
                .lineColorBand(Color.BLACK)
                .bandFillColor(Color.LIGHT_GRAY)
                ;
    }

    public static PlotSettings ofDefaults() {
        return defaultBuilder().build();
    }

}
