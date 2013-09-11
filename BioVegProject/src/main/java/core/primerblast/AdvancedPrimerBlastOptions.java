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
	 * GCMin : Double
	 * Minimum GC%
	 */
	private Double GCMin;
	/**
	 * GCMax : Double
	 * Maximum GC%
	 */
	private Double GCMax;
	/**
	 * maxGCEnd : Integer
	 * Primer max end GC
	 */
	private Integer maxGCEnd;
	/**
	 * thSelfAny : Integer
	 * max th for self complementarity any
	 */
	private Double thSelfAny;
	/**
	 * thSelfAny : Integer
	 * max th for self complementarity end
	 */
	private Double thSelfEnd;
	/**
	 * thPairAny : Integer
	 * max th for pair complementarity any
	 */
	private Double thPairAny;
	/**
	 * thPairEnd : Integer
	 * max th for pair complementarity end
	 */
	private Double thPairEnd;
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
	public Double getGCMin() {
		return GCMin;
	}
	
	/**
	 * Setter for the minimum GC%
	 * @param gCMin : Integer
	 */
	public void setGCMin(Double gCMin) {
		GCMin = gCMin;
	}
	
	/**
	 * Getter for the maximum GC%
	 * @return GCMax : Integer
	 */
	public Double getGCMax() {
		return GCMax;
	}
	
	/**
	 * Setter for the maximum GC%
	 * @param gCMax : Integer
	 */
	public void setGCMax(Double gCMax) {
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
	 * @return thSelfAny : Double
	 */
	public Double getThSelfAny() {
		return thSelfAny;
	}

	/**
	 * Setter for the self complementarity any
	 * @param thSelfAny : Double
	 */
	public void setThSelfAny(Double selfAny) {
		this.thSelfAny = selfAny;
	}

	/**
	 * Getter for the self complementarity end
	 * @return thSelfEnd : Double
	 */
	public Double getThSelfEnd() {
		return thSelfEnd;
	}

	/**
	 * Setter for the self complementarity end
	 * @param thSelfEnd : Double
	 */
	public void setThSelfEnd(Double selfEnd) {
		this.thSelfEnd = selfEnd;
	}

	/**
	 * Getter for the pair complementarity any
	 * @return thPairAny : Double
	 */
	public Double getThPairAny() {
		return thPairAny;
	}

	/**
	 * Setter for the pair complementarity any
	 * @param thPairAny : Double
	 */
	public void setThPairAny(Double pairAny) {
		this.thPairAny = pairAny;
	}

	/**
	 * Getter for the pair complementarity end
	 * @return thPairEnd : Double
	 */
	public Double getThPairEnd() {
		return thPairEnd;
	}

	/**
	 * Setter for the pair complementarity end
	 * @param thPairEnd : Double
	 */
	public void setThPairEnd(Double pairEnd) {
		this.thPairEnd = pairEnd;
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
