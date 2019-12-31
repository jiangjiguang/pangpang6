package com.pangpang6.books.miansh;

import java.util.LinkedList;
import java.util.Queue;

public class Test1 {
    private static Queue<String> queue1 = new LinkedList<>();
    private static Queue<String> queue2 = new LinkedList<>();

    public static void main(String[] args) {
        String a="a";
        String b="b";
        String c="c";
        push(a);
        push(b);
        String m=pop();
        System.out.println(m);
        push(c);
        System.out.println(pop());
    }


    public static void push(String a){
        queue1.add(a);
    }

    public static String pop(){
        while (true){
            String S=queue1.remove();
            if (queue1.isEmpty()){
                while (queue2.isEmpty()){
                    queue1.add(queue2.remove());
                }
                return S;
            }else {
                queue2.add(S);
            }
        }
    }

}
