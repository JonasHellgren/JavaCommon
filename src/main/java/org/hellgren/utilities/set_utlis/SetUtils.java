package org.hellgren.utilities.set_utlis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtils {

    public  static <T> boolean allSame(List<T> list) {
        T firstItem = list.get(0);
        return list.stream().allMatch(i -> i.equals(firstItem));
    }

    public  static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> union=new HashSet<>(list1);
        union.addAll(list2);
        return new ArrayList<>(union);
    }


    public  static <T> List<T> difference(List<T> list1, List<T> list2) {
        List<T> union = union(list1, list2);
        Set<T> difference = new HashSet<>(union);
        intersection(list1, list2).forEach(difference::remove);
        return new ArrayList<>(difference);
    }


    public  static <T> List<T> intersection(List<T> list1, List<T> list2) {
        Set<T> same=new HashSet<>(list1);
        same.retainAll(list2);
        return new ArrayList<>(same);
    }

}
