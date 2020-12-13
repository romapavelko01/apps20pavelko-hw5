package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

public class AsIntStreamTest {

    private IntStream emptyStream;
    private IntStream nonEmptyStream;
    private IntStream oneElStream;

    @Before
    public void init() {
        int[] emptyArr = {};
        int[] nonEmptyArr = {-3, -8, -2, 9, 15, 10, 7, 4, 6, 5};
        int[] oneElArr = {4};
        emptyStream = AsIntStream.of(emptyArr);
        nonEmptyStream = AsIntStream.of(nonEmptyArr);
        oneElStream = AsIntStream.of(oneElArr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        double myAverage = emptyStream.average();
    }

    @Test
    public void testAverage() {
        double expectedAvgOne = 4.3;
        double actAvgOne = nonEmptyStream.average();

        double expectedAvgTwo = 4.0;
        double actAvgTwo = oneElStream.average();

        Assert.assertEquals(expectedAvgOne, actAvgOne, 0.001);
        Assert.assertEquals(expectedAvgTwo, actAvgTwo, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxErr() {
        int myMax = emptyStream.max();
    }

    @Test
    public void testMax() {
        int expMaxOne = 15;
        int actMaxOne = nonEmptyStream.max();
        int expMaxTwo = 4;
        int actMaxTwo = oneElStream.max();

        Assert.assertEquals(expMaxOne, actMaxOne, 0.001);
        Assert.assertEquals(expMaxTwo, actMaxTwo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinErr() {
        int myMin = emptyStream.min();
    }

    @Test
    public void testMin() {
        int expMinOne = -8;
        int actMinOne = nonEmptyStream.min();
        int expMinTwo = 4;
        int actMinTwo = oneElStream.min();

        Assert.assertEquals(expMinOne, actMinOne, 0.001);
        Assert.assertEquals(expMinTwo, actMinTwo, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountErr() {
        long myCount = emptyStream.count();
    }

    @Test
    public void testCount() {
        long expOne = 10;
        long actOne = nonEmptyStream.count();
        long expTwo = 1;
        long actTwo = oneElStream.count();

        Assert.assertEquals(expOne, actOne, 0.001);
        Assert.assertEquals(expTwo, actTwo, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumErr() {
        int mySum = emptyStream.sum();
    }

    @Test
    public void testSum() {
        int expSumOne = 43;
        int actSumOne = nonEmptyStream.sum();
        int expSumTwo = 4;
        int actSumTwo = oneElStream.sum();

        Assert.assertEquals(expSumOne, actSumOne);
        Assert.assertEquals(expSumTwo, actSumTwo);
    }

    @Test()
    public void testReduce() {
        int expEmpty = 1;
        int actEmpty = emptyStream.reduce(1, (y, x) -> y *= x);

        int expOne = 4;
        int actOne = oneElStream.reduce(1, (y, x) -> y *= x);

        int expMany = -54432000;
        int actMany = nonEmptyStream.reduce(1, (y, x) -> y *= x);

        Assert.assertEquals(expEmpty, actEmpty, 0.001);
        Assert.assertEquals(expOne, actOne, 0.001);
        Assert.assertEquals(expMany, actMany, 0.001);
    }

    @Test
    public void testFilter() {
        int[] expArrayOne = {9, 15, 10, 7, 4, 6, 5};
        int[] actArrayOne = nonEmptyStream.filter(x -> x > 0).toArray();

        int[] expFinalArr = {};
        int[] actArrFinalOne = emptyStream.filter(x -> x + 1 > 0).toArray();
        int[] actArrFinalTwo = oneElStream.filter(x -> x < 0).toArray();

        Assert.assertArrayEquals(expArrayOne, actArrayOne);
        Assert.assertArrayEquals(expFinalArr, actArrFinalOne);
        Assert.assertArrayEquals(expFinalArr, actArrFinalTwo);
    }

    @Test
    public void testMap() {
        int[] expOne = {9, 64, 4, 81, 225, 100, 49, 16, 36, 25};
        int[] expTwo = {16};
        int[] expThree = {};

        int[] actOne = nonEmptyStream.map(x -> x*x).toArray();
        int[] actTwo = oneElStream.map(x -> x*x).toArray();
        int[] actThree = emptyStream.map(x -> x*x).toArray();

        Assert.assertArrayEquals(expOne, actOne);
        Assert.assertArrayEquals(expTwo, actTwo);
        Assert.assertArrayEquals(expThree, actThree);
    }

    @Test
    public void testFlatMap() {
        int[] expOne = {-4, -3, -9, -8, -3, -2, 8,
                9, 14, 15, 9, 10, 6, 7, 3, 4, 5, 6, 4, 5};
        int[] expTwo = {3, 4};
        int[] expThree = {};

        int[] actOne = nonEmptyStream.
                flatMap(a -> AsIntStream.of(a - 1, a)).toArray();
        int[] actTwo = oneElStream.
                flatMap(a -> AsIntStream.of(a - 1, a)).toArray();
        int[] actThree = emptyStream.
                flatMap(a -> AsIntStream.of(a - 1, a)).toArray();

        Assert.assertArrayEquals(expOne, actOne);
        Assert.assertArrayEquals(expTwo, actTwo);
        Assert.assertArrayEquals(expThree, actThree);
    }

    @Test
    public void testForEach() {
        StringBuilder resStrOne = new StringBuilder();
        StringBuilder resStrTwo = new StringBuilder();
        StringBuilder resStrThree = new StringBuilder();

        nonEmptyStream.forEach(resStrOne::append);
        oneElStream.forEach(resStrTwo::append);
        emptyStream.forEach(resStrThree::append);

        String expOne = "-3-8-2915107465";
        String expTwo = "4";
        String expThree = "";

        Assert.assertEquals(expOne, resStrOne.toString());
        Assert.assertEquals(expTwo, resStrTwo.toString());
        Assert.assertEquals(expThree, resStrThree.toString());

    }
}
