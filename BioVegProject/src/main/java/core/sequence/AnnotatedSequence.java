package core.sequence;

import java.util.ArrayList;

/**
 * Class which contains a annotated sequence which is a list of SequencePart
 * 
 * @author pidupuis, gcornut
 * 
 */
public class AnnotatedSequence {

	/**
	 * sequenceParts : ArrayList<SequencePart> List of sequence parts
	 */
	private final ArrayList<SequencePart> sequenceParts;

	/**
	 * Sequence names
	 */
	private String name;

	/**
	 * Default constructor
	 */
	public AnnotatedSequence() {
		this(null);
	}

	/**
	 * Constructs a annotated sequence with a name
	 * @param name
	 */
	public AnnotatedSequence(String name) {
		this.sequenceParts = new ArrayList<SequencePart>(); // Instantiate the list
		this.name = name;
	}

	/**
	 * Method which add a sequence part to the list
	 * 
	 * @param type
	 *            : SequencePartType : type which can be 3UTR, 5UTR, EXON or
	 *            INTRON
	 * @param sequence
	 *            : String : sequence
	 * @param start
	 *            : int : position of the first nucleotide
	 * @param end
	 *            : int : position of the last nucleotide
	 */
	public void add(SequencePartType type, String sequence, int start, int end) {
		sequenceParts.add(new SequencePart(type, sequence, start, end));
	}

	public ArrayList<SequencePart> getSequenceParts() {
		return sequenceParts;
	}

	/**
	 * Getter which return the concatenated sequence
	 * 
	 * @return String : concatenated sequence
	 */
	public String getSequence() {
		StringBuffer out = new StringBuffer();

		for (SequencePart sp : sequenceParts) {
			out.append(sp.getSequence());
		}

		return out.toString();
	}

	@Override
	/**
	 * Method which friendly displays the annotated sequence
	 */
	public String toString() {
		String out = "";

		for (SequencePart sp : sequenceParts) {
			if (sp.getType() == SequencePartType.UTR5
					|| sp.getType() == SequencePartType.UTR3)
				out += sp.getStart() + ":" + sp.getEnd() + " ["
						+ sp.getSequence() + "]\n\n";
			else if (sp.getType() == SequencePartType.EXON)
				out += sp.getStart() + ":" + sp.getEnd() + " {"
						+ sp.getSequence() + "}\n\n";
			else
				out += sp.getStart() + ":" + sp.getEnd() + " ("
						+ sp.getSequence() + ")\n\n";
		}

		return out;
	}

	/**
	 * Method which return the sequence size
	 * 
	 * @return int : sequence size
	 */
	public int getSequenceLenght() {
		return sequenceParts.get(sequenceParts.size() - 1).getEnd();
	}

	/**
	 * Method which return the sequence type at a given location
	 * 
	 * @return SequencePartType
	 */
	public SequencePartType getLocType(int loc) {
		for (SequencePart sq : this.sequenceParts) {
			if (loc >= sq.getStart() && loc <= sq.getEnd()) {
				return sq.getType();
			}
		}
		return null;
	}

	/**
	 * Method which returns a list of sequence part type contained between two positions
	 * @param start : Integer
	 * @param end : Integer
	 * @return parts : ArrayList<SequencePartType>
	 */
	public ArrayList<SequencePartType> getPartTypesFromPositions(Integer start, Integer end) {
		ArrayList<SequencePartType> parts = new ArrayList<SequencePartType>();
		boolean match = false;

		for (SequencePart sq : this.sequenceParts) {
			if (start >= sq.getStart() && start <= sq.getEnd()) {
				match = true;
			}

			if (match) {
				parts.add(sq.getType());
			}

			if (end >= sq.getStart() && end <= sq.getEnd()) {
				match = false;
			}
		}

		return parts;

	}

	/**
	 * La méthode que je dois faire : prend un amplicon en paramètre et retourne
	 * l'annotatedSequence correspondante.
	 * 
	 * @return int : sequence size
	 */
	public AnnotatedSequence annotateAmplicon(String amplicon) {

		AnnotatedSequence annotAmplicon = new AnnotatedSequence();
		amplicon = amplicon.toLowerCase();
		int idxDeb = this.getSequence().indexOf(amplicon);
		int idxFin = idxDeb + amplicon.length();

		System.out.println("SEQUENCE: " + this.getSequence());
		System.out.println("AMPLICON: " + amplicon);
		System.out.println("DEBUT:    " + idxDeb);
		System.out.println("FIN:      " + idxFin);

		if (idxDeb == -1) {
			new IndexOutOfBoundsException(
					"Amplicon non trouvé dans la séquence annotée");
		}
		if (idxFin > this.getSequenceLenght()) {
			new IndexOutOfBoundsException(
					"La fin de l'amplicon dépasse de la séquence annotée");
		}

		boolean debFound = false;
		int subDeb;
		int subFin;
		int subIdxDeb;
		int subIdxFin;
		for (SequencePart sq : this.sequenceParts) {
			if (idxDeb >= sq.getStart() && debFound == false && idxDeb < sq.getEnd()) {
				subDeb = idxDeb - sq.getStart() + 1;
				debFound = true;
				subIdxDeb = idxDeb;
			}
			else {
				subDeb = 0;
				subIdxDeb = sq.getStart();
			}

			if (debFound == true) {
				if (idxFin <= sq.getEnd()) {
					subFin = (idxFin - sq.getStart()) + 1;
					debFound = false;
					subIdxFin = idxFin;
				}
				else {
					subFin = sq.getSequence().length();
					subIdxFin = sq.getEnd();
				} 
				String subSeq = sq.getSequence().substring(subDeb, subFin);
				annotAmplicon.add(sq.getType(), subSeq, subIdxDeb, subIdxFin);                        
			}

		}

		return annotAmplicon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
