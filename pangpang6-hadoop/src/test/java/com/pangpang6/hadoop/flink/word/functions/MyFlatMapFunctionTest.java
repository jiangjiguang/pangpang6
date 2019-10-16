package com.pangpang6.hadoop.flink.word.functions;

import com.google.common.collect.Lists;
import org.apache.flink.api.common.functions.util.ListCollector;
import org.junit.Test;

import java.util.List;

public class MyFlatMapFunctionTest {

    @Test
    public void test01() throws Exception {
        MyFlatMapFunction myFlatMapFunction = new MyFlatMapFunction();

        List<String> list = Lists.newArrayList();
        ListCollector<String> listCollector = new ListCollector(list);

        myFlatMapFunction.flatMap("a a a", listCollector);

        System.out.println(list);
    }
}
