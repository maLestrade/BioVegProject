package ui.field;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class TabThermoParams extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JSpinner spinAnyMaxSelfComp;
	private final JSpinner spinEndMaxSelfComp;
	private final JSpinner spinAnyMaxPairComp;
	private final JSpinner spinEndMaxPairComp;
	private final JSpinner spinTHAnyMaxSelfComp;
	private final JSpinner spinTHEndMaxSelfComp;
	private final JSpinner spinTHAnyMaxPairComp;
	private final JSpinner spinTHEndMaxPairComp;
	private final JSpinner spinMaxPrimerHairpin;

	public TabThermoParams() {
		setBorder(BorderFactory.createTitledBorder("Thermodynamic Alignement Options"));
		setLayout(new MigLayout("ins 5 5 0 5", "[]10[100][100]", ""));
		setOpaque(false);
		
		add(new JLabel("Any"), "alignx center, skip1");
		add(new JLabel("3'"), "alignx center, wrap");
	
		add(new JLabel("<html>Max Self<br/> Complementarity"));
		spinAnyMaxSelfComp = new JSpinner(new SpinnerNumberModel(0d, 0d, Double.NaN, 0.1d));
		add(spinAnyMaxSelfComp, "growx");
		spinEndMaxSelfComp = new JSpinner(new SpinnerNumberModel(0d, 0d, Double.NaN, 0.1d));
		add(spinEndMaxSelfComp, "growx, wrap");
		
		add(new JLabel("<html>Max Pair<br/> Complementarity"));
		spinAnyMaxPairComp = new JSpinner(new SpinnerNumberModel(0d, 0d, Double.NaN, 0.1d));
		add(spinAnyMaxPairComp, "growx");
		spinEndMaxPairComp = new JSpinner(new SpinnerNumberModel(0d, 0d, Double.NaN, 0.1d));
		add(spinEndMaxPairComp, "growx, wrap");
		
		add(new JLabel("<html>TH: Max Self<br/> Complementarity"));
		spinTHAnyMaxSelfComp = new JSpinner(new SpinnerNumberModel(0d, 0d, 50d, 0.1d));
		add(spinTHAnyMaxSelfComp, "growx");
		spinTHEndMaxSelfComp = new JSpinner(new SpinnerNumberModel(0d, 0d, 50d, 0.1d));
		add(spinTHEndMaxSelfComp, "growx, wrap");
		
		add(new JLabel("<html>TH: Max Pair<br/> Complementarity"));
		spinTHAnyMaxPairComp = new JSpinner(new SpinnerNumberModel(0d, 0d, 50d, 0.1d));
		add(spinTHAnyMaxPairComp, "growx");
		spinTHEndMaxPairComp = new JSpinner(new SpinnerNumberModel(0d, 0d, 50d, 0.1d));
		add(spinTHEndMaxPairComp, "growx, wrap");
		
		add(new JLabel("<html>TH: Max Primer<br/> Hairpin"));
		spinMaxPrimerHairpin = new JSpinner(new SpinnerNumberModel(0d, 0d, 50d, 0.1d));
		add(spinMaxPrimerHairpin, "growx");
	}

	public JSpinner getSpinAnyMaxSelfComp() {
		return spinAnyMaxSelfComp;
	}

	public JSpinner getSpinEndMaxSelfComp() {
		return spinEndMaxSelfComp;
	}

	public JSpinner getSpinAnyMaxPairComp() {
		return spinAnyMaxPairComp;
	}

	public JSpinner getSpinEndMaxPairComp() {
		return spinEndMaxPairComp;
	}

	public JSpinner getSpinMaxPrimerHairpin() {
		return spinMaxPrimerHairpin;
	}

	public JSpinner getSpinTHAnyMaxSelfComp() {
		return spinTHAnyMaxSelfComp;
	}

	public JSpinner getSpinTHEndMaxSelfComp() {
		return spinTHEndMaxSelfComp;
	}

	public JSpinner getSpinTHAnyMaxPairComp() {
		return spinTHAnyMaxPairComp;
	}

	public JSpinner getSpinTHEndMaxPairComp() {
		return spinTHEndMaxPairComp;
	}

}