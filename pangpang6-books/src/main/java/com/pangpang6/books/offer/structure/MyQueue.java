package com.pangpang6.books.offer.structure;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * 基本思路是 s1接收元素  s2弹出元素  当s2为空时，把s1的数据push到s2
 */
public class MyQueue<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    //加入队列
    public void offer(T data) {
        stack1.push(data);
    }

    //从队列中取出元素
    public T poll() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        if (!stack1.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
        return null;
    }
}
