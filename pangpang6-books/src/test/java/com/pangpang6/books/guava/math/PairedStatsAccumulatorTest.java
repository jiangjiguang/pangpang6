package com.pangpang6.books.guava.math;

import com.google.common.math.LinearTransformation;
import com.google.common.math.PairedStatsAccumulator;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/13.
 */
public class PairedStatsAccumulatorTest {

    @Test
    public void leastSquaresFitTest(){
        PairedStatsAccumulator p1 = new PairedStatsAccumulator();
        p1.add(2,4);
        p1.add(3,5);
        p1.add(3,6);
        p1.add(3,7);


        System.out.println(p1.count());
        System.out.println(p1.xStats());
        System.out.println(p1.yStats());

        LinearTransformation l1 = p1.leastSquaresFit();
        System.out.println(l1.transform(3));

        System.out.println(p1.pearsonsCorrelationCoefficient());

        System.out.println(p1.populationCovariance());

        System.out.println(p1.sampleCovariance());

    }
}
