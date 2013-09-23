package ui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import core.primer.Primer;
import core.primer.PrimerCouple;
import core.primerquery.VitisPrimerQuery;
import core.sequence.AnnotatedSequence;
import core.sequence.SequencePartType;

public class Result extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JTextArea txtAResult;
	
	public Result(JFrame parent) {
		setTitle("Results");
		setBounds(100, 100, 500, 500);
		
		txtAResult = new JTextArea();
		txtAResult.setLineWrap(true);
		txtAResult.setFont(new Font("Monospaced",Font.PLAIN,15));
		txtAResult.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(new OnlyVerticalScrollPanel(txtAResult));
		scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(scroll);
		
		setLocationRelativeTo(parent);
	}

	public void printResult(String result) {
		txtAResult.setText(result);
	}
	
	public void printResult(AnnotatedSequence annotatedSequence, VitisPrimerQuery query) {
		final StringBuffer buff = new StringBuffer();
		
		buff.append(">" + annotatedSequence.getName() + "\n");
		buff.append(annotatedSequence.getSequence()+"\n");
		
		for (PrimerCouple couple : query.getPrimerSet().getPrimerCouples()) {
			buff.append("#############" + "\n");
			
			Primer forward = couple.getForward();
			Primer reverse = couple.getReverse();
			
			buff.append("Forward : "	+ forward.getName() 		+ "\n");
			buff.append("Seq : "		+ forward.getHybridSite() 	+ "\n");
			buff.append("Start : "		+ forward.getStart() 		+ "\n");
			buff.append("End : "		+ forward.getEnd() 			+ "\n");
			buff.append("Tm : "			+ forward.getTm() 			+ "\n");
			buff.append("GC% : "		+ forward.getGc() 			+ "\n");
			buff.append("Self : "		+ forward.getSelfCompAny() 	+ "\n");
			buff.append("Self 3' : "	+ forward.getSelfCompEnd() 	+ "\n");
			
			buff.append("-" + "\n");
			
			buff.append("Reverse : "	+ reverse.getName() 		+ "\n");
			buff.append("Seq : "		+ reverse.getHybridSite() 	+ "\n");
			buff.append("Start : "		+ reverse.getStart() 		+ "\n");
			buff.append("End : "		+ reverse.getEnd() 			+ "\n");
			buff.append("Tm : "			+ reverse.getTm() 			+ "\n");
			buff.append("GC% : "		+ reverse.getGc() 			+ "\n");
			buff.append("Self : "		+ reverse.getSelfCompAny() 	+ "\n");
			buff.append("Self 3' : "	+ reverse.getSelfCompEnd() 	+ "\n");
			
			buff.append("=> score : "   + couple.getScore()			+ "\n");
			
			buff.append("\n" + "The generated amplicon will be on :" + "\n");
			
			if(annotatedSequence != null) {
				for(SequencePartType t : annotatedSequence.getPartTypesFromPositions(forward.getStart(), reverse.getStart())) {
					buff.append(t+" ");
				}
				buff.append("\n\n");
			}
		}
		buff.append("#############" + "\n");
		printResult(buff.toString());
	}	
	
}
