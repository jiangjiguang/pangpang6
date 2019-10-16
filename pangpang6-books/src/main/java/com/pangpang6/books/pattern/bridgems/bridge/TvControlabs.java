package com.pangpang6.books.pattern.bridgems.bridge;


import com.pangpang6.books.pattern.bridgems.control.Control;

public abstract class TvControlabs {

	  Control mControl=null;
	public TvControlabs(Control mControl)
	{
		this.mControl=mControl;
	}
	
	public abstract void Onoff();
	public abstract void nextChannel();
	public abstract void preChannel();
	
	
}
