package core.primer;
import java.util.ArrayList;

/**
 * Class for a list of primer couples
 * @author pidupuis, gcornut
 *
 */
public class PrimerSet {
	
	/**
	 * primerCouples : ArrayList<PrimerCouple>
	 * List of primer couples
	 */
	private ArrayList<PrimerCouple> primerCouples;
	
	/**
	 * Default constructor
	 */
	public PrimerSet() {
		this.primerCouples = new ArrayList<PrimerCouple>(); // Instantiate the list
	}

	/**
	 * Getter for the primer couples
	 * @return primerCouples : ArrayList<PrimerCouple>
	 */
	public ArrayList<PrimerCouple> getPrimerCouples() {
		return primerCouples;
	}
	
	/**
	 * Create a primer couple from two primers and add it to the list
	 * @param forward : Primer
	 * @param reverse : Primer
	 */
	public void addPrimerCouple(Primer forward, Primer reverse) {
		this.primerCouples.add(new PrimerCouple(forward, reverse));
	}
	
	
}
