package ui.process;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;
import ui.field.TabBasicParams;
import ui.field.TabThermoParams;

public class PanelProcess extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JTextField txtAccNum;

	protected TabBasicParams pnlTabParam;

	protected final JSpinner spinTmDiff;
	protected JSpinner spinLastSubSeqSize;

	protected TabThermoParams pnlTabThermo;	

	public PanelProcess() {
		setName("From accesion number");
		setLayout(new BorderLayout());
		setOpaque(false);

		JPanel pnlView = new JPanel();
		{
			pnlView.setLayout(new MigLayout("", "[]20[grow]", "[top][top][top][top][bottom,grow]"));
			pnlView.setOpaque(false);
			
			{
				JLabel lblAccNum = new JLabel("Accession Number");
				pnlView.add(lblAccNum);

				txtAccNum = new JTextField();
				txtAccNum.setColumns(15);
				pnlView.add(txtAccNum, "wrap");
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

		JScrollPane scroll = new JScrollPane(pnlView);
		scroll.setViewportBorder(BorderFactory.createEmptyBorder());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		add(scroll, BorderLayout.CENTER);

		JButton btnSubmit = new JButton("Search Primers");
		add(btnSubmit, BorderLayout.SOUTH);

		initValues();
	}

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
		pnlTabThermo.getSpinMaxPrimerHairpin().setValue(40d);
	}


}
