package core.primer;

/**
 * Class for one primer
 * @author pidupuis, gcornut
 *
 */
public class Primer {

	/**
	 * name : String
	 * name of the primer, assigned by primerblast
	 */
	private String name;
	/**
	 * start : int
	 * position of the first nucleotide of the primer in the sequence
	 */
	private int start;
	/**
	 * end : int
	 * position of the last nucleotide of the primer in the sequence
	 */
	private int end;
	/**
	 * hybridSite : String
	 * sequence of the hybridation site (in 5'>3')
	 */
	private String hybridSite;
	
	/**
	 * Constructor using fields
	 * @param name : String : name of the primer, assigned by primerblast
	 * @param start : String : position of the first nucleotide of the primer in the sequence
	 * @param end : String : position of the last nucleotide of the primer in the sequence
	 * @param hybridSite : String : sequence of the hybridation site (in 5'>3')
	 */
	public Primer(String name, int start, int end, String hybridSite) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.hybridSite = hybridSite;
	}

	/**
	 * Getter which return the primer name
	 * @return name : String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the primer name
	 * @param name : String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter which return the position of the first nucleotide
	 * @return start : int
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Setter for the position of the first nucleotide
	 * @param start : int
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Getter which return the position of the last nucleotide
	 * @return end : int
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Setter for the position of the last nucleotide
	 * @param end : int
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Getter which return the hybridation site
	 * @return hybridSite : String
	 */
	public String getHybridSite() {
		return hybridSite;
	}

	/**
	 * Setter for the hybridation site
	 * @param hybridSite : String
	 */
	public void setHybridSite(String hybridSite) {
		this.hybridSite = hybridSite;
	}
	
}
