package com.pangpang6.books.pattern.factory.absfactory;


import com.pangpang6.books.pattern.factory.pizza.Pizza;

/**
 * 就是将工厂抽象出来
 */
public interface AbsFactory {
    Pizza CreatePizza(String ordertype);
}
