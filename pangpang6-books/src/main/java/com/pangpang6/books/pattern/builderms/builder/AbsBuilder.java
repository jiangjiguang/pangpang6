package com.pangpang6.books.pattern.builderms.builder;


import com.pangpang6.books.pattern.builderms.vacation.Vacation;

public abstract class AbsBuilder {

	public Vacation mVacation;

	public AbsBuilder(String std) {
		mVacation = new Vacation(std);
	}

	public abstract void buildvacation();

	public abstract void buildDay(int i);

	public abstract void addHotel(String hotel);

	public abstract void addTicket(String ticket);

	public abstract void addEvent(String tvent);

	public Vacation getVacation() {

		return mVacation;

	}

}
