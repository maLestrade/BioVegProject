package core.primer;

/**
 * Class for a couple of Primer
 * @author pidupuis, gcornut
 *
 */
public class PrimerCouple {

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
	
	
	
	
}
