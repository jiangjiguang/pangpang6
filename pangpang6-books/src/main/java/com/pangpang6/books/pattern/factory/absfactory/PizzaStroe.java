package com.pangpang6.books.pattern.factory.absfactory;



public class PizzaStroe {
	public static void main(String[] args) {
		
		OrderPizza mOrderPizza;
		mOrderPizza=new	OrderPizza(new LDFactory());
		
	}

	

}
