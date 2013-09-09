package core.primerblast;

import apollo.analysis.RemotePrimerBlastNCBI.PrimerBlastOptions;

/**
 * Class to perform primerblast options
 * Inherits from apollo program
 * @author pidupuis, gcornut
 *
 */
public class AdvancedPrimerBlastOptions extends PrimerBlastOptions {
	
	/**
	 * primerSizeMin : Integer
	 * Minimum size for the primer
	 */
	private Integer primerSizeMin;
	/**
	 * primerSizeMax : Integer
	 * Maximum size for the primer
	 */
	private Integer primerSizeMax;
	/**
	 * primerSizeOpt : Integer
	 * Optimal size for the primer
	 */
	private Integer primerSizeOpt;
	/**
	 * GCMin : Integer
	 * Minimum GC%
	 */
	private Integer GCMin;
	/**
	 * GCMax : Integer
	 * Maximum GC%
	 */
	private Integer GCMax;
	
	/**
	 * Getter for the minimum size
	 * @return primerSizeMin : Integer
	 */
	public Integer getPrimerSizeMin() {
		return primerSizeMin;
	}
	
	/**
	 * Setter for the minimum size
	 * @param primerSizeMin ; Integer
	 */
	public void setPrimerSizeMin(Integer primerSizeMin) {
		this.primerSizeMin = primerSizeMin;
	}
	
	/**
	 * Getter for the maximum size
	 * @return primerSizeMax : Integer
	 */
	public Integer getPrimerSizeMax() {
		return primerSizeMax;
	}
	
	/**
	 * Setter for the maximum size
	 * @param primerSizeMax : Integer
	 */
	public void setPrimerSizeMax(Integer primerSizeMax) {
		this.primerSizeMax = primerSizeMax;
	}
	
	/**
	 * Getter for the optimal size
	 * @return primerSizeOpt : Integer
	 */
	public Integer getPrimerSizeOpt() {
		return primerSizeOpt;
	}
	
	/**
	 * Setter for the optimal size
	 * @param primerSizeOpt : Integer
	 */
	public void setPrimerSizeOpt(Integer primerSizeOpt) {
		this.primerSizeOpt = primerSizeOpt;
	}
	
	/**
	 * Getter for the minimum GC%
	 * @return GCMin : Integer
	 */
	public Integer getGCMin() {
		return GCMin;
	}
	
	/**
	 * Setter for the minimum GC%
	 * @param gCMin : Integer
	 */
	public void setGCMin(Integer gCMin) {
		GCMin = gCMin;
	}
	
	/**
	 * Getter for the maximum GC%
	 * @return GCMax : Integer
	 */
	public Integer getGCMax() {
		return GCMax;
	}
	
	/**
	 * Setter for the maximum GC%
	 * @param gCMax : Integer
	 */
	public void setGCMax(Integer gCMax) {
		GCMax = gCMax;
	}
}
