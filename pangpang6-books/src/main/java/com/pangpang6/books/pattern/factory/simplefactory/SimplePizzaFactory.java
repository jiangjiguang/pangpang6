package com.pangpang6.books.pattern.factory.simplefactory;


import com.pangpang6.books.pattern.factory.pizza.CheesePizza;
import com.pangpang6.books.pattern.factory.pizza.GreekPizza;
import com.pangpang6.books.pattern.factory.pizza.PepperPizza;
import com.pangpang6.books.pattern.factory.pizza.Pizza;

/**
 * 就是把变化的部分（创建对象部分）抽象出来
 */
public class SimplePizzaFactory {

    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (ordertype.equals("greek")) {
            pizza = new GreekPizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new PepperPizza();
        }
        return pizza;

    }

}
