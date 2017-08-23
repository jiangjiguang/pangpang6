package com.pangpang6.utils.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jiangjg on 2017/5/27.
 */
public class ListConcurrent {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        /*
        int i = 0;
        for(String item : list){
            System.out.println(item);
            if(i == 2){
                list.remove(i);
            }
            i++;
        }
        */

        for(int j=0; j<list.size(); j++){
            System.out.println(j + list.get(j));
            if(j == 2){
                System.out.println(list.get(j));
                list.remove(j);
            }

        }


    }
}
