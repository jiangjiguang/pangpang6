package com.pangpang6.hadoop.hadoop2.tq;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiangjiguang on 2018/3/25.
 */
public class TQSort extends WritableComparator {

    private static final Logger logger = LoggerFactory.getLogger(TQSort.class);


    public TQSort() {
        super(Weather.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Weather w1 = (Weather) a;
        Weather w2 = (Weather) b;

        logger.info("---- compare: a=%s, b=%s", w1.toString(), w2.toString());
        //return w1.compareTo(w2);

        int c1 = w1.getYear() - w2.getYear();
        if (c1 != 0) {
            return c1;
        }

        int c2 = w1.getMonth() - w2.getMonth();
        if (c2 != 0) {
            return c2;
        }
        return -(w1.getWd() - w2.getWd());

    }
}
