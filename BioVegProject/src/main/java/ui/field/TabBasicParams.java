package ui.field;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class TabBasicParams extends JPanel {
	public static final long serialVersionUID = 1L;
	
	private final JSpinner spinMinProductSize;
	private final JSpinner spinMaxProductSize;
	private final JSpinner spinMinPrimerSize;
	private final JSpinner spinOptPrimerSize;
	private final JSpinner spinMaxPrimerSize;
	private final JSpinner spinMinTm;
	private final JSpinner spinOptTm;
	private final JSpinner spinMaxTm;
	private final JSpinner spinMinGC;
	private final JSpinner spinOptGC;
	private final JSpinner spinMaxGC;
	
	private final JSpinner spinTmDiff;
	private final JSpinner spinLastSubSeqSize;
	private final JSpinner spinMaxGCEnd;	

	public TabBasicParams() {
		setBorder(BorderFactory.createTitledBorder("Standard Options"));
		setLayout(new MigLayout("ins 5 5 0 5", "[][grow][grow][grow]", "[][][][][][][][]"));
		setOpaque(false);
		
		JLabel lblMin = new JLabel("Minimum");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMin, "skip 1, grow");
		JLabel lblOpt = new JLabel("Optimal");
		lblOpt.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOpt, "grow");
		JLabel lblMax = new JLabel("Maximum");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMax, "grow, wrap");
	
		// PRODUCT SIZE
		JLabel lblProductSize = new JLabel("PCR Product size");
		add(lblProductSize);
		{
			spinMinProductSize = new JSpinner();
			add(spinMinProductSize, "growx");
			
			spinMaxProductSize = new JSpinner();
			add(spinMaxProductSize, "skip 1, grow, wrap");
			
			new MinOptMaxField(spinMinProductSize, spinMaxProductSize);
		}

		// PRIMER SIZE
		JLabel lblPrimerSize = new JLabel("Primer size");
		add(lblPrimerSize);
		{
			spinMinPrimerSize = new JSpinner();
			add(spinMinPrimerSize, "growx");
			
			spinOptPrimerSize = new JSpinner();
			add(spinOptPrimerSize, "grow");	
			
			spinMaxPrimerSize = new JSpinner();
			add(spinMaxPrimerSize, "grow, wrap");	
			
			new MinOptMaxField(spinMinPrimerSize, spinOptPrimerSize, spinMaxPrimerSize);
		}

		// TM
		JLabel lblTm = new JLabel("Tm");
		add(lblTm);
		{
			spinMinTm = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMinTm, "grow");
			
			spinOptTm = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinOptTm, "grow");	
			
			spinMaxTm = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMaxTm, "grow, wrap");	
			
			new MinOptMaxField(spinMinTm, spinOptTm, spinMaxTm);
		}

		// GC%
		JLabel lblGC = new JLabel("GC%");
		add(lblGC);
		{
			spinMinGC = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMinGC, "grow");

			spinOptGC = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinOptGC, "grow");

			spinMaxGC = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMaxGC, "grow, wrap");
			
			new MinOptMaxField(spinMinGC, spinOptGC, spinMaxGC);
		}
		
		// TM diff
		{
			JLabel lblTmDiff = new JLabel("<html>Max Tm <br/>difference");
			add(lblTmDiff);

			spinTmDiff = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinTmDiff, "growx, wrap");
		}

		// Last subsequence size
		{
			JLabel lblLastSubSeqSize = new JLabel("<html>Last sub <br/>sequence size");
			add(lblLastSubSeqSize);

			SpinnerNumberModel model = new SpinnerNumberModel();
			model.setMinimum(0);
			spinLastSubSeqSize = new JSpinner(model);
			add(spinLastSubSeqSize, "growx, wrap");
		}
		
		// Max GC end
		{
			add(new JLabel("<html>Max GC at end <br/>(five last nucleotides)"));
			
			spinMaxGCEnd = new JSpinner(new SpinnerNumberModel(2, 0, 5, 1));
			add(spinMaxGCEnd, "aligny top, growx");
		}
	}

	public JSpinner getSpinMinProductSize() {
		return spinMinProductSize;
	}

	public JSpinner getSpinMaxProductSize() {
		return spinMaxProductSize;
	}

	public JSpinner getSpinMinPrimerSize() {
		return spinMinPrimerSize;
	}

	public JSpinner getSpinOptPrimerSize() {
		return spinOptPrimerSize;
	}

	public JSpinner getSpinMaxPrimerSize() {
		return spinMaxPrimerSize;
	}

	public JSpinner getSpinMinTm() {
		return spinMinTm;
	}

	public JSpinner getSpinOptTm() {
		return spinOptTm;
	}

	public JSpinner getSpinMaxTm() {
		return spinMaxTm;
	}

	public JSpinner getSpinMinGC() {
		return spinMinGC;
	}

	public JSpinner getSpinOptGC() {
		return spinOptGC;
	}

	public JSpinner getSpinMaxGC() {
		return spinMaxGC;
	}

	public JSpinner getSpinTmDiff() {
		return spinTmDiff;
	}

	public JSpinner getSpinLastSubSeqSize() {
		return spinLastSubSeqSize;
	}

	public JSpinner getSpinMaxGCEnd() {
		return spinMaxGCEnd;
	}
	
	
}
