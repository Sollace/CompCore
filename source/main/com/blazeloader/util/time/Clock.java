package com.blazeloader.util.time;

import java.util.GregorianCalendar;

public class Clock implements IPreciseTime, IClock {
	
	private GregorianCalendar calendar = (GregorianCalendar)GregorianCalendar.getInstance();
	
	protected int get(int v) {
		long milli = System.currentTimeMillis();
		if (milli != calendar.getTimeInMillis()) calendar.setTimeInMillis(milli);
		return calendar.get(v);
	}
	
	public String getDate() {
		return padded(getDays()) + "-" + padded(calendar.get(GregorianCalendar.MONTH)+1) + "-" + padded(calendar.get(GregorianCalendar.YEAR));
	}
	
	public String getTime() {
		return padded(getHours()) + ":" + padded(calendar.get(GregorianCalendar.MINUTE)) + ":" + padded(calendar.get(GregorianCalendar.SECOND));
	}
	
	protected String padded(int v) {
		return v < 10 ? "0" + v : v + "";
	}
	
	public String getTimePrecise() {
		return getTime() + ":" + padded(calendar.get(GregorianCalendar.MILLISECOND));
	}
	
	@Override
	public int getWeeks() {
		return get(GregorianCalendar.WEEK_OF_YEAR);
	}

	@Override
	public int getDays() {
		return get(GregorianCalendar.DAY_OF_YEAR);
	}

	@Override
	public int getMonths() {
		return get(GregorianCalendar.MONTH) % 12;
	}

	@Override
	public int getYears() {
		return get(GregorianCalendar.YEAR);
	}

	@Override
	public Day getDay() {
		return Day.values()[(get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7];
	}

	@Override
	public Month getMonth() {
		return Month.values()[getMonths()];
	}

	@Override
	public boolean isLeap() {
		return calendar.isLeapYear(getYears());
	}

	@Override
	public String getEra() {
		return get(GregorianCalendar.ERA) == GregorianCalendar.AD ? "AD" : "BC";
	}

	@Override
	public int getHours() {
		return get(GregorianCalendar.HOUR_OF_DAY);
	}

	@Override
	public int getMinutes() {
		return get(GregorianCalendar.MINUTE);
	}

	@Override
	public int getSeconds() {
		return get(GregorianCalendar.SECOND);
	}

	@Override
	public int getMilliseconds() {
		return get(GregorianCalendar.MILLISECOND);
	}

	@Override
	public long getTotalMilliseconds() {
		return calendar.getTimeInMillis();
	}
	
	public String toString() {
		return getDate() + " " + getTimePrecise();
	}
}
