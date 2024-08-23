package org.hellgren.utilities.list_arrays;

import java.util.ArrayList;
import java.util.List;


/***
 * Example [[1 2 3],[1 2 3]] -> [1 1], [2 2] and [3 3].
 */

public class ListTransposer {

    public static List<List<Double>> transpose(List<List<Double>> originalList) {
        List<List<Double>> result = new ArrayList<>();
        if (!originalList.isEmpty()) {
            // Initialize the result lists
            int size = originalList.get(0).size();
            for (int i = 0; i < size; i++) {
                result.add(new ArrayList<>());
            }

            // Populate the result lists
            for (List<Double> innerList : originalList) {
                for (int i = 0; i < innerList.size(); i++) {
                    result.get(i).add(innerList.get(i));
                }
            }
        }
        return result;
    }


}
