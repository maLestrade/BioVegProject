package ui.worker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import core.primer.Primer;
import core.primer.PrimerCouple;
import core.sequence.AnnotatedSequence;
import core.sequence.SequencePartType;

public abstract class ResultWorker extends SwingWorker<String, String> {

	private final ResultWindow resultWindow;
	
	public ResultWorker(ResultWindow resultWindow) {
		this.resultWindow = resultWindow;
	}
	
	public List<String> printSequence(AnnotatedSequence seq) {
		ArrayList<String> out = new ArrayList<String>(2);
		out.add(">" + seq.getName());
		out.add(seq.getSequence().replaceAll("(.{60})", "$1\n"));
		return out;
	}
	
	public List<String> printCouples(List<PrimerCouple> chunks, AnnotatedSequence annotatedSequence) {
		final int size = chunks.size()*23 + 1;
		final ArrayList<String> buff = new ArrayList<String>(size);
		
		for (PrimerCouple couple : chunks) {
			buff.add("#############" + "\n");
			
			Primer forward = couple.getForward();
			Primer reverse = couple.getReverse();
			
			buff.add("Forward : "	+ forward.getName() 		+ "\n");
			buff.add("Seq : "		+ forward.getHybridSite() 	+ "\n");
			buff.add("Start : "		+ forward.getStart() 		+ "\n");
			buff.add("End : "		+ forward.getEnd() 			+ "\n");
			buff.add("Tm : "		+ forward.getTm() 			+ "\n");
			buff.add("GC% : "		+ forward.getGc() 			+ "\n");
			buff.add("Self : "		+ forward.getSelfCompAny() 	+ "\n");
			buff.add("Self 3' : "	+ forward.getSelfCompEnd() 	+ "\n");
			
			buff.add("-" + "\n");
			
			buff.add("Reverse : "	+ reverse.getName() 		+ "\n");
			buff.add("Seq : "		+ reverse.getHybridSite() 	+ "\n");
			buff.add("Start : "		+ reverse.getStart() 		+ "\n");
			buff.add("End : "		+ reverse.getEnd() 			+ "\n");
			buff.add("Tm : "		+ reverse.getTm() 			+ "\n");
			buff.add("GC% : "		+ reverse.getGc() 			+ "\n");
			buff.add("Self : "		+ reverse.getSelfCompAny() 	+ "\n");
			buff.add("Self 3' : "	+ reverse.getSelfCompEnd() 	+ "\n");
			
			buff.add("=> score : "   + couple.getScore()		+ "\n");
	
			buff.add("\n" + "The generated amplicon will be on :" + "\n");
	
			for(SequencePartType t : annotatedSequence.getPartTypesFromPositions(forward.getStart(), reverse.getStart())) {
				buff.add(t+" ");
			}
			buff.add("\n\n");
		}
		buff.add("#############" + "\n");
		
		return buff;
	}
	
	@Override
	protected void process(List<String> chunks) {
		for(String line: chunks) {
			resultWindow.appendResult(line);
			resultWindow.appendResult("\n");
		}
	}
}
