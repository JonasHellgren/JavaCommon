package org.hellgren.utilities.conditionals;

import lombok.Getter;

public final class Counter {

    @Getter
    private int count;
    private final int minCount;
    private final int maxCount;

    public Counter() {
        this(0,Integer.MAX_VALUE);
    }

    public Counter(int maxCount) {
        this(0,maxCount);
    }

    public Counter(int minCount, int maxCount) {

        if (maxCount<minCount) {
            throw new IllegalArgumentException("maxCount<minCount");
        }

        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public static Counter empty()        { return new Counter(0,0); }

    public static Counter ofMaxCount(Integer maxCount) {
        return  new Counter(0, maxCount);
    }

    public void increase() {
        count++;
    }

    public boolean isExceeded() {
        return count>=maxCount;
    }

    public boolean isNotExceeded() {
        return !isExceeded();
    }

    public boolean isBelowMinCount() {
        return count<minCount;
    }

    public void reset() {
        count=0;
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }


}
