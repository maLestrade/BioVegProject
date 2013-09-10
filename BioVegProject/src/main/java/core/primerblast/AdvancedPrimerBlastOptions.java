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
	 * maxGCEnd : Integer
	 * Primer max end GC
	 */
	private Integer maxGCEnd;
	/**
	 * selfAny : Integer
	 * self complementarity any
	 */
	private Double selfAny;
	/**
	 * selfAny : Integer
	 * self complementarity end
	 */
	private Double selfEnd;
	/**
	 * pairAny : Integer
	 * max pair complementarity any
	 */
	private Double pairAny;
	/**
	 * pairEnd : Integer
	 * max pair complementarity end
	 */
	private Double pairEnd;
	/**
	 * maxHairpin : Double
	 * maximum hairpin melting temp
	 */
	private Double maxHairpin;
	/**
	 * thAlignement : Boolean
	 * thermodynamic alignment, which can be "on" or "off"
	 */
	private Boolean thAlignment;
	// TODO : the setter will take a boolean value and put a string value (on or off)
	
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

	/**
	 * Getter for the max number of g and c at the end of the primer
	 * @return maxGCEnd : Integer
	 */
	public Integer getMaxGCEnd() {
		return maxGCEnd;
	}

	/**
	 * Setter for the max number of g and c at the end of the primer
	 * @param maxGCEnd : Integer
	 */
	public void setMaxGCEnd(Integer maxGCEnd) {
		this.maxGCEnd = maxGCEnd;
	}

	/**
	 * Getter for the self complementarity any
	 * @return selfAny : Double
	 */
	public Double getSelfAny() {
		return selfAny;
	}

	/**
	 * Setter for the self complementarity any
	 * @param selfAny : Double
	 */
	public void setSelfAny(Double selfAny) {
		this.selfAny = selfAny;
	}

	/**
	 * Getter for the self complementarity end
	 * @return selfEnd : Double
	 */
	public Double getSelfEnd() {
		return selfEnd;
	}

	/**
	 * Setter for the self complementarity end
	 * @param selfEnd : Double
	 */
	public void setSelfEnd(Double selfEnd) {
		this.selfEnd = selfEnd;
	}

	/**
	 * Getter for the pair complementarity any
	 * @return pairAny : Double
	 */
	public Double getPairAny() {
		return pairAny;
	}

	/**
	 * Setter for the pair complementarity any
	 * @param pairAny : Double
	 */
	public void setPairAny(Double pairAny) {
		this.pairAny = pairAny;
	}

	/**
	 * Getter for the pair complementarity end
	 * @return pairEnd : Double
	 */
	public Double getPairEnd() {
		return pairEnd;
	}

	/**
	 * Setter for the pair complementarity end
	 * @param pairEnd : Double
	 */
	public void setPairEnd(Double pairEnd) {
		this.pairEnd = pairEnd;
	}

	/**
	 * Getter for the maximum hairpin melting temp
	 * @return maxHairpin : Double
	 */
	public Double getMaxHairpin() {
		return maxHairpin;
	}

	/**
	 * Setter for the maximum hairpin melting temp
	 * @param maxHairpin : Double
	 */
	public void setMaxHairpin(Double maxHairpin) {
		this.maxHairpin = maxHairpin;
	}
	
	/**
	 * Getter for the thermodynamic alignment option
	 * @return thAlignment : Boolean
	 */
	public Boolean getThAlignment() {
		return thAlignment;
	}

	/**
	 * Setter for the thermodynamic alignment option
	 * @param thAlignment : Boolean
	 */
	public void setThAlignment(Boolean thAlignment) {
		this.thAlignment = thAlignment;
	}
	
	
}
