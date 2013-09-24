
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
import ui.field.TabBasicParams;
import ui.field.TabThermoParams;
import ui.filechooser.FileChooserField;
import ui.filechooser.SequenceFileChooser;
import ui.worker.ResultWindow;
import ui.worker.ResultWorker;
import apollo.analysis.PrimerBlastHtmlParser.PrimerBlastHtmlParserException;
import apollo.analysis.RemotePrimerBlastNCBI;
import core.primerblast.AdvancedPrimerBlastOptions;
import core.primerquery.VitisPrimerQuery;
import core.sequence.AnnotatedSequence;
import core.sequence.SequencePartType;

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
		final String[] steps = {"Loading sequence", "Searching for primers"};

		ResultWindow window = new ResultWindow(parent);
		window.setVisible(true);
		ResultWorker worker = new ResultWorker(window) {
			@Override
			protected String doInBackground() throws Exception {
				AnnotatedSequence seq = null;
				
				publish(steps[0]+"...");
				{ // Retrieving sequence
					setProgress(0);
					
					seq = new AnnotatedSequence("Input sequence:");
					
					try {
						String sequence = new String("");
						BufferedReader in = new BufferedReader(new FileReader(fileChooserField.getFile().toString()));					
						String line = null;
						//String ls = System.getProperty("line.separator");
						while( (line = in.readLine()) != null ) {
							sequence = sequence+line;
						}
						in.close();
						seq.add(SequencePartType.UNKNOWN, sequence, 0, sequence.length()-1);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return "";
					} catch (IOException e) {
						e.printStackTrace();
						return "";
					}
					
					printSequence(seq);
				}
				
				publish(steps[1]+"...");
				{ // Searching for primers
					setProgress(1);
					

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
						seq.getSequence(), 
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
									+ seq.getSequenceLenght() 
									+ ") is shorter than the product size specified ("
									+ (Integer)pnlTabParam.getSpinMinProductSize().getValue()
									+ "-"
									+ (Integer)pnlTabParam.getSpinMaxProductSize().getValue() 
									+ ")",
								"Error: sequence lenght",
								JOptionPane.ERROR_MESSAGE);
						}
						else
							e.printStackTrace();
						
						return "";
					}
					
					// Display results
					printCouples(query.getPrimerSet().getPrimerCouples(), seq);
				}
				
				return "";
			}
		};
		
		window.run(worker, steps);
		worker.execute();
	}
	
}
