package com.pangpang6.books.guava.base;

import com.google.common.base.MoreObjects;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class MoreObjectsTest {
    @Test
    public void toStringHelperTest(){
        Test01 test01 = new Test01();
        test01.setA1("jiangjiguang");
        System.out.println(test01);
    }

    public class Test01{
        private String a1;
        private String a2;

        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public String getA2() {
            return a2;
        }

        public void setA2(String a2) {
            this.a2 = a2;
        }

        @Override
        public String toString() {
            /*
            return "Test01{" +
                    "a1='" + a1 + '\'' +
                    ", a2='" + a2 + '\'' +
                    '}';
                    */

            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("a1", a1)
                    .add("a2", a2)
                    .toString();//Player{name=Underwood}
        }
    }
}
