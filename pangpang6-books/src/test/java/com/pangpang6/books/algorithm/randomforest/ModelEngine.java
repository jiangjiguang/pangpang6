package com.pangpang6.books.algorithm.randomforest;

public interface ModelEngine<T> {
	T execute(Parameters parameters);
}
