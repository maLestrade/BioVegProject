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

	public void setPrimerCouples(ArrayList<PrimerCouple> primerCouples) {
		this.primerCouples = primerCouples;
	}
	
	
}
