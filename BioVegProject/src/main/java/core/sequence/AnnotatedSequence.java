package core.sequence;

import java.util.ArrayList;

/**
 * Class which contains a annotated sequence which is a list of SequencePart
 * @author pidupuis, gcornut
 *
 */
public class AnnotatedSequence {
	
	/**
	 * serialVersionUID : long
	 * Variable for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * sequenceParts : ArrayList<SequencePart>
	 * List of sequence parts
	 */
	private final ArrayList<SequencePart> sequenceParts;
	
	/**
	 * Default constructor
	 */
	public AnnotatedSequence() {
		sequenceParts = new ArrayList<SequencePart>(); // Instantiate the list
	}
	
	/**
	 * Method which add a sequence part to the list
	 * @param type : SequencePartType : type which can be 3UTR, 5UTR, EXON or INTRON
	 * @param sequence : String : sequence
	 * @param start : int : position of the first nucleotide
	 * @param end : int : position of the last nucleotide
	 */
	public void add(SequencePartType type, String sequence, int start, int end) {
		sequenceParts.add(new SequencePart(type, sequence, start, end));
	}
	
	/**
	 * Getter which return the concatenated sequence
	 * @return String : concatenated sequence
	 */
	public String getSequence() {
		String out = "";
		
		for(SequencePart sp: sequenceParts) {
			out += sp.getSequence();
		}
		
		return out;
	}
	
	@Override
	/**
	 * Method which friendly displays the annotated sequence
	 */
	public String toString() {
		String out = "";
		
		for(SequencePart sp: sequenceParts) {
			if(sp.getType() == SequencePartType.UTR5 || sp.getType() == SequencePartType.UTR3)
				out += sp.getStart() + ":" + sp.getEnd() + " [" +  sp.getSequence() + "]\n\n";
			else if(sp.getType() == SequencePartType.EXON)
				out += sp.getStart() + ":" + sp.getEnd() + " {" + sp.getSequence() + "}\n\n";
			else
				out += sp.getStart() + ":" + sp.getEnd() + " (" + sp.getSequence() + ")\n\n";
		}
		
		return out;
	}
	
	/**
	 * Method which return the sequence size
	 * @return int : sequence size
	 */
	public int getSequenceLenght() {
		return sequenceParts.get(sequenceParts.size()-1).getEnd();
	}
}
