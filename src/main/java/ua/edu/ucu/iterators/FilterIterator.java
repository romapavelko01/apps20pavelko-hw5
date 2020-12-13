package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    private final Iterator<Integer> myIterator;
    private final IntPredicate predicate;
    private int myInt;

    public FilterIterator(Iterator<Integer> inpIt, IntPredicate inpPredicate) {
        this.myIterator = inpIt;
        this.predicate = inpPredicate;
    }

    @Override
    public boolean hasNext() {
        while (myIterator.hasNext()) {
            myInt = myIterator.next();
            if (predicate.test(myInt)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return myInt;
    }
}
