package core.primer;

public class PrimerCouple {

	private Primer forward;
	private Primer reverse;
	
	
	public PrimerCouple(Primer forward, Primer reverse) {
		this.forward = forward;
		this.reverse = reverse;
	}


	public Primer getForward() {
		return forward;
	}


	public void setForward(Primer forward) {
		this.forward = forward;
	}


	public Primer getReverse() {
		return reverse;
	}


	public void setReverse(Primer reverse) {
		this.reverse = reverse;
	}
	
	
	
	
}
