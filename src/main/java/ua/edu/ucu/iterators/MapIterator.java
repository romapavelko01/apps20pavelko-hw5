package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private final Iterator<Integer> myIterator;
    private final IntUnaryOperator function;

    public MapIterator(Iterator<Integer> inpIt, IntUnaryOperator inpFunc) {
        this.myIterator = inpIt;
        this.function = inpFunc;
    }

    @Override
    public boolean hasNext() {
        return myIterator.hasNext();
    }

    @Override
    public Integer next() {
        return function.apply(myIterator.next());
    }
}
