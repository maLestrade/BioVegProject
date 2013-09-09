package core.primer;
import java.util.ArrayList;

public class PrimerSet {
	
	private ArrayList<PrimerCouple> primerCouples;
	
	public PrimerSet() {
		this.primerCouples = new ArrayList<PrimerCouple>();
	}

	public ArrayList<PrimerCouple> getPrimerCouples() {
		return primerCouples;
	}
	
	public void addPrimerCouple(Primer forward, Primer reverse) {
		this.primerCouples.add(new PrimerCouple(forward, reverse));
		
	}
	
	
}
