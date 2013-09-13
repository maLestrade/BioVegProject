
package ui.process;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;
import ui.field.TabBasicParams;
import ui.field.TabThermoParams;
import ui.filechooser.FileChooserField;
import ui.filechooser.SequenceFileChooser;

public class PanelProcess2 extends PanelProcess {
	
	private static final long serialVersionUID = 1L;

	private TabBasicParams pnlTabParam;

	private FileChooserField fileChooserField;
	private JSpinner spinTmDiff;
	private JSpinner spinLastSubSeqSize;

	private TabThermoParams pnlTabThermo;	
	private final JFrame parent;
	
	public PanelProcess2(JFrame parent) {
		super("From sequence", parent);
		this.parent = parent;
	}
	
	@Override
	protected JPanel initPanel() {
		JPanel pnlView = new JPanel();
		{
			pnlView.setLayout(new MigLayout("", "[]20[grow]", "[top][top][top][top][bottom,grow]"));
			pnlView.setOpaque(false);
			
			{
				JLabel lblAccNum = new JLabel("Sequence file");
				pnlView.add(lblAccNum);

				fileChooserField = new FileChooserField(new File("."), new SequenceFileChooser("Choose sequence files"), "select sequence file", "no file selected");
				pnlView.add(fileChooserField, "wrap");
			}

			pnlTabParam = new TabBasicParams();
			pnlView.add(pnlTabParam, "spanx 2, wrap, growx");

			{
				JLabel lblTmDiff = new JLabel("<html>Max Tm <br/>difference");
				pnlView.add(lblTmDiff);

				spinTmDiff = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
				pnlView.add(spinTmDiff, "wrap");
			}
			
			{
				JLabel lblLastSubSeqSize = new JLabel("<html>Last sub <br/>sequence size");
				pnlView.add(lblLastSubSeqSize);
				
				spinLastSubSeqSize = new JSpinner(new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1));
				pnlView.add(spinLastSubSeqSize, "wrap");
			}

			pnlTabThermo = new TabThermoParams();
			pnlView.add(pnlTabThermo, "spanx 2, wrap, growx");
		}
		return pnlView;
	}
	
	@Override
	protected void initValues() {
		pnlTabParam.getSpinMaxPrimerSize().setValue(22);
		pnlTabParam.getSpinOptPrimerSize().setValue(20);
		pnlTabParam.getSpinMinPrimerSize().setValue(18);

		pnlTabParam.getSpinMaxProductSize().setValue(450);
		pnlTabParam.getSpinMinProductSize().setValue(300);

		pnlTabParam.getSpinMaxTm().setValue(57d);
		pnlTabParam.getSpinOptTm().setValue(60d);
		pnlTabParam.getSpinMinTm().setValue(63d);

		pnlTabParam.getSpinMaxGC().setValue(60d);
		pnlTabParam.getSpinOptGC().setValue(50d);
		pnlTabParam.getSpinMinGC().setValue(40d);

		spinTmDiff.setValue(3d);
		spinLastSubSeqSize.setValue(100);
		
		pnlTabThermo.getSpinAnyMaxSelfComp().setValue(6d);
		pnlTabThermo.getSpinEndMaxSelfComp().setValue(3d);
		pnlTabThermo.getSpinAnyMaxPairComp().setValue(6d);
		pnlTabThermo.getSpinEndMaxPairComp().setValue(3d);
		pnlTabThermo.getSpinTHAnyMaxSelfComp().setValue(30d);
		pnlTabThermo.getSpinTHEndMaxSelfComp().setValue(30d);
		pnlTabThermo.getSpinTHAnyMaxPairComp().setValue(30d);
		pnlTabThermo.getSpinTHEndMaxPairComp().setValue(30d);
		pnlTabThermo.getSpinMaxPrimerHairpin().setValue(30d);
	}

	@Override
	protected void run() {
		
	}

	
}
