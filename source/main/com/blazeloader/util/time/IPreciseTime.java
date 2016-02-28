package com.blazeloader.util.time;

public interface IPreciseTime extends ITime {
	/**
	 * Gets a string formatted represenation of the current time.
	 */
	public String getTimePrecise();
	
	/**
	 * Gets the number of milliseconds since the last full second.
	 */
	public int getMilliseconds();
	
	/**
	 * Gets the total milliseconds since the beginning of time. Used for comparing clocks.
	 */
	public long getTotalMilliseconds();
	
	/**
	 * Gets the total number of milliseconds difference between this time and the other passed in.
	 */
	public default long getMillisecondsDifference(IPreciseTime other)  {
		return getTotalMilliseconds() - other.getTotalMilliseconds();
	}
}
