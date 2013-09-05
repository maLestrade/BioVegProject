package core.primerblast;

import apollo.analysis.RemotePrimerBlastNCBI.PrimerBlastOptions;

public class AdvancedPrimerBlastOptions extends PrimerBlastOptions {
	
	private Integer primerSizeMin;
	private Integer primerSizeMax;
	private Integer primerSizeOpt;
	private Integer GCMin;
	private Integer GCMax;
	
	public Integer getPrimerSizeMin() {
		return primerSizeMin;
	}
	public void setPrimerSizeMin(Integer primerSizeMin) {
		this.primerSizeMin = primerSizeMin;
	}
	public Integer getPrimerSizeMax() {
		return primerSizeMax;
	}
	public void setPrimerSizeMax(Integer primerSizeMax) {
		this.primerSizeMax = primerSizeMax;
	}
	public Integer getPrimerSizeOpt() {
		return primerSizeOpt;
	}
	public void setPrimerSizeOpt(Integer primerSizeOpt) {
		this.primerSizeOpt = primerSizeOpt;
	}
	public Integer getGCMin() {
		return GCMin;
	}
	public void setGCMin(Integer gCMin) {
		GCMin = gCMin;
	}
	public Integer getGCMax() {
		return GCMax;
	}
	public void setGCMax(Integer gCMax) {
		GCMax = gCMax;
	}
}
