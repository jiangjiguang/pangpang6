package com.pangpang6.books.pattern.factory.simplefactory;


public class PizzaStroe {
    public static void main(String[] args) {
        SimplePizzaFactory mSimplePizzaFactory;
        OrderPizza mOrderPizza;
        mOrderPizza = new OrderPizza(new SimplePizzaFactory());

    }
}
