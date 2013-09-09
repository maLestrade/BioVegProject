package core.primer;

public class Primer {

	private String name;
	private int start;
	private int end;
	private String hybridSite;
	private String primerSeq;
	
	
	public Primer(String name, int start, int end, String hybridSite, String primerSeq) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.hybridSite = hybridSite;
		this.primerSeq = primerSeq;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	public String getHybridSite() {
		return hybridSite;
	}


	public void setHybridSite(String hybridSite) {
		this.hybridSite = hybridSite;
	}


	public String getPrimerSeq() {
		return primerSeq;
	}


	public void setPrimerSeq(String primerSeq) {
		this.primerSeq = primerSeq;
	}
	
	
}
