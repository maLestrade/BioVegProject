package core.sequence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SequenceUtilsTest {

	@Test
	public void test() {
		String in = "attccgatcgrykmswbnnvdhnttgtgcg";
		String expected = "taaggctagcyrmkswvnnbhdnaacacgc";
		String actual = SequenceUtils.complement(in);
		
		assertEquals(expected, actual);
	}

}
