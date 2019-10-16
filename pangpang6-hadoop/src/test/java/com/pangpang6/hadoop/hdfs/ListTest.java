package com.pangpang6.hadoop.hdfs;

import com.pangpang6.utils.MyJSONMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjiguang on 2018/3/28.
 */
public class ListTest {
    @Test
    public void test01() {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(3);

        System.out.println(MyJSONMapper.nonDefaultMapper().toJSONString(list.iterator()));
    }
}
