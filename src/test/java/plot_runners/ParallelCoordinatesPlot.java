package plot_runners;

import org.hellgren.plotters.parallel_coordinates.LineData;
import org.hellgren.plotters.parallel_coordinates.NormalizeLineData;
import org.hellgren.plotters.parallel_coordinates.ParallelCoordinatesChartCreator;
import org.knowm.xchart.SwingWrapper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ParallelCoordinatesPlot {
    public static void main(String[] args) {

        // Prepare Data
        List<LineData> data = prepareData();
        String[] inputNames = {"Input1", "Input2", "Input3"};
        String outputName = "Output";


        var dataNorm= NormalizeLineData.normalize(data);
        var creator= ParallelCoordinatesChartCreator.builder()
                .inputNames(inputNames).outputName(outputName)
                .data(dataNorm).build();
        new SwingWrapper<>(creator.create()).displayChart();
    }


    static Function<Double, Integer> categorize = (Double d) -> {
        int intValue = d.intValue(); // Convert double to int
        if (intValue < 5) {
            return 1;
        } else if (intValue <= 10) {
            return 2;
        } else {
            return 3;
        }
    };

    private static   List<LineData> prepareData() {
        List<LineData> data = new ArrayList<>();
        data.add(LineData.ofCatFcn(List.of(1d, 3d, 5d), 3d, categorize));
        data.add(LineData.ofCatFcn(List.of(2d, 14d, 6d), 14d, categorize));
        data.add(LineData.ofCatFcn(List.of(3d, 2d, 7d), 2d, categorize));
        data.add(LineData.ofCatFcn(List.of(4d, 5d, 8d), 5d, categorize));
        data.add(LineData.ofCatFcn(List.of(5d, 11d, 9d), 11d, categorize));
        return data;
    }

    private static Map<String, Double> createRecord(
            double input1, double input2, double input3, double output, double category) {
        Map<String, Double> record = new HashMap<>();
        record.put("Input1", input1);
        record.put("Input2", input2);
        record.put("Input3", input3);
        record.put("Output", output);
        record.put("Category", category);
        return record;
    }


}
