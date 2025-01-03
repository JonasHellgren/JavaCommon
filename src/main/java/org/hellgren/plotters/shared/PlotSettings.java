package org.hellgren.plotters.shared;

import lombok.Builder;
import lombok.With;
import org.knowm.xchart.style.Styler;
import java.awt.*;

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
        Font axisTicksFont
) {

    public static PlotSettings.PlotSettingsBuilder defaultBuilder() {
        return PlotSettings.builder()
                .title("title").xAxisLabel("x").yAxisLabel("y")
                .width(500).height(300)
                .showLegend(true).legendPosition(Styler.LegendPosition.OutsideE)
                .legendTextFont(new Font("Arial", Font.PLAIN, 10))
                .showGridLines(true)
                .colorRangeSeries(null)
                .showAxisTicks(true).axisTicksDecimalFormat("#").showMarker(false)
                .axisTitleFont(new Font("Arial", Font.BOLD, 12))
                .axisTicksFont(new Font("Arial", Font.PLAIN, 12));
    }

    public static PlotSettings ofDefaults() {
        return defaultBuilder().build();
    }

}
