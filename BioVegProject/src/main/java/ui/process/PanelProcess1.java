package ui.process;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import ui.LoadingWindow;
import ui.Result;
import ui.field.TabBasicParams;
import ui.field.TabThermoParams;
import apollo.analysis.RemotePrimerBlastNCBI;
import core.genoscope.GenoscopeVitisSequenceQuery;
import core.primer.Primer;
import core.primer.PrimerCouple;
import core.primerblast.AdvancedPrimerBlastOptions;
import core.primerquery.VitisPrimerQuery;
import core.sequence.AnnotatedSequence;
import core.sequence.SequencePart;
import core.sequence.SequencePartType;

public class PanelProcess1 extends PanelProcess {

	private static final long serialVersionUID = 1L;

	private TabBasicParams pnlTabParam;

	private JTextField txtAccNum;

	private TabThermoParams pnlTabThermo;
	private final JFrame parent;

	public PanelProcess1(JFrame parent) {
		super("From accesion number", parent);
		this.parent = parent;
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

		pnlTabParam.getSpinMaxTm().setValue(63d);
		pnlTabParam.getSpinOptTm().setValue(60d);
		pnlTabParam.getSpinMinTm().setValue(57d);

		pnlTabParam.getSpinMaxGC().setValue(60d);
		pnlTabParam.getSpinOptGC().setValue(50d);
		pnlTabParam.getSpinMinGC().setValue(40d);

		pnlTabParam.getSpinTmDiff().setValue(3d);
		pnlTabParam.getSpinLastSubSeqSize().setValue(1000);
		pnlTabParam.getSpinMaxGCEnd().setValue(2);

		pnlTabThermo.getSpinAnyMaxSelfComp().setValue(6d);
		pnlTabThermo.getSpinEndMaxSelfComp().setValue(3d);
		pnlTabThermo.getSpinAnyMaxPairComp().setValue(6d);
		pnlTabThermo.getSpinEndMaxPairComp().setValue(3d);
		pnlTabThermo.getSpinTHAnyMaxSelfComp().setValue(30d);
		pnlTabThermo.getSpinTHEndMaxSelfComp().setValue(30d);
		pnlTabThermo.getSpinTHAnyMaxPairComp().setValue(30d);
		pnlTabThermo.getSpinTHEndMaxPairComp().setValue(30d);
		pnlTabThermo.getSpinMaxPrimerHairpin().setValue(40d);
	}

	@Override
	protected void run() {
		final StringBuffer buff = new StringBuffer();
		new LoadingWindow("Retrieving Genoscope Sequence", parent) {
			@Override
			public void process() {

				AnnotatedSequence seq = null;
				try {
					GenoscopeVitisSequenceQuery gvsq = new GenoscopeVitisSequenceQuery(txtAccNum.getText());
					seq = gvsq.getAnnotatedSequence();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				//JOptionPane.showMessageDialog(null, seq.getSequence());
				setMessage("Searching for primers");
				
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
				opt.setMaxGCEnd((Integer)pnlTabParam.getSpinMaxGCEnd().getValue());

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
					
					buff.append("\n" + "The generated amplicon will be on :" + "\n");
					
					for(SequencePartType t : seq.getPartTypesFromPositions(forward.getStart(), reverse.getStart())) {
						buff.append(t+" ");
					}
					buff.append("\n\n");
					
				}
				buff.append("#############" + "\n");
				
				setVisible(false);
				(new Result(buff.toString(), parent)).setVisible(true);
			}
		};
	}


}