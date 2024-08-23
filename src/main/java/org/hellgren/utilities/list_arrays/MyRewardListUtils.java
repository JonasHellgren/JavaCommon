package org.hellgren.utilities.list_arrays;

import com.beust.jcommander.internal.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.hellgren.utilities.list_arrays.MyListUtils.dotProduct;
import static org.hellgren.utilities.list_arrays.MyListUtils.elementProduct;

public final class MyRewardListUtils {

    private MyRewardListUtils()
    {}

    /**
     *  list = [10 10 10], df=0.5 => listDf=[1*df^0 1*df^1 1*df^2] => dotProduct(list,listDf)=10+5+2.5
     */
    public static double discountedSum(List<Double> list, double discountFactor) {
        List<Double> listDf = getDiscountList(list.size(), discountFactor);
        return dotProduct(list,listDf);
    }


    /**
     * 1d,10d,10d , df=0.5->  10d,5d,2.5d
     */

    public static List<Double> discountedElements(List<Double> list, double discountFactor) {
        List<Double> listDf = getDiscountList(list.size(), discountFactor);
        return elementProduct(list,listDf);
    }

    /**
     * 1d,10d,10d , df=0.5->  0.25d,5d,10d
     */

    public static List<Double> discountedElementsReverse(List<Double> list, double discountFactor) {
        List<Double> listDf = getDiscountList(list.size(), discountFactor);
        Collections.reverse(listDf);
        return elementProduct(list,listDf);
    }

    /**
     rewards=[0,1,1] => returns=[2,2,1]
     */

    public static List<Double> getReturns(List<Double> rewards) {
        double singleReturn = 0;
        List<Double> returns = new ArrayList<>();
        for (int i = rewards.size() - 1; i >= 0; i--) {
            singleReturn = singleReturn + rewards.get(i);
            returns.add(singleReturn);
        }
        Collections.reverse(returns);
        return returns;
    }

    public static List<Double> getDiscountList(int len, double discountFactor) {
        List<Double> listDf = new ArrayList<>();
        double df = 1;
        for (int i = 0; i < len; i++) {
            listDf.add(df);
            df = df * discountFactor;
        }
        return listDf;
    }

    public static List<Double> cumulativeSum(List<Double> values) {
        double sum=0;
        List<Double> accumValues= Lists.newArrayList();
        for (Double value : values) {
            sum += value;  // Update the running sum
            accumValues.add(sum);  // Add the running sum to the accumValues list
        }
        return accumValues;
    }
}
