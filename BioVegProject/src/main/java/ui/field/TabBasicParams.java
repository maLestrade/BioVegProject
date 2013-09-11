package ui.field;

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
	
	public TabBasicParams() {
		setLayout(new MigLayout("", "[][grow][grow][grow]", "[][][][][]"));
		setOpaque(false);
		
		JLabel lblMin = new JLabel("Minimum");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMin, "skip 1,grow");
		JLabel lblOpt = new JLabel("Optimal");
		lblOpt.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOpt, "grow");
		JLabel lblMax = new JLabel("Maximum");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMax, "grow,wrap");
	
		JLabel lblProductSize = new JLabel("PCR Product size");
		add(lblProductSize);
		{
			spinMinProductSize = new JSpinner();
			add(spinMinProductSize, "growx");
			
			spinMaxProductSize = new JSpinner();
			add(spinMaxProductSize, "skip 1,grow,wrap");
			
			new MinOptMaxField(spinMinProductSize, spinMaxProductSize);
		}
		
		JLabel lblPrimerSize = new JLabel("Primer size");
		add(lblPrimerSize);
		{
			spinMinPrimerSize = new JSpinner();
			add(spinMinPrimerSize, "grow");
			
			spinOptPrimerSize = new JSpinner();
			add(spinOptPrimerSize, "grow");	
			
			spinMaxPrimerSize = new JSpinner();
			add(spinMaxPrimerSize, "grow,wrap");	
			
			new MinOptMaxField(spinMinPrimerSize, spinOptPrimerSize, spinMaxPrimerSize);
		}
		
		JLabel lblTm = new JLabel("Tm");
		add(lblTm);
		{
			spinMinTm = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMinTm, "grow");
			
			spinOptTm = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinOptTm, "grow");	
			
			spinMaxTm = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMaxTm, "grow,wrap");	
			
			new MinOptMaxField(spinMinTm, spinOptTm, spinMaxTm);
		}
		
		JLabel lblGC = new JLabel("GC%");
		add(lblGC);
		{
			spinMinGC = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMinGC, "grow");

			spinOptGC = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinOptGC, "grow");

			spinMaxGC = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
			add(spinMaxGC, "grow");
			
			new MinOptMaxField(spinMinGC, spinOptGC, spinMaxGC);
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
}
