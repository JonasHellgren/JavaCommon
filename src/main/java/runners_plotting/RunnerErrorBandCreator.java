package runners_plotting;

import org.hellgren.plotters.plotting_2d.ErrorBandCreator;
import javax.swing.*;
import java.awt.*;

public class RunnerErrorBandCreator {


    public static void main(String[] args) {

        double[] xData1 = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData1 = {5.0, 7.0, 6.0, 8.0, 10.0};
        double[] errData1 = {1.0, 1.0, 1.0, 2.0, 2.0};

        double[] xData2 = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData2 = {15.0, 17.0, 16.0, 18.0, 20.0};
        double[] errData2 = {1.0, 1.0, 1.0, 2.0, 2.0};

        var creator = ErrorBandCreator.newDefault();
        creator.addErrorBand("1",xData1, yData1, errData1, Color.BLACK);
        creator.addErrorBand("2",xData2, yData2, errData2,Color.GRAY);

        SwingUtilities.invokeLater(() -> creator.create().setVisible(true));
    }


}
