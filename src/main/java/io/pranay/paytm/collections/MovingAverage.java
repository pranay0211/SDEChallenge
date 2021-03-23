package io.pranay.paytm.collections;

import java.math.BigDecimal;

/**
 * 
 * The <code>MovingAverage</code> interface is for tracking the moving average
 * of the last N elements added. Also, this interface supports adding elements
 * to the dataset and provides read-access to the elements. Clear the list of
 * elements and other related functionalities to operate on list of dataset.
 * 
 * 
 * @author Pranay Patadiya
 *
 */
public interface MovingAverage {

	/***
	 * Clears the list
	 */
	public void clear();

	/**
	 * Returns {@code true} if this list contains no elements.
	 *
	 * @return {@code true} if this list contains no elements
	 */
	boolean isEmpty();

	/**
	 * Appends the specified element to the end of the list
	 * 
	 * @param value element to be appended to the list
	 */
	public void add(BigDecimal value);

	/**
	 * Appends the specified element to the end of the list
	 * 
	 * @param value element to be appended to the list
	 */
	public void add(Long value);

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public BigDecimal get(int index);

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	public int size();

	/**
	 * Calculate and return the moving average of given last N element from list of
	 * dataset
	 * 
	 * @return {@link BigDecimal} - Moving Average
	 */
	public BigDecimal getMovingAverage(Long lastN);

	/***
	 * Calculate and return the moving average where number of Last element N is not
	 * given. In this case return the moving average of whole dataset.
	 * 
	 * @return {@link BigDecimal}
	 */
	public BigDecimal getMovingAverage();

}
