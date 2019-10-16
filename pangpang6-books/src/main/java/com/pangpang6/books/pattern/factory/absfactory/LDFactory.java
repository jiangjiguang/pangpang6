package com.pangpang6.books.pattern.factory.absfactory;


import com.pangpang6.books.pattern.factory.pizza.LDCheesePizza;
import com.pangpang6.books.pattern.factory.pizza.LDPepperPizza;
import com.pangpang6.books.pattern.factory.pizza.Pizza;

public class LDFactory implements AbsFactory {

    @Override
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;

    }

}
