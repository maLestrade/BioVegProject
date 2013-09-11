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
	
	private Sequence parentSequence;
	private Sequence subSequence;
	private AdvancedPrimerBlastOptions opt;
	private AdvancedPrimerBlast rp;
	private CurationSet cs;
	private PrimerSet primerSet;
	private Integer lastSubsequenceSize;
	private Double complementarityAny;
	private Double complementarityEnd;
	
	public VitisPrimerQuery(String numAcc, String seq) {
		this.initAdvancedPrimerBlastOptions();
		this.rp = new AdvancedPrimerBlast(opt);
		this.parentSequence = new Sequence(numAcc, seq);
		this.subSequence = new Sequence(numAcc, seq.length()>1000?seq.substring(seq.length()-1000, seq.length()):seq);
	}
	
	public VitisPrimerQuery(String numAcc, String seq, Integer lastSubsequenceSize, Double complementarityAny, Double complementarityEnd, AdvancedPrimerBlastOptions opt) {
		this.initAdvancedPrimerBlastOptions(lastSubsequenceSize, complementarityAny, complementarityEnd, opt);
		this.rp = new AdvancedPrimerBlast(opt);
		this.parentSequence = new Sequence(numAcc, seq);
		this.subSequence = new Sequence(numAcc, seq.length()>1000?seq.substring(seq.length()-1000, seq.length()):seq);
	}
	
	private void initAdvancedPrimerBlastOptions(Integer lastSubsequenceSize, Double complementarityAny, Double complementarityEnd, AdvancedPrimerBlastOptions opt) {
		// GENERAL OPTIONS
		this.lastSubsequenceSize = lastSubsequenceSize;
		this.complementarityAny = complementarityAny;
		this.complementarityEnd = complementarityEnd;
		
		// PRIMERBLAST OPTIONS
		this.opt = opt;
	}
	
	private void initAdvancedPrimerBlastOptions() {		
		// GENERAL OPTIONS
		this.lastSubsequenceSize = 1000;
		this.complementarityAny = 6.0;
		this.complementarityEnd = 6.0;
		
		// PRIMERBLAST OPTIONS
		this.opt = new AdvancedPrimerBlastOptions();
		
		this.opt.setPrimerProductMin(300);
		this.opt.setPrimerProductMax(400);
		
		this.opt.setPrimerNumReturn(30);
		
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
		
		this.opt.setGCMin(40d);
		this.opt.setGCMax(60d);
		this.opt.setMaxGCEnd(2);
		
		this.opt.setThAlignment(true);
		this.opt.setThSelfAny(30.0);
		this.opt.setThSelfEnd(30.0);
		this.opt.setThPairAny(30.0);
		this.opt.setThPairEnd(30.0);
		this.opt.setMaxHairpin(40.0);
		
	}
	
	public void runAnalysis() throws Exception {
		this.cs = new CurationSet();
		cs.setResults(new StrandedFeatureSet());
		cs.setRefSequence(this.subSequence);
		
		rp.runAnalysis(cs, this.subSequence, 1);
		
		this.retrievePrimers();
	}
	
	private void retrievePrimers(){
		
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
				String strand = this.subSequence.getResidues();
				String hybridSite = strand.substring(level3Forward.getStart()-2, level3Forward.getEnd()-1);
				
				Integer start = level3Forward.getStart()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
				Integer end = level3Forward.getEnd()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
				
				// TODO : Do not save primer if a value is not inside the chosen scale
				if (level3Forward.getSelfCompAny() > this.complementarityAny || level3Forward.getSelfCompEnd() > this.complementarityEnd) {
					continue;
				}
				
				Primer primerForward = new Primer(level3Forward.getName(), start, end, hybridSite, level3Forward.getTm(), level3Forward.getGc(), level3Forward.getSelfCompAny(), level3Forward.getSelfCompEnd());

								
				// Reverse primer
				SeqFeatureI level3Reverse = level2Reverse.getFeatureAt(1);
				strand = (new DNASequence(this.subSequence.getResidues())).getComplement().getSequenceAsString();
				hybridSite = (new DNASequence(strand.substring(level3Reverse.getEnd()-2, level3Reverse.getStart()-1))).getReverse().getSequenceAsString();

				start = level3Reverse.getStart()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
				end = level3Reverse.getEnd()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;

				if (level3Reverse.getSelfCompAny() > this.complementarityAny || level3Reverse.getSelfCompEnd() > this.complementarityEnd) {
					continue;
				}
				Primer primerReverse = new Primer(level3Reverse.getName(), start, end, hybridSite, level3Reverse.getTm(), level3Reverse.getGc(), level3Reverse.getSelfCompAny(), level3Reverse.getSelfCompEnd());
				
				this.primerSet.addPrimerCouple(primerForward, primerReverse);
			}
		}
	}

	public Sequence getParentSequence() {
		return parentSequence;
	}

	public void setParentSequence(Sequence parentSequence) {
		this.parentSequence = parentSequence;
	}

	public Sequence getSubSequence() {
		return subSequence;
	}

	public void setSubSequence(Sequence subSequence) {
		this.subSequence = subSequence;
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

	public Integer getLastSubsequenceSize() {
		return lastSubsequenceSize;
	}

	public void setLastSubsequenceSize(Integer lastSubsequenceSize) {
		this.lastSubsequenceSize = lastSubsequenceSize;
	}

	public Double getComplementarityAny() {
		return complementarityAny;
	}

	public void setComplementarityAny(Double complementarityAny) {
		this.complementarityAny = complementarityAny;
	}

	public Double getComplementarityEnd() {
		return complementarityEnd;
	}

	public void setComplementarityEnd(Double complementarityEnd) {
		this.complementarityEnd = complementarityEnd;
	}
	
	
	
}
