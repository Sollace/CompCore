package com.blazeloader.util.time;

public enum Month {
	JANUARY("January"),
	FEBRUARY("February"),
	MARCH("March"),
	APRIL("April"),
	MAY("May"),
	JUNE("JUNE"),
	JULY("JULY"),
	AUGUST("August"),
	SEPTEMBER("September"),
	OCTOBER("October"),
	NOVEMBER("November"),
	DECEMBER("December");
	
	private final String name;
	
	Month(String s) {
		name = s;
	}
	
	public String displayName() {
		return name;
	}
}
