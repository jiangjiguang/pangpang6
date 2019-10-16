package com.pangpang6.books.guava.base;

import com.google.common.base.Throwables;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

import java.util.List;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class ThrowablesTest {

    @Test
    public void getCausalChainTest(){
        try{
            int a = 0;
            int c = 4/a;
        }catch (Exception ex){
            System.out.println(ExceptionUtils.getFullStackTrace(ex));
            List<Throwable> list = Throwables.getCausalChain(ex);
            System.out.println("------------");
            System.out.println(list);
            System.out.println(Throwables.getRootCause(ex));
            System.out.println(Throwables.getStackTraceAsString(ex));
        }
    }
}
