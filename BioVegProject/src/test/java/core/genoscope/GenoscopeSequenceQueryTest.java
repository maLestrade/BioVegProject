package core.genoscope;

import org.junit.BeforeClass;
import org.junit.Test;

import sequence.Sequence;

public class GenoscopeSequenceQueryTest {

	private static GenoscopeSequenceQuery genoscopeSequenceQuery;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		genoscopeSequenceQuery = new GenoscopeSequenceQuery("GSVIVT01019504001");
	}

	@Test
	public void test() {
		Sequence seq = genoscopeSequenceQuery.getSequence();
		System.out.println(seq);
		System.out.println("sequence length: "+seq.getSequenceLenght());
		System.out.println(seq.getSequence().length());
	}

}
