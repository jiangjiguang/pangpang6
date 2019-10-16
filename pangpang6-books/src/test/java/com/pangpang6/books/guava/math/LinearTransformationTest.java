package com.pangpang6.books.guava.math;

import com.google.common.math.LinearTransformation;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/13.
 */
public class LinearTransformationTest {
    @Test
    public void buildTest(){
        LinearTransformation l1 = LinearTransformation.mapping(2,3).withSlope(2);
        System.out.println(l1.slope());

        System.out.println(l1.transform(3));

        System.out.println(l1.isHorizontal());
        System.out.println(l1.isVertical());
        LinearTransformation l2 = l1.inverse().inverse();
        System.out.println(l2.transform(3));
    }
}
