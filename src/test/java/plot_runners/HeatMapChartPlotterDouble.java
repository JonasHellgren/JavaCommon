package plot_runners;


import org.hellgren.plotters.plotting_2d.SwingHeatMapPlotter;

public class HeatMapChartPlotterDouble {

        public static void main(String[] args) {
            double[][] data = {
                    {1.0, 2.0, 3.0},
                    {2.0, 3.0, 4.0},
                    {5.0, 4.0, 5.0}
            };


            var shower= SwingHeatMapPlotter.builder()
                    .xAxisLabels(new String[]{"X1", "X2", "X3"})
                    .yAxisLabels(new String[]{"Y1", "Y2", "Y3"})
                    .showLabels(true)
                    .build();

            shower.showHeatMap(data,"map");
        }



    }




