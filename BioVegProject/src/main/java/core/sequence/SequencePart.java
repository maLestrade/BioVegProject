package core.sequence;

/**
 * Class which contains information of a sequence part
 * @author pidupuis, gcornut
 *
 */
public class SequencePart {
	
	/**
	 * type : SequencePartType
	 * Sequence type which can be 3UTR, 5UTR, EXON or INTRON
	 */
	private final SequencePartType type;
	/**
	 * sequence : String
	 */
	private final String sequence;
	/**
	 * start : int
	 * position of the first nucleotide
	 */
	private final int start;
	/**
	 * end : int
	 * position of the last nucleotide
	 */
	private final int end;
	
	/**
	 * Constructor using fields
	 * @param type : SequencePartType : type which can be 3UTR, 5UTR, EXON or INTRON
	 * @param sequence : String : sequence
	 * @param start : int : position of the first nucleotide
	 * @param end : int : position of the last nucleotide
	 */
	public SequencePart(SequencePartType type, String sequence, int start, int end) {
		this.type = type;
		this.sequence = sequence.toLowerCase().replace("\n", "");
		this.start = start;
		this.end = end;
	}

	/**
	 * Getter for the sequence part type
	 * @return type : SequencePartType
	 */
	public SequencePartType getType() {
		return type;
	}

	/**
	 * Getter for the sequence
	 * @return sequence : String
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * Getter for the position of the first nucleotide
	 * @return start : int
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Getter for the position of the last nucleotide
	 * @return end : int
	 */
	public int getEnd() {
		return end;
	}
	
	/**
	 * Return true if the position is in the SequencePart
	 * @param i
	 * @return
	 */
	public boolean isInSequencePart(int i) {
		return (i >= this.start && i <= this.end);
	}
}
