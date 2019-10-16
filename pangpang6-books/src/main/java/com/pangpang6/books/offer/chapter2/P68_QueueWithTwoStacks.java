package com.pangpang6.books.offer.chapter2;

import com.pangpang6.books.offer.structure.MyQueue;

public class P68_QueueWithTwoStacks {

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();

        System.out.println(myQueue.poll());
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        myQueue.offer(4);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
    }
}
