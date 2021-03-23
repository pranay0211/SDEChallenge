package io.pranay.paytm;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.pranay.paytm.collections.MovingAverage;
import io.pranay.paytm.collections.impl.MovingAverageArrayList;

public class MovingAverageArrayListTest {

	private static final long TEST_SAMPLE_SIZE = 7000000;
	private static final long TEST_ELEMENT_SIZE = 9000000;

	@Test
	@DisplayName("After clearing dataset, return 0 as size of dataset")
	public void clearMovingAverageDatasetSuccessTest() {
		MovingAverage ma = new MovingAverageArrayList();
		ma.add(1L);
		ma.add(2L);
		ma.add(3L);
		ma.getMovingAverage(2L);
		ma.clear();
		Assertions.assertEquals(0, ma.size());
	}

	@Test
	@DisplayName("Error Throw when N is lesser than 0")
	public void movingAverageIllegalArgumentExceptionLessThanZero() {
		MovingAverage ma = new MovingAverageArrayList();
		Assertions.assertThrows(IllegalArgumentException.class, () -> ma.getMovingAverage(-1L));
	}

	@Test
	@DisplayName("Throw error when data list is empty and try to get moving average")
	public void getMovingAverageOfEmptyDatesetThrowExceptionTest() {
		MovingAverage ma = new MovingAverageArrayList();
		Assertions.assertThrows(IllegalArgumentException.class, () -> ma.getMovingAverage(1L));
	}

	@Test
	@DisplayName("Error Throw when N is larger than MovingAvgList size")
	public void movingAverageIllegalArgumentExceptionLargerThanSize() {
		MovingAverage ma = new MovingAverageArrayList();
		ma.add(1l);
		Assertions.assertThrows(IllegalArgumentException.class, () -> ma.getMovingAverage(5L));
	}

	@Test
	@DisplayName("Moving average test success - Long value")
	public void testMovingAverage() {
		MovingAverage ma = new MovingAverageArrayList();
		ma.add(2L);
		ma.add(4L);
		Assertions.assertEquals(3, ma.getMovingAverage(2L).longValue());
		ma.add(8L);
		Assertions.assertEquals(4.0, ma.getMovingAverage(3L).longValue());
		ma.add(5L);
		ma.add(3L);
		ma.add(199L);
		Assertions.assertEquals(43.0, ma.getMovingAverage(5L).longValue());
		
	}

	@Test
	@DisplayName("Moving average double")
	public void testMovingAverageDoubles() {
		MovingAverage ma = new MovingAverageArrayList();
		ma.add(BigDecimal.valueOf(3.14));
		ma.add(BigDecimal.valueOf(9.14));
		ma.add(BigDecimal.valueOf(656.3));
		Assertions.assertEquals(332.72, ma.getMovingAverage(2L).doubleValue());
	}

	@Test
	@DisplayName("Get moving average of whole dataset when N not given")
	public void getWholeDataMovingAverageWithoutGivingNTest() {
		MovingAverage ma = new MovingAverageArrayList();
		ma.add(BigDecimal.valueOf(2.12));
		ma.add(BigDecimal.valueOf(101.98));
		ma.add(BigDecimal.valueOf(9028.21));
		ma.add(BigDecimal.valueOf(4.11));
		ma.add(BigDecimal.valueOf(1.98));
		ma.add(BigDecimal.valueOf(28.21));
		ma.add(BigDecimal.valueOf(2.2));
		ma.add(BigDecimal.valueOf(189.98));
		ma.add(BigDecimal.valueOf(98.21));
		
		// get moving average of last N = 2 elements
		Assertions.assertEquals(144.095, ma.getMovingAverage(2L).doubleValue());
		
		// get moving average of all element without N
		Assertions.assertEquals(1050, ma.getMovingAverage().intValue());
	}
	
	@Test
	@DisplayName("Scalability test")
	public void testScalledMovingAverage() {
		MovingAverage ma = new MovingAverageArrayList();
		double value = 3.14;
		long start_time = System.currentTimeMillis();
		for(int i=0;i<TEST_ELEMENT_SIZE;i++) {
			ma.add(BigDecimal.valueOf(i * value));
		}
		Assertions.assertEquals(1.726999843E7, ma.getMovingAverage(TEST_SAMPLE_SIZE).doubleValue());
		long end_time = System.currentTimeMillis();
		long repeatDuration = end_time - start_time;
		System.out.println("repeat test took " + repeatDuration/1000 + " seconds due to caching");
		
	}
	
}
