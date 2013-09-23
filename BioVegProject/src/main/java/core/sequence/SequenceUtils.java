package core.sequence;

import org.apache.commons.lang.StringUtils;


public class SequenceUtils {
	/**
	 * Complements a sequence string
	 * <ul>
	 * <li>A = adenine
	 * <li>C = cytosine
	 * <li>G = guanine
	 * <li>T = thymine
	 * <li>R = G A (purine)
	 * <li>Y = T C (pyrimidine)
	 * <li>K = G T (keto)
	 * <li>M = A C (amino)
	 * <li>S = G C (strong bonds)
	 * <li>W = A T (weak bonds)
	 * <li>B = G T C (all but A)
	 * <li>D = G A T (all but C)
	 * <li>H = A C T (all but G)
	 * <li>V = G C A (all but T)
	 * <li>N = A G C T (any)
	 * </ul>
	 * @param sequence
	 * @return
	 */
	public static String complement(String sequence) {
		return StringUtils.replaceChars(
			sequence.trim(), 
			"atcgrykmswbvdhnATCGRYKMSWBVDHN", 
			"tagcyrmkswvbhdnTAGCYRMKSWVBHDN"
		);
	}
}
