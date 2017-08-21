package com.pangpang6.books.algorithm.randomforest;

public class GreaterEqual extends Function {
	@Override
	public boolean apply(float left,float right) {
		return Float.compare(left, right) <=0 ;
		//return Double.compare(left,right)>=0;
	}
	@Override
	public String toString() {
		return ">=";
	}
}
