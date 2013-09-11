package ui.process;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import apollo.analysis.RemotePrimerBlastNCBI;
import core.genoscope.GenoscopeVitisSequenceQuery;
import core.primer.Primer;
import core.primer.PrimerCouple;
import core.primerblast.AdvancedPrimerBlastOptions;
import core.primerquery.VitisPrimerQuery;
import core.sequence.AnnotatedSequence;
import net.miginfocom.swing.MigLayout;
import ui.field.TabBasicParams;
import ui.field.TabThermoParams;

public class PanelProcess1 extends PanelProcess {

	private static final long serialVersionUID = 1L;

	private TabBasicParams pnlTabParam;

	private JTextField txtAccNum;

	private TabThermoParams pnlTabThermo;


	public PanelProcess1() {
		super("From accesion number");
	}

	@Override
	protected JPanel initPanel() {
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

		pnlTabParam.getSpinTmDiff().setValue(3d);
		pnlTabParam.getSpinLastSubSeqSize().setValue(100);

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
		AnnotatedSequence seq = null;
		try {
			GenoscopeVitisSequenceQuery gvsq = new GenoscopeVitisSequenceQuery(txtAccNum.getText());
			seq = gvsq.getAnnotatedSequence();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(null, seq.getSequence());

		
		AdvancedPrimerBlastOptions opt = new AdvancedPrimerBlastOptions();
		// PRIMERBLAST OPTIONS
		opt = new AdvancedPrimerBlastOptions();

		opt.setPrimerProductMin((Integer)pnlTabParam.getSpinMinProductSize().getValue());
		opt.setPrimerProductMax((Integer)pnlTabParam.getSpinMaxProductSize().getValue());

		opt.setPrimerNumReturn(30);

		opt.setPrimerMinTm((Double)pnlTabParam.getSpinMinTm().getValue());
		opt.setPrimerOptTm((Double)pnlTabParam.getSpinOptTm().getValue());
		opt.setPrimerMaxTm((Double)pnlTabParam.getSpinMaxTm().getValue());
		opt.setPrimerMaxDiffTm((Double)pnlTabParam.getSpinTmDiff().getValue());

		opt.setSearchSpecificPrimer(true);
		opt.setPrimerSpecificityDatabase(RemotePrimerBlastNCBI.PrimerBlastOptions.Database.nt);
		opt.setOrganism("29760");

		opt.setPrimerSizeMin((Integer)pnlTabParam.getSpinMinPrimerSize().getValue());
		opt.setPrimerSizeOpt((Integer)pnlTabParam.getSpinOptPrimerSize().getValue());
		opt.setPrimerSizeMax((Integer)pnlTabParam.getSpinMaxPrimerSize().getValue());

		opt.setGCMin((Double)pnlTabParam.getSpinMinGC().getValue());
		opt.setGCMax((Double)pnlTabParam.getSpinMaxGC().getValue());
		opt.setMaxGCEnd(2);

		opt.setThAlignment(true);
		opt.setThSelfAny((Double)pnlTabThermo.getSpinTHAnyMaxSelfComp().getValue());
		opt.setThSelfEnd((Double)pnlTabThermo.getSpinTHEndMaxSelfComp().getValue());
		opt.setThPairAny((Double)pnlTabThermo.getSpinTHAnyMaxPairComp().getValue());
		opt.setThPairEnd((Double)pnlTabThermo.getSpinTHEndMaxPairComp().getValue());
		opt.setMaxHairpin((Double)pnlTabThermo.getSpinMaxPrimerHairpin().getValue());
		
		VitisPrimerQuery query = new VitisPrimerQuery(
			txtAccNum.getText(), 
			seq.getSequence(), 
			(Integer)pnlTabParam.getSpinLastSubSeqSize().getValue(), 
			(Double)pnlTabThermo.getSpinAnyMaxSelfComp().getValue(),
			(Double)pnlTabThermo.getSpinEndMaxSelfComp().getValue(),
			opt
		);
		
		try {
			query.runAnalysis();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		for (PrimerCouple couple : query.getPrimerSet().getPrimerCouples()) {
			System.out.println("#############");
			
			Primer forward = couple.getForward();
			Primer reverse = couple.getReverse();
			
			System.out.println("Forward : "+forward.getName());
			System.out.println("Seq : "+forward.getHybridSite());
			System.out.println("Start : "+forward.getStart());
			System.out.println("End : "+forward.getEnd());
			System.out.println("Tm : "+forward.getTm());
			System.out.println("GC% : "+forward.getGc());
			System.out.println("Self : "+forward.getSelfCompAny());
			System.out.println("Self 3' : "+forward.getSelfCompEnd());
			System.out.println("-");
			System.out.println("Reverse : "+reverse.getName());
			System.out.println("Seq : "+reverse.getHybridSite());
			System.out.println("Start : "+reverse.getStart());
			System.out.println("End : "+reverse.getEnd());
			System.out.println("Tm : "+reverse.getTm());
			System.out.println("GC% : "+reverse.getGc());
			System.out.println("Self : "+reverse.getSelfCompAny());
			System.out.println("Self 3' : "+reverse.getSelfCompEnd());
			
		}
		System.out.println("#############");
	}


}