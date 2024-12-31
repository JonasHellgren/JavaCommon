package runners_plotting;


import org.hellgren.plotters.three_dim.SwingHeatMapPlotter;

public class RunnerSwingHeatMapPlotter {

        public static void main(String[] args) {
            double[][] data = {  //[y][x]
                    {1.0, 2.0, 3.0},
                    {2.0, 3.0, 4.0},
                    {5.0, 4.0, 5.0}
            };

            System.out.println("data[0][2] = " + data[0][2]);
            System.out.println("data[1][2] = " + data[1][2]);

            var shower= SwingHeatMapPlotter.builder()
                    .xAxisLabels(new String[]{"X0", "X1", "X2"})
                    .yAxisLabels(new String[]{"Y0", "Y1", "Y2"})
                    .showLabels(true)
                    .build();

            shower.showHeatMap(data,"map");
        }



    }




