package com.pangpang6.books.pattern.factory.absfactory;


import com.pangpang6.books.pattern.factory.pizza.NYCheesePizza;
import com.pangpang6.books.pattern.factory.pizza.NYPepperPizza;
import com.pangpang6.books.pattern.factory.pizza.Pizza;

public class NYFactory implements AbsFactory {


    @Override
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new NYCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new NYPepperPizza();
        }
        return pizza;

    }

}
