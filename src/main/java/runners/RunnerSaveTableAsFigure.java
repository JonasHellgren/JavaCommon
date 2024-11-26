package runners;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.SneakyThrows;
import org.hellgren.plotters.table_saver.TableToImage;

public class RunnerSaveTableAsFigure {

    public static final int HEADER_ROW = 0;
    static String PATH="src/main/java/pics";

    @SneakyThrows
    public static void main(String[] args) {

        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(HEADER_ROW, 0, "Scenario");
        table.put(HEADER_ROW, 1, "Trading rev.");
        table.put(HEADER_ROW, 2, "HW cost");
        table.put(HEADER_ROW, 3, "House el cost");
        table.put(HEADER_ROW, 4, "Adj. rev.");
        table.put(HEADER_ROW, 5, "dSoH (ppm)");

        TableToImage toImage=new TableToImage(TableToImage.DEFAULT_SETTINGS
                .withWidth(600).withHeight(200).withXPos(10));
        toImage.saveTableAsFigure(table, PATH+"/table.png");

    }

}
