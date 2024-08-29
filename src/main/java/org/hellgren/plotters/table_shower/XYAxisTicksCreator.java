package org.hellgren.plotters.table_shower;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.hellgren.utilities.conditionals.Conditionals;
import org.hellgren.utilities.list_arrays.ListCreator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *  Creates ticks on x and y axis
 *
 */

@AllArgsConstructor
public class XYAxisTicksCreator {

    TableSettings s;

    public String[] columnNames() {
        Preconditions.checkArgument(s.isNofColNamesOk(),"Bad nof col names");
        String[] columnNames = new String[s.nX() + 1];
        columnNames[0] = s.yName() + "\\" + s.xName();
        Conditionals.executeOneOfTwo(s.colNames().isPresent(),
                () -> replaceFromIndexOne(columnNames, arr2List(s.colNames().orElseThrow())),
                () -> replaceFromIndexOne(columnNames, getNumbersAsStringList()));
        return columnNames;
    }

    public String[] createRowNames() {
        Preconditions.checkArgument(s.isNofRowNamesOk(),"Bad nof row names");
        return s.colNames().isPresent()
                ? s.rowNames().orElseThrow()
                : double2String(ListCreator.createFromStartWithStepWithNofItems(
                        s.nYstart(), s.nYstep(), s.nY()));
    }

    List<String> getNumbersAsStringList() {
        var xSpace = ListCreator.createFromStartWithStepWithNofItems(s.nXstart(), s.nXstep(), s.nX());
        return xSpace.stream().map(n -> String.format(s.formatTicks(), n)).toList();
    }

    void replaceFromIndexOne(String[] columnNames, List<String> doubleList) {
        int startNameIndex = 1;
        IntStream.range(0, doubleList.size())
                .forEach(i -> columnNames[i + startNameIndex] = doubleList.get(i));
    }

    List<String> arr2List(String[] array) {
        return Arrays.stream(array).toList();
    }

    String[] double2String(List<Double> ySpace) {
        return ySpace.stream()
                .map(n -> String.format(s.formatTicks(), n)).toArray(String[]::new);
    }


}
