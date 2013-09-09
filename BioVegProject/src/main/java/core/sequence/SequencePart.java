package core.sequence;

public class SequencePart {
	
	private final SequencePartType type;
	private final String sequence;
	private final int start;
	private final int end;
	
	public SequencePart(SequencePartType type, String sequence, int start, int end) {
		this.type = type;
		this.sequence = sequence.toLowerCase().replace("\n", "");
		this.start = start;
		this.end = end;
	}

	public SequencePartType getType() {
		return type;
	}

	public String getSequence() {
		return sequence;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
}
