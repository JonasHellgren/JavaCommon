package plot_runners;

import org.apache.commons.lang3.tuple.Pair;
import org.hellgren.plotters.plotting_2d.PlotterScatter;

import java.util.List;

public class RunnerScatterPlotter {

    public static void main(String[] args) {
        List<Pair<Double,Double>> dataPairs=List.of(Pair.of(1d,-1d),Pair.of(2d,2d),Pair.of(3d,3d));
        PlotterScatter plotter=new PlotterScatter("posReal","value");
        plotter.plot(dataPairs);
    }
}
