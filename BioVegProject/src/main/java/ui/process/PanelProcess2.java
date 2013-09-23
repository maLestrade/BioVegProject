
package ui.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ui.LoadingWindow;
import ui.Result;
import ui.field.TabBasicParams;
import ui.field.TabThermoParams;
import ui.filechooser.FileChooserField;
import ui.filechooser.SequenceFileChooser;
import apollo.analysis.PrimerBlastHtmlParser.PrimerBlastHtmlParserException;
import apollo.analysis.RemotePrimerBlastNCBI;
import core.primerblast.AdvancedPrimerBlastOptions;
import core.primerquery.VitisPrimerQuery;

public class PanelProcess2 extends PanelProcess {
	
	private static final long serialVersionUID = 1L;

	private TabBasicParams pnlTabParam;

	private FileChooserField fileChooserField;
//	private JSpinner spinTmDiff;
//	private JSpinner spinLastSubSeqSize;

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

				fileChooserField = new FileChooserField(new File("."), new SequenceFileChooser("Choose sequence files"), "select sequence file", "no file selected");// new SequenceFileChooser("Choose sequence files"), "select sequence file", "no file selected");
				pnlView.add(fileChooserField, "wrap");
			}

			pnlTabParam = new TabBasicParams();
			pnlView.add(pnlTabParam, "spanx 2, wrap, growx");

//			{
//				JLabel lblTmDiff = new JLabel("<html>Max Tm <br/>difference");
//				pnlView.add(lblTmDiff);
//
//				spinTmDiff = new JSpinner(new SpinnerNumberModel(0d, 0d, 100d, 0.1d));
//				pnlView.add(spinTmDiff, "wrap");
//			}
//			
//			{
//				JLabel lblLastSubSeqSize = new JLabel("<html>Last sub <br/>sequence size");
//				pnlView.add(lblLastSubSeqSize);
//				
//				spinLastSubSeqSize = new JSpinner(new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1));
//				pnlView.add(spinLastSubSeqSize, "wrap");
//			}

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

		pnlTabParam.getSpinMaxProductSize().setValue(120);
		pnlTabParam.getSpinMinProductSize().setValue(80);

		pnlTabParam.getSpinMaxTm().setValue(63d);
		pnlTabParam.getSpinOptTm().setValue(60d);
		pnlTabParam.getSpinMinTm().setValue(57d);

		pnlTabParam.getSpinMaxGC().setValue(60d);
		pnlTabParam.getSpinOptGC().setValue(50d);
		pnlTabParam.getSpinMinGC().setValue(40d);

//		spinTmDiff.setValue(3d);
//		spinLastSubSeqSize.setValue(100);
		
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
		
		new LoadingWindow("Annotating sequence", parent) {
			@Override
			public void process() {
				
				System.out.println(fileChooserField.getFile().getName());
				
				String sequence = new String("");
				try {
					BufferedReader in = new BufferedReader(new FileReader(fileChooserField.getFile().toString()));					
					String line = null;
//				    String ls = System.getProperty("line.separator");

				    while( (line = in.readLine()) != null ) {
				        sequence = sequence+line;
				    }
				    in.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return;
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}

			    System.out.println("Sequence :\t"	+sequence);
			    System.out.println("Taille :\t"		+sequence.length());
				
				setMessage("Searching for primers");
				
				System.out.println("Set options...");				
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
				
				System.out.println("Prepare query...");
				VitisPrimerQuery query = new VitisPrimerQuery(
					fileChooserField.getFile().getName(), 
					sequence, 
					(Integer)pnlTabParam.getSpinLastSubSeqSize().getValue(), 
					(Double)pnlTabThermo.getSpinAnyMaxSelfComp().getValue(),
					(Double)pnlTabThermo.getSpinEndMaxSelfComp().getValue(),
					opt
				);
				
				System.out.println("Run analysis...");
				try {
					query.runAnalysis();
				} catch (Exception e) {
					if(e instanceof PrimerBlastHtmlParserException && e.getMessage().contains("is shorter than specified")) {
						JOptionPane.showMessageDialog(
							null,
							"Error: the sequence length ("
								+ sequence.length() 
								+ ") is shorter than the product size specified ("
								+ (Integer)pnlTabParam.getSpinMinProductSize().getValue()
								+ "-"
								+ (Integer)pnlTabParam.getSpinMaxProductSize().getValue() 
								+ ")",
							"Error: sequence lenght",
							JOptionPane.ERROR_MESSAGE);
					}
					e.printStackTrace();
					
					return;
				}
				
				setVisible(false);
				Result res = new Result(parent);
				res.printResult("Input sequence:\n", sequence, null, query);
				res.setVisible(true);
			}
		};
	}

	
}
