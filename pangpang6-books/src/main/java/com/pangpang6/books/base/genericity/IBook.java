package com.pangpang6.books.base.genericity;

import java.io.Serializable;

public interface IBook<T extends Serializable> {

    T getBook();
}
