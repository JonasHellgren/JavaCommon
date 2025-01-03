package runners_plotting;

import org.hellgren.plotters.deprecated.PlotterMultiplePanelsTrajectory;

import java.util.ArrayList;
import java.util.List;

public class RunnerMultiplePanelsTrajectory {

    public static void main(String[] args) {
        var plotter = new PlotterMultiplePanelsTrajectory(
                List.of("A","B"),"x");
        List<List<Double>> listOfTrajectories = new ArrayList<>();
        listOfTrajectories.add(List.of(1d,2d,3d));
        listOfTrajectories.add(List.of(10d,20d,30d));
        plotter.plot(listOfTrajectories);
    }
}
