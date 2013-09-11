package ui.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class TabThermoParams extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JCheckBox chkThermoAli;
	private final JSpinner spinAnyMaxSelfComp;
	private final JSpinner spinEndMaxSelfComp;
	private final JSpinner spinAnyMaxPairComp;
	private final JSpinner spinEndMaxPairComp;
	private final JSpinner spinMaxPrimerHairpin;

	public TabThermoParams() {
		setLayout(new MigLayout("", "[]10[100][100]", ""));
		setOpaque(false);
		
		chkThermoAli = new JCheckBox("Use Thermodynamic Oligo Alignment");
		add(chkThermoAli, "spanx 3, wrap");
		
		add(new JLabel("Any"), "alignx center, skip1");
		add(new JLabel("3'"), "alignx center, wrap");
	
		add(new JLabel("<html>Max Self<br/> Complementarity"));
		spinAnyMaxSelfComp = new JSpinner(new SpinnerNumberModel(0.0d, 0.0d, Double.NaN, 0.1d));
		add(spinAnyMaxSelfComp, "growx");
		spinEndMaxSelfComp = new JSpinner(new SpinnerNumberModel(0.0d, 0.0d, Double.NaN, 0.1d));
		add(spinEndMaxSelfComp, "wrap, growx");
		
		add(new JLabel("<html>Max Pair<br/> Complementarity"));
		spinAnyMaxPairComp = new JSpinner(new SpinnerNumberModel(0.0d, 0.0d, Double.NaN, 0.1d));
		add(spinAnyMaxPairComp, "growx");
		spinEndMaxPairComp = new JSpinner(new SpinnerNumberModel(0.0d, 0.0d, Double.NaN, 0.1d));
		add(spinEndMaxPairComp, "wrap, growx");
		
		add(new JLabel("<html>TH: Max Primer<br/> Hairpin"));
		spinMaxPrimerHairpin = new JSpinner();
		add(spinMaxPrimerHairpin, "growx");
		
		chkThermoAli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateCheck();
			}
		});
		updateCheck();
	}
	
	private void updateCheck() {
		boolean enabled = chkThermoAli.isSelected();
		
		for(Component c: this.getComponents()) {
			if(c instanceof JLabel)
				((JLabel)c).setEnabled(enabled);
			else if(c instanceof JSpinner)
				((JSpinner)c).setEnabled(enabled);
		}
	}

	public JCheckBox getChkThermoAli() {
		return chkThermoAli;
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

}