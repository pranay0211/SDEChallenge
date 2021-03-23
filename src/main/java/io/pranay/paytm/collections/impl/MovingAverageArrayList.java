package io.pranay.paytm.collections.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import io.pranay.paytm.collections.MovingAverage;

/**
 * 
 * The <code>MovingAverageArrayList</code> interface is for tracking the moving
 * average of the last N elements added. Also, this interface supports adding
 * elements to the dataset and provides read-access to the elements. Clear the
 * list of elements and other related functionalities to operate on list of
 * dataset.
 * 
 * 
 * @author Pranay Patadiya
 *
 */
public class MovingAverageArrayList implements MovingAverage {

	private List<BigDecimal> dataset;
	private Long prev_N;
	private BigDecimal m_avg;

	/***
	 * Setting up <code>lastN</code>, denotes total last elements in dataset, to be
	 * consider to calculate moving average
	 * 
	 * @param sizeOfMovingAvgList
	 */
	public MovingAverageArrayList() {
		this.m_avg = BigDecimal.ZERO;
		this.prev_N = 0l;
		dataset = new ArrayList<BigDecimal>();
	}

	/***
	 * Clear the data set
	 */
	@Override
	public void clear() {
		this.prev_N = 0l;
		this.m_avg = BigDecimal.ZERO;
		this.dataset.clear();
	}

	/***
	 * Check if the dataset is empty
	 * 
	 * @return Boolean (TRUE/FALSE)
	 */
	@Override
	public boolean isEmpty() {
		return this.dataset.size() == 0;
	}

	/***
	 * Append the element to dataset to the end of list
	 */
	@Override
	public void add(BigDecimal element) {
		this.dataset.add(element);
		m_avg = BigDecimal.ZERO;
	}

	/***
	 * Append the element to dataset to the end of list
	 */
	@Override
	public void add(Long element) {
		this.dataset.add(BigDecimal.valueOf(element));
		this.m_avg = BigDecimal.ZERO;
		this.prev_N = 0l;
	}

	/***
	 * Return the
	 */
	@Override
	public BigDecimal get(int index) {
		return this.dataset.get(index);
	}

	@Override
	public int size() {
		return this.dataset.size();
	}

	@Override
	public synchronized BigDecimal getMovingAverage(Long lastN) {

		// return cached value
		if (!this.m_avg.equals(BigDecimal.ZERO) && lastN.equals(prev_N)) {
			return m_avg;
		}

		// Throw IllegalArgumentException if given last element number is negative or
		// zero
		if (lastN <= 0) {
			throw new IllegalArgumentException("Given number N must be : 0 <= N < sizeOfList");
		}

		// Throw IllegalArgumentException if given last element number is higher than
		// dataset size
		if (lastN > dataset.size()) {
			throw new IllegalArgumentException("Given number N must be less than total number of data");
		}

		this.m_avg = calculateMovingAverage(lastN);
		this.prev_N = lastN;

		return this.m_avg;
	}

	/***
	 * Helper function to calculate the moving average
	 * 
	 * @return BigDecimal - MovingAverage
	 */
	private BigDecimal calculateMovingAverage(Long lastN) {

		int datasetIndex = (int) (dataset.size() - lastN);

		long cnt = 0;
		BigDecimal cigma = BigDecimal.ZERO;

		while (datasetIndex < dataset.size() && cnt <= lastN) {
			cigma = cigma.add(dataset.get(datasetIndex));
			cnt++;
			datasetIndex++;
		}

		return cigma.divide(BigDecimal.valueOf(lastN), MathContext.DECIMAL128);
	}

	/****
	 * Return the moving average of whole list of dataset
	 * @return {@link BigDecimal}
	 */
	@Override
	public synchronized BigDecimal getMovingAverage() {

		if (this.dataset.size() <= 0) {
			return BigDecimal.ZERO;
		}

		return getMovingAverage(Long.valueOf(this.dataset.size()));
	}

}
