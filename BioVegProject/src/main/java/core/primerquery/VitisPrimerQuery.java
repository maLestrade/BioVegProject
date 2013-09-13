package core.primerquery;

import org.biojava3.core.sequence.DNASequence;
import apollo.analysis.RemotePrimerBlastNCBI;
import apollo.datamodel.CurationSet;
import apollo.datamodel.FeatureSetI;
import apollo.datamodel.SeqFeatureI;
import apollo.datamodel.Sequence;
import apollo.datamodel.StrandedFeatureSet;
import core.primer.Primer;
import core.primer.PrimerCouple;
import core.primer.PrimerSet;
import core.primerblast.AdvancedPrimerBlast;
import core.primerblast.AdvancedPrimerBlastOptions;
import java.lang.Math;
import java.util.Collections;

/**
 * Class which design primers using primerblast and sort the result list
 * @author pidupuis, gcornut
 *
 */
public class VitisPrimerQuery {
	
	/**
	 * Full gene sequence from genoscope
	 */
	private Sequence parentSequence;
	/**
	 * Subsequence which is located at the end of the original sequence
	 */
	private Sequence subSequence;
	/**
	 * PrimerBlast options
	 */
	private AdvancedPrimerBlastOptions opt;
	/**
	 * PrimerBlast request
	 */
	private AdvancedPrimerBlast rp;
	/**
	 * PrimerBlast results
	 */
	private CurationSet cs;
	/**
	 * List of primer couples
	 */
	private PrimerSet primerSet;
	/**
	 * Size of the subsequence
	 */
	// TODO: put the size as proportion instead of number
	private Integer lastSubsequenceSize;
	/**
	 * Max self complementarity any
	 */
	private Double complementarityAny;
	/**
	 * Max self complementarity end
	 */
	private Double complementarityEnd;
	
	/**
	 * Constructor which use default PrimerBlast options
	 * @param numAcc
	 * @param seq
	 */
	public VitisPrimerQuery(String numAcc, String seq) {
		this.initAdvancedPrimerBlastOptions();
		this.rp = new AdvancedPrimerBlast(opt);
		this.parentSequence = new Sequence(numAcc, seq);
		this.subSequence = new Sequence(numAcc, seq.length()>1000?seq.substring(seq.length()-1000, seq.length()):seq);
	}
	
	/**
	 * Constructor which use the PrimerBlast options chosen by the user
	 * @param numAcc
	 * @param seq
	 * @param lastSubsequenceSize
	 * @param complementarityAny
	 * @param complementarityEnd
	 * @param opt
	 */
	public VitisPrimerQuery(String numAcc, String seq, Integer lastSubsequenceSize, Double complementarityAny, Double complementarityEnd, AdvancedPrimerBlastOptions opt) {
		this.initAdvancedPrimerBlastOptions(lastSubsequenceSize, complementarityAny, complementarityEnd, opt);
		this.rp = new AdvancedPrimerBlast(opt);
		this.parentSequence = new Sequence(numAcc, seq);
		this.subSequence = new Sequence(numAcc, seq.length()>1000?seq.substring(seq.length()-1000, seq.length()):seq);
	}
	
	/**
	 * Initialize the PrimerBlast options
	 * @param lastSubsequenceSize
	 * @param complementarityAny
	 * @param complementarityEnd
	 * @param opt
	 */
	private void initAdvancedPrimerBlastOptions(Integer lastSubsequenceSize, Double complementarityAny, Double complementarityEnd, AdvancedPrimerBlastOptions opt) {
		// GENERAL OPTIONS
		this.lastSubsequenceSize = lastSubsequenceSize;
		this.complementarityAny = complementarityAny;
		this.complementarityEnd = complementarityEnd;
		
		// PRIMERBLAST OPTIONS
		this.opt = opt;
	}
	
	/**
	 * Initialize the PrimerBlast options with default values
	 */
	private void initAdvancedPrimerBlastOptions() {		
		// GENERAL OPTIONS
		this.lastSubsequenceSize = 1000;
		this.complementarityAny = 6.0;
		this.complementarityEnd = 6.0;
		
		// PRIMERBLAST OPTIONS
		this.opt = new AdvancedPrimerBlastOptions();
		
		this.opt.setPrimerProductMin(300);
		this.opt.setPrimerProductMax(450);
		
		this.opt.setPrimerNumReturn(30);
		
		this.opt.setPrimerMinTm(57d);
		this.opt.setPrimerOptTm(60d);
		this.opt.setPrimerMaxTm(63d);
		this.opt.setPrimerMaxDiffTm(3d);
		
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
		this.opt.setThSelfAny(30d);
		this.opt.setThSelfEnd(30d);
		this.opt.setThPairAny(30d);
		this.opt.setThPairEnd(30d);
		this.opt.setMaxHairpin(40d);
		
	}
	
	/**
	 * Run PrimerBlast analysis and retrieve the primer couples
	 * @throws Exception
	 */
	public void runAnalysis() throws Exception {
		this.cs = new CurationSet();
		cs.setResults(new StrandedFeatureSet());
		cs.setRefSequence(this.subSequence);
		
		rp.runAnalysis(cs, this.subSequence, 1); // Launch the PrimerBlast request
		
		this.retrievePrimers(); // Retrieve the primers
		this.sortPrimers(); // Sort the primer couple list
	}
	
	/**
	 * Method which retrieve the primer couple
	 */
	private void retrievePrimers(){
		
		this.primerSet = new PrimerSet();
		
		FeatureSetI level0Forward = cs.getResults().getForwardSet();
		FeatureSetI level0Reverse = cs.getResults().getReverseSet();
		
		for(int i = 0; i < level0Forward.size(); i++) {
			SeqFeatureI level1Forward = level0Forward.deleteFeatureAt(i);
			SeqFeatureI level1Reverse = level0Reverse.deleteFeatureAt(i);
			
			// FOR EACH PRIMER COUPLE
			for(int j = 0; j < level1Forward.size(); j++) {
				SeqFeatureI level2Forward = level1Forward.getFeatureAt(j);
				SeqFeatureI level2Reverse = level1Reverse.getFeatureAt(j);
				
				Primer primerForward;
				Primer primerReverse;
				
				{ // FORWARD PRIMER
					SeqFeatureI level3Forward = level2Forward.getFeatureAt(0);
					String strand = this.subSequence.getResidues();
					String hybridSite = strand.substring(level3Forward.getStart()-2, level3Forward.getEnd()-1);
					
					Integer start = level3Forward.getStart()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
					Integer end = level3Forward.getEnd()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
					
					// TODO : Do not save primer if a value is not inside the chosen scale
					if (level3Forward.getSelfCompAny() > this.complementarityAny || level3Forward.getSelfCompEnd() > this.complementarityEnd) { // Does not keep primer if the self values are big
						continue;
					}
					primerForward = new Primer(level3Forward.getName(), start, end, hybridSite, level3Forward.getTm(), level3Forward.getGc(), level3Forward.getSelfCompAny(), level3Forward.getSelfCompEnd());
				}
				
				{ // REVERSE PRIMER
					SeqFeatureI level3Reverse = level2Reverse.getFeatureAt(1);
					String strand = (new DNASequence(this.subSequence.getResidues())).getComplement().getSequenceAsString();
					String hybridSite = (new DNASequence(strand.substring(level3Reverse.getEnd()-2, level3Reverse.getStart()-1))).getReverse().getSequenceAsString();
	
					Integer start = level3Reverse.getStart()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
					Integer end = level3Reverse.getEnd()+this.parentSequence.getResidues().length()-this.lastSubsequenceSize;
	
					if (level3Reverse.getSelfCompAny() > this.complementarityAny || level3Reverse.getSelfCompEnd() > this.complementarityEnd) { // Does not keep primer if the self values are big
						continue;
					}
					primerReverse = new Primer(level3Reverse.getName(), start, end, hybridSite, level3Reverse.getTm(), level3Reverse.getGc(), level3Reverse.getSelfCompAny(), level3Reverse.getSelfCompEnd());
				}
				
				this.primerSet.addPrimerCouple(primerForward, primerReverse);
			}
		}
	}
	
	/**
	 * Method which sort the primer couple list
	 */
	private void sortPrimers() {
		
		for (PrimerCouple couple : this.primerSet.getPrimerCouples()) {
			
			Primer forward = couple.getForward();
			Primer reverse = couple.getReverse();
			
			// Subjective score which is good when near 0. It is calculating as following :
			// |Tm1-Tm2| + |µ(Tm1-Tm2)-60| + |GC1-GC2| + |µ(GC1-GC2)-50| + self1 + self3'1 + self2 + self3'2
			Double score = 
					Math.abs(forward.getTm()-reverse.getTm())
					+ Math.abs(getMean(forward.getTm(),reverse.getTm())-60.0)
					+ Math.abs(forward.getGc()-reverse.getGc())
					+ Math.abs(getMean(forward.getGc(),reverse.getGc())-50.0)
					+ forward.getSelfCompAny()
					+ forward.getSelfCompEnd()
					+ reverse.getSelfCompAny()
					+ reverse.getSelfCompEnd();
			
			couple.setScore(score);
		}
		Collections.sort(this.primerSet.getPrimerCouples());
	}
	
	/**
	 * Method which calculate the mean of several double values
	 * @param numberList
	 * @return mean : Double
	 */
	private Double getMean(Double... numberList) {
	    Double total=0.0;
	    for (Double d: numberList) {
	        total += d;
	    }
	    return total / (numberList.length);
	}

	/**
	 * 
	 * @return parentSequence : Sequence
	 */
	public Sequence getParentSequence() {
		return parentSequence;
	}

	/**
	 * 
	 * @param parentSequence : Sequence
	 */
	public void setParentSequence(Sequence parentSequence) {
		this.parentSequence = parentSequence;
	}

	/**
	 * 
	 * @return subSequence : Sequence
	 */
	public Sequence getSubSequence() {
		return subSequence;
	}

	/**
	 * 
	 * @param subSequence : Sequence
	 */
	public void setSubSequence(Sequence subSequence) {
		this.subSequence = subSequence;
	}

	/**
	 * 
	 * @return opt : AdvancedPrimerBlastOptions
	 */
	public AdvancedPrimerBlastOptions getOpt() {
		return opt;
	}

	/**
	 * 
	 * @param opt : AdvancedPrimerBlastOptions
	 */
	public void setOpt(AdvancedPrimerBlastOptions opt) {
		this.opt = opt;
	}

	/**
	 * 
	 * @return primerSet : PrimerSet
	 */
	public PrimerSet getPrimerSet() {
		return primerSet;
	}

	/**
	 * 
	 * @param primerSet : PrimerSet
	 */
	public void setPrimerSet(PrimerSet primerSet) {
		this.primerSet = primerSet;
	}

	/**
	 * 
	 * @return lastSubsequenceSize : Integer
	 */
	public Integer getLastSubsequenceSize() {
		return lastSubsequenceSize;
	}

	/**
	 * 
	 * @param lastSubsequenceSize : Integer
	 */
	public void setLastSubsequenceSize(Integer lastSubsequenceSize) {
		this.lastSubsequenceSize = lastSubsequenceSize;
	}

	/**
	 * 
	 * @return complementarityAny : Double
	 */
	public Double getComplementarityAny() {
		return complementarityAny;
	}

	/**
	 * 
	 * @param complementarityAny : Double
	 */
	public void setComplementarityAny(Double complementarityAny) {
		this.complementarityAny = complementarityAny;
	}

	/**
	 * 
	 * @return complementarityEnd : Double
	 */
	public Double getComplementarityEnd() {
		return complementarityEnd;
	}

	/**
	 * 
	 * @param complementarityEnd : Double
	 */
	public void setComplementarityEnd(Double complementarityEnd) {
		this.complementarityEnd = complementarityEnd;
	}
	
	
	
}
