package com.pangpang6.books.pattern.chainms;

public class Client {

	public Client() {

	}

	public PurchaseRequest sendRequst(int Type, int Number, float Price) {
		return new PurchaseRequest(Type, Number, Price);
	}

}
