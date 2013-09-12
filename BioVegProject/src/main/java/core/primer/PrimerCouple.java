package core.primer;

import java.util.Comparator;

/**
 * Class for a couple of Primer
 * @author pidupuis, gcornut
 *
 */
public class PrimerCouple implements Comparable<PrimerCouple> {

	/**
	 * forward : Primer
	 * Primer for the forward strand
	 */
	private Primer forward;
	/**
	 * reverse : Primer
	 * Primer for the reverse strand
	 */
	private Primer reverse;
	/**
	 * score : Double
	 * Subjective score which is good when near 0. It is calculating as following :
	 * |Tm1-Tm2| + |µ(Tm1-Tm2)-60| + |GC1-GC2| + |µ(GC1-GC2)-50| + self1 + self3'1 + self2 + self3'2
	 */
	private Double score;
	
	/**
	 * Constructor using fields
	 * @param forward : Primer
	 * @param reverse : Primer
	 */
	public PrimerCouple(Primer forward, Primer reverse) {
		this.forward = forward;
		this.reverse = reverse;
	}

	/**
	 * Getter for the forward primer
	 * @return forward : Primer
	 */
	public Primer getForward() {
		return forward;
	}

	/**
	 * Setter for the forward primer
	 * @param forward : Primer
	 */
	public void setForward(Primer forward) {
		this.forward = forward;
	}

	/**
	 * Getter for the reverse primer
	 * @return reverse : Primer
	 */
	public Primer getReverse() {
		return reverse;
	}

	/**
	 * Setter for the reverse primer
	 * @param reverse : Primer
	 */
	public void setReverse(Primer reverse) {
		this.reverse = reverse;
	}

	/**
	 * Getter for the subjective score
	 * @return score : Double
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * Setter for the subjective score
	 * @param score : Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public int compareTo(PrimerCouple o) {
		// Ascending order
		return (int) (this.score*100 - o.getScore()*100);
	}
	
	
	
	
}
