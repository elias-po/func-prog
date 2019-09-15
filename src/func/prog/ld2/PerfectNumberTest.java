package func.prog.ld2;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Assert;
import org.junit.Test;

public class PerfectNumberTest {

	@Test
	public void test6Perfect() {
		Assert.assertEquals(PerfectNumber.STATE.PERFECT, PerfectNumber.process(6));
	}

	@Test
	public void test8Deficient() {
		Assert.assertEquals(PerfectNumber.STATE.DEFICIENT, PerfectNumber.process(8));
	}

	@Test
	public void test20Abundant() {
		Assert.assertEquals(PerfectNumber.STATE.ABUNDANT, PerfectNumber.process(20));
	}

	@Test
	public void test16DeficientWithIntSqrt() {
		Assert.assertEquals(PerfectNumber.STATE.DEFICIENT, PerfectNumber.process(16));
	}

	@Test
	public void test1Deficient() {
		Assert.assertEquals(PerfectNumber.STATE.DEFICIENT, PerfectNumber.process(1));
	}

	@Test
	public void testDivisors1() {
		Object[] expected = new Integer[] { 1 };
		int n = 1;
		assertArrayEquals(expected, PerfectNumber.divisors(n).toArray());
	}

	@Test
	public void testDivisors6() {
		Object[] expected = new Integer[] { 1, 2, 3, 6 };
		int n = 6;
		assertArrayEquals(expected, PerfectNumber.divisors(n).toArray());
	}

	@Test
	public void testDivisors8() {
		Object[] expected = new Integer[] { 1, 2, 4, 8 };
		int n = 8;
		assertArrayEquals(expected, PerfectNumber.divisors(n).toArray());
	}
}
