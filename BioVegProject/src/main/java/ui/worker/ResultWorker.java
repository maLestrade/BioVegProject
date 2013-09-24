package ui.worker;

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
	
	public void printSequence(AnnotatedSequence seq) {
		publish(
			">" + seq.getName(),
			seq.getSequence(),
			"\n"
		);
	}
	
	public void printCouples(List<PrimerCouple> chunks, AnnotatedSequence annotatedSequence) {
		for (PrimerCouple couple : chunks) {
			publish("#############");
			
			Primer forward = couple.getForward();
			Primer reverse = couple.getReverse();
			
			publish("Forward : "	+ forward.getName() 	);
			publish("Seq : "		+ forward.getHybridSite());
			publish("Start : "		+ forward.getStart() 	);
			publish("End : "		+ forward.getEnd() 		);
			publish("Tm : "			+ forward.getTm() 		);
			publish("GC% : "		+ forward.getGc() 		);
			publish("Self : "		+ forward.getSelfCompAny());
			publish("Self 3' : "	+ forward.getSelfCompEnd());
			
			publish("-");
			
			publish("Reverse : "	+ reverse.getName() 	);
			publish("Seq : "		+ reverse.getHybridSite());
			publish("Start : "		+ reverse.getStart() 	);
			publish("End : "		+ reverse.getEnd() 		);
			publish("Tm : "			+ reverse.getTm() 		);
			publish("GC% : "		+ reverse.getGc() 		);
			publish("Self : "		+ reverse.getSelfCompAny());
			publish("Self 3' : "	+ reverse.getSelfCompEnd());
			
			publish("=> score : "   + couple.getScore()	);
	
			publish("\n" + "The generated amplicon will be on :");
			String parts = "";
			for(SequencePartType t : annotatedSequence.getPartTypesFromPositions(forward.getStart(), reverse.getStart())) {
				parts += t+" ";
			}
			publish(parts+"\n");
		}
		publish("#############");
		
	}
	
	@Override
	protected void process(List<String> chunks) {
		for(String line: chunks) {
			resultWindow.appendResult(line);
			resultWindow.appendResult("\n");
		}
	}
}
