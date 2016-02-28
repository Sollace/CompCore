package com.blazeloader.util.time;

public interface ITime {
	/**
	 * Gets a string formatted represenation of the current time.
	 */
	public String getTime();
	
	/**
	 * Gets the hour of day.
	 */
	public int getHours();
	
	/**
	 * Gets the number of minutes since the last whole hour.
	 */
	public int getMinutes();
	
	/**
	 * Gets the number of seconds since the last whole minute.
	 */
	public int getSeconds();
}
