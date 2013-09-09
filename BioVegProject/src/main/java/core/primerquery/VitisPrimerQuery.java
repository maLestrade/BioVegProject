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
	
	public VitisPrimerQuery(Sequence sequence, AdvancedPrimerBlastOptions opt,
			AdvancedPrimerBlast rp, CurationSet cs, PrimerSet primerSet) {
		super();
		this.sequence = sequence;
		this.opt = opt;
		this.rp = rp;
		this.cs = cs;
		this.primerSet = primerSet;
	}

	public VitisPrimerQuery(String numAcc, String seq) {
		this.initAdvancedPrimerBlastOptions();
		this.rp = new AdvancedPrimerBlast(opt);
		this.sequence = new Sequence(numAcc, seq);
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
	
	public void runAnalysis() throws Exception {
		this.cs = new CurationSet();
		cs.setResults(new StrandedFeatureSet());
		cs.setRefSequence(this.sequence);
		
		rp.runAnalysis(cs, this.sequence, 1);
		
		this.retrievePrimers();
	}
	
	private void retrievePrimers(){
		// TODO : forward plus : in primer blast, the primer sequence displayed is the hybridization site
		// TODO : reverse minus : reverse or complement problem
		
		// TODO : check position of primers (2 nucleotides of the forward primer)
		
		String seqRevComp = (new DNASequence(this.sequence.getResidues())).getReverseComplement().getSequenceAsString();
		
		this.primerSet = new PrimerSet();
		
		FeatureSetI level0Forward = cs.getResults().getForwardSet();
		FeatureSetI level0Reverse = cs.getResults().getReverseSet();
		
		for(int i = 0; i < level0Forward.size(); i++) {
			SeqFeatureI level1Forward = level0Forward.deleteFeatureAt(i);
			SeqFeatureI level1Reverse = level0Reverse.deleteFeatureAt(i);
			
			// For each primer couple
			for(int j = 0; j < level1Forward.size(); j++) {
				SeqFeatureI level2Forward = level1Forward.getFeatureAt(j);
				SeqFeatureI level2Reverse = level1Reverse.getFeatureAt(j);
				
				// Forward primer
				SeqFeatureI level3Forward = level2Forward.getFeatureAt(0);
				String strand = this.sequence.getResidues();
				String hybridSite = strand.substring(level3Forward.getStart()-2, level3Forward.getEnd()-1);
				String primerSeq = (new DNASequence(hybridSite)).getComplement().getSequenceAsString();
				Primer primerForward = new Primer(level3Forward.getName(), level3Forward.getStart(), level3Forward.getEnd(), hybridSite, primerSeq);
				
				// Reverse primer
				SeqFeatureI level3Reverse = level2Reverse.getFeatureAt(1);
				strand = (new DNASequence(this.sequence.getResidues())).getComplement().getSequenceAsString();
				//hybridSite = (new DNASequence(strand.substring(level3Reverse.getEnd(), level3Reverse.getStart())) ).getReverse().getSequenceAsString();
				hybridSite = (new DNASequence(strand.substring(level3Reverse.getEnd()-2, level3Reverse.getStart()-1))).getReverse().getSequenceAsString();

				primerSeq = (new DNASequence(hybridSite)).getComplement().getSequenceAsString();
				Primer primerReverse = new Primer(level3Reverse.getName(), level3Reverse.getStart(), level3Reverse.getEnd(), hybridSite, primerSeq);
				
				this.primerSet.addPrimerCouple(primerForward, primerReverse);
			}
		}
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public AdvancedPrimerBlastOptions getOpt() {
		return opt;
	}

	public void setOpt(AdvancedPrimerBlastOptions opt) {
		this.opt = opt;
	}

	public PrimerSet getPrimerSet() {
		return primerSet;
	}

	public void setPrimerSet(PrimerSet primerSet) {
		this.primerSet = primerSet;
	}
}
