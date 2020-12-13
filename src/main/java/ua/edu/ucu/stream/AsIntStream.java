package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.BaseIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private final Iterator<Integer> myIterator;

    private AsIntStream(Iterator<Integer> inpIt) {
        this.myIterator = inpIt;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new BaseIterator(values));
    }

    public void isEmpty() {
        if (!myIterator.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        isEmpty();
        int sum = 0;
        int size = 0;
        while (myIterator.hasNext()) {
            sum += myIterator.next();
            size += 1;
        }
        return (double) sum/size;
    }

    @Override
    public Integer max() {
        isEmpty();
        double myMax = Double.NEGATIVE_INFINITY;
        while (myIterator.hasNext()) {
            int next = myIterator.next();
            if (next > myMax) {
                myMax = next;
            }
        }
        return (int) myMax;
    }

    @Override
    public Integer min() {
         isEmpty();
         double myMin = Double.POSITIVE_INFINITY;
         while (myIterator.hasNext()) {
             int next = myIterator.next();
             if (next < myMin) {
                 myMin = next;
             }
         }
         return (int) myMin;
    }

    @Override
    public long count() {
        isEmpty();
        return reduce(0, (count, x) -> count + 1);
    }

    @Override
    public Integer sum() {
        isEmpty();
        return reduce(0, Integer::sum);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(myIterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (myIterator.hasNext()) {
            action.accept(myIterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(myIterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(myIterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int res = identity;
        while (myIterator.hasNext()) {
            res = op.apply(res, myIterator.next());
        }
        return res;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arr = new ArrayList<>();
        while (myIterator.hasNext()) {
            arr.add(myIterator.next());
        }
        int[] resArr = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            resArr[i] = arr.get(i);
        }
        return resArr;
    }

    public Iterator<Integer> getMyIterator() {
        return myIterator;
    }

}
