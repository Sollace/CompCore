package com.blazeloader.util.time;

public interface IDate {
	/**
	 * Gets a string formatted representation of the current date.
	 */
	public String getDate();
	
	/**
	 * Gets the number of weeks that have passed since the beginning of the year.
	 */
	public int getWeeks();
	
	/**
	 * Gets the number of days that have passed since the beginning of the year.
	 */
	public int getDays();
	
	/**
	 * Gets the number of months that have passed since the beginning of the year.
	 */
	public int getMonths();
	
	/**
	 * Gets the current year.
	 */
	public int getYears();
	
	/**
	 * Gets the current Day of the month.
	 */
	public int getDay();
	
	/**
	 * Gets the current day.
	 */
	public Day getDayOfWeek();
	
	/**
	 * Gets the current Month.
	 */
	public Month getMonth();
	
	/**
	 * Returns true if the current year is a leap year.
	 */
	public boolean isLeap();
	
	/**
	 * Gets the decadence suffix for the current date.
	 * <p>
	 * Eg. BC, AD, etc
	 * </p>
	 */
	public String getEra();
}
