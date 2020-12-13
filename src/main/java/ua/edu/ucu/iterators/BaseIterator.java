package ua.edu.ucu.iterators;

import java.util.Iterator;

public class BaseIterator implements Iterator<Integer> {
    private int currIndex;
    private final int[] myValues;
    public BaseIterator(int... values) {
        myValues = values;
    }

    @Override
    public boolean hasNext() {
        return currIndex < myValues.length;
    }

    @Override
    public Integer next() {
        return myValues[currIndex++];
    }
}
