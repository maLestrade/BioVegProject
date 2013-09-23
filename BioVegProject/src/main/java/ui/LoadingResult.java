package ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import core.primer.Primer;
import core.primer.PrimerCouple;
import core.sequence.AnnotatedSequence;
import core.sequence.SequencePartType;

public abstract class LoadingResult extends SwingWorker<String, String> {

	private final JFrame resultWindow;
	
	private final JLabel lblProgressMessage;
	private final JProgressBar progressBar;
	private final JTextArea txtAResult;	
	
	public LoadingResult(JFrame parent, final String[] stepNames) {
		resultWindow = new JFrame("Results");
		
		resultWindow.setLayout(new MigLayout("ins 5, hidemode 3", "[grow]", ""));
		
		{ // Progress status
			this.lblProgressMessage = new JLabel("");
			lblProgressMessage.setBorder(new EmptyBorder(5,5,5,5));
			resultWindow.add(lblProgressMessage, "grow, wrap");
			
			this.progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
			resultWindow.add(progressBar, "grow, wrap");
		}
		
		{ // Text result
			txtAResult = new JTextArea();
			txtAResult.setEditable(false);
			
			JScrollPane scroll = new JScrollPane(txtAResult);
			scroll.setViewportBorder(BorderFactory.createEmptyBorder());
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.getViewport().setOpaque(false);
			scroll.setOpaque(false);
			scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			resultWindow.add(scroll);
		}
		
		resultWindow.setBounds(100, 100, 500, 500);
		resultWindow.setLocationRelativeTo(parent);
		
		addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println(event);
				switch (event.getPropertyName()) {
					case "progress":
						if(stepNames != null) {
							int step = (int) event.getNewValue();
							if(step<stepNames.length) {
								lblProgressMessage.setText(stepNames[step]);
							}
						}
						break;
					case "state":
						switch ((StateValue) event.getNewValue()) {
							case DONE:
								progressBar.setVisible(false);
								lblProgressMessage.setVisible(false);
							default: break;
						}
						break;
					default: break;
				}
			}
		});
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
			txtAResult.append(line);
			txtAResult.append("\n");
		}
	}
}
