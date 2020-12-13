package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private final Iterator<Integer> myIterator;
    private final IntToIntStreamFunction function;
    private Iterator<Integer> helpIt;

    public FlatMapIterator(Iterator<Integer> inpIt, IntToIntStreamFunction inpFunc) {
        this.myIterator = inpIt;
        this.function = inpFunc;
        this.helpIt = new BaseIterator();
    }

    @Override
    public boolean hasNext() {
        if (helpIt.hasNext()) {
            return true;
        }

        if (myIterator.hasNext()) {
            AsIntStream my = (AsIntStream) function.applyAsIntStream(myIterator.next());
            helpIt = new BaseIterator(my.toArray());
            return true;
        }

        return false;
    }

    @Override
    public Integer next() {
        return helpIt.next();
    }
}
