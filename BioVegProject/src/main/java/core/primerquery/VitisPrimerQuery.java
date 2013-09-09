package core.primerquery;

import org.biojava3.core.sequence.DNASequence;

import apollo.analysis.RemotePrimerBlastNCBI;
import apollo.datamodel.CurationSet;
import apollo.datamodel.FeatureSetI;
import apollo.datamodel.SeqFeatureI;
import apollo.datamodel.Sequence;
import apollo.datamodel.StrandedFeatureSet;
import core.primer.Primer;
import core.primer.PrimerSet;
import core.primerblast.AdvancedPrimerBlast;
import core.primerblast.AdvancedPrimerBlastOptions;


public class VitisPrimerQuery {
	
	private Sequence sequence;
	private AdvancedPrimerBlastOptions opt;
	private AdvancedPrimerBlast rp;
	private CurationSet cs;
	private PrimerSet primerSet;
	
	public VitisPrimerQuery(String numAcc, String seq) {
		this.initAdvancedPrimerBlastOptions();
		this.rp = new AdvancedPrimerBlast(opt);
		this.sequence = new Sequence(numAcc, seq);
	}
	
	public void runAnalysis() throws Exception {
		this.cs = new CurationSet();
		cs.setResults(new StrandedFeatureSet());
		cs.setRefSequence(this.sequence);
		
		rp.runAnalysis(cs, this.sequence, 1);
	}
	
	private void initAdvancedPrimerBlastOptions() {
		this.opt = new AdvancedPrimerBlastOptions();
		
		this.opt.setPrimerProductMin(300);
		this.opt.setPrimerProductMax(400);
		
		this.opt.setPrimerNumReturn(7002467);
		
		this.opt.setPrimerMinTm(57.0);
		this.opt.setPrimerOptTm(60.0);
		this.opt.setPrimerMaxTm(63.0);
		this.opt.setPrimerMaxDiffTm(3.0);
		
		this.opt.setSearchSpecificPrimer(true);
		this.opt.setPrimerSpecificityDatabase(RemotePrimerBlastNCBI.PrimerBlastOptions.Database.nt);
		this.opt.setOrganism("29760");
		
		this.opt.setPrimerSizeMin(18);
		this.opt.setPrimerSizeOpt(20);
		this.opt.setPrimerSizeMax(22);
		
		this.opt.setGCMin(40);
		this.opt.setGCMax(60);
		
	}
	
	public void plopynounetnounetnounet(){
		String seqRevComp = (new DNASequence(this.sequence.getResidues())).getReverseComplement().getSequenceAsString();
		
		this.primerSet = new PrimerSet();
		
		// TODO : modify this code to treat forward and reverse separately
		
		FeatureSetI level0 = cs.getResults().getForwardSet();
		for(int i = 0; i < level0.size(); i++) {
			SeqFeatureI level1 = level0.deleteFeatureAt(i);
			
			//For each plus strand primer couple
			for(int j = 0; j < level1.size(); j++) {
				SeqFeatureI level2 = level1.getFeatureAt(j);
				
				// TODO : instantiate a primer couple
							
				//For each plus strand primer
				for(int k = 0; k < level2.size(); k++) {
					SeqFeatureI level3 = level2.getFeatureAt(k);
						
					String name = level3.getName();
					String strand = "";
					if(name.contains("forward")) {
						strand = this.sequence.getResidues();
					}
					else {
						strand = seqRevComp;
					}
					
//					System.out.print(level3.getName()+"\t");
//					System.out.print(level3.getStart()+"-"+level3.getEnd()+"\t");
					
					String hybridSite = strand.substring(level3.getStart(), level3.getEnd());
					String primerSeq = (new DNASequence(hybridSite)).getComplement().getSequenceAsString();
										
//					System.out.print(primerSeq+"\t");
//					
//					System.out.println();
					
					Primer primer = new Primer(name, level3.getStart(), level3.getEnd(), hybridSite, primerSeq);
					
					// TODO : add this primer to the primer couple
				}
//				System.out.println();
				
				// TODO : add this primer couple to the primer set
			}
		}
	}
	
	
	
	
	
	
}
