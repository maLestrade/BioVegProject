package core.sequence;

import java.util.ArrayList;

public class Sequence {
	
	private static final long serialVersionUID = 1L;
	private final ArrayList<SequencePart> sequenceParts;
	
	public Sequence() {
		sequenceParts = new ArrayList<SequencePart>();
	}
	
	public void add(SequencePartType type, String sequence, int start, int end) {
		sequenceParts.add(new SequencePart(type, sequence, start, end));
	}
	
	public String getSequence() {
		String out = "";
		
		for(SequencePart sp: sequenceParts) {
			out += sp.getSequence();
		}
		
		return out;
	}
	
	@Override
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
	
	public int getSequenceLenght() {
		return sequenceParts.get(sequenceParts.size()-1).getEnd();
	}
}
