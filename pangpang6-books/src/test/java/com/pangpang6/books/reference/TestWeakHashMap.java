package com.pangpang6.books.reference;


import org.junit.Test;

import java.util.*;

/**
 * -Xmx5M
 *
 * @author Administrator
 */
public class TestWeakHashMap {

    Map map;

    @Test
    public void testWeakHashMap() {
        map = new WeakHashMap();
        List l = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            map.put(ii, new byte[i]);
            System.out.println(map.get(0));
        }
    }

    @Test
    public void testWeakHashMap2() {
        map = new WeakHashMap();
        List l = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            l.add(ii);
            map.put(ii, new byte[i]);
        }
    }

    @Test
    public void testHashMap() {
        map = new HashMap();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            map.put(ii, new byte[i]);
        }
    }
}
