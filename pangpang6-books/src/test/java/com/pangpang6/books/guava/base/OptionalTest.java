package com.pangpang6.books.guava.base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class OptionalTest {

    @Test
    public void absentTest() {
        Optional<String> op = Optional.absent();
        System.out.println(op.isPresent());
    }

    @Test
    public void equalsTest() {
        Test01 test01 = new Test01();
        test01.setA1("a1");
        test01.setA2("a2");

        Test01 test02 = new Test01();
        test02.setA1("a1");
        test02.setA2("a2");

        Optional<Test01> optional = Optional.of(test01);
        System.out.println(optional.equals(test02));

        System.out.println(optional.equals(optional));

    }


    public class Test01 {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Test01)) return false;

            Test01 test01 = (Test01) o;

            if (a1 != null ? !a1.equals(test01.a1) : test01.a1 != null) return false;
            return a2 != null ? a2.equals(test01.a2) : test01.a2 == null;
        }

        @Override
        public int hashCode() {
            int result = a1 != null ? a1.hashCode() : 0;
            result = 31 * result + (a2 != null ? a2.hashCode() : 0);
            return result;
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
