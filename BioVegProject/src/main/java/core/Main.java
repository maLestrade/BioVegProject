package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;

import org.biojava3.core.sequence.DNASequence;

import apollo.analysis.RemotePrimerBlastNCBI;
import apollo.datamodel.CurationSet;
import apollo.datamodel.FeatureSetI;
import apollo.datamodel.SeqFeatureI;
import apollo.datamodel.Sequence;
import apollo.datamodel.StrandedFeatureSet;

public class Main {
	
	static File file = new File("CurationSet_test");

	public static void main(String[] args) throws Exception {
		String seq = "atacagccatctttctggtttttccctctctctctctctctctcatttaaccatggctgagactgttattgaaatccccatggctgttccgataaagaaggcaaggaccagcaggaaggcgctgaaggagaagagctcttcaacgaataaggcaaacatcttggccggacagatctcagagtcctctccggcgccagttccgacgccgtctgaggacgccggaaaggagaaccacgagagcctgtcacagcctctgtccgggaagaagaagagtaaaggggctcagaaagggaagaaatcgaaggagtcccagtcgtttgagagggacttgcaagaaatgcaggaaaagcttgagcaattgcggcttgagaaggagaagacggaggagttattgaaggctagagacgagatgttgaagatcaaggaggaagagctcgagacaaggggtcgagagcaggagaagcttcagatggagttgaagaaattgcagaagttgaaggagttcaaacccaccgtggttcgttttcttgatttctttctccattcccccccccccccccctttttttgtgtttggtgtccaagaatattgaagtgtggaaaaagtaattttgaattttatggtttaatggatatttctttattggaatctttcttgatattctttttaatttccttttctttccagcgtttttactagcaaacaaccagactattaattcctttgcatatctgggttttaagtattagatttcattttcttgaccttcttcagattctttcttaagtttgttagttttttttttcttccattttattagcaaccaaacagaccattgtgattcttgatttctgctttcaagggtttgatttgatttttatcggtctttctttggatattcccttttaatttcactttcctgatctttctttcagacatttccacttcactctctgagagataaagaacaagaaaagaaggagaagaacaaaaagggctgcccagaaacgaaaaggccatctccgtcatatgtactctggtgcaaggatcagtggaatgaggtaaatgagagaaaataggttccgaagaagaaattgaatttgactccaaaacctagaaattcatctgtatttttgaacgtaggccaagaaagcaaaccccgatgcagacttcaaggagatctcaaatattttgggggctaaatggaagacaatcagcgcagaagagaagaagccatatgaggagaagtatcaggctgagaaggaagcctatttgcagatagtggggaaggagaagcgcgaaaatgaagccatgaggctgttggaagaggagcagaagcagaagacagctatggagttgcttgagcaatatctccagttcaaacagggagcagaaaaggagaacaagaagaaaaagtaaagccccattaatctccccccttcctctgttccattctgaaaattttctctttttttttagaaaaggacccgctaatctccctcgcttcctctctgcttctctctaatttttttctcaactcttttacaggaaagagaaggacccattgaaaccgaagcatcctgtatcagcattttttttgttctcaaaggaaaggcgagcagctctgcttggagaggataagaatgttttagaggtagctgatgattagaatctgggtggttttttttttttttttttctctcttggaattcgtttctactgatttgagctaatttgaaggttgggtattttgatagattgccaagattgctggtgaagagtggaagaacatgacggagaagcagaaacgcccttacgaagaggttcatctcatgacctctctgtttctgtctttgtttctctctgcagcttggtctcttacaatttccatcgaaaacttgcagattgcaaagaaaaataaggcaaagtatcaggaagaaatggagttgtacaaacagcagaaggatgaagaagctgaagatctcaagaagggagaggaggagcagatgaaaatccagaaacatgaagcattacaactgctcaagaagaaagaaaaaaccgaaaatataatcaaggttcttcaacgttgtcaccataaatttgtgggtattgttggtttcaaaacttattcagcagctaatctgtggtttgaatatagaaaaccaaggagaatcgccagaagaagaagaagcagaaggaaaaggccaactctgatccaaacaagcctaagaagccggcatcctcattcctcctattcaggtatatatcaatcagggatatttttcgggattttctggctttttagattatgattcaataaggaaattgttcttgttctctgcagcaaagaagcaaggaatagtttcctgcaagagcgaccaggaataaacaattccaccctcaatgctctgatttcagtgaaatggaaggtattatcatttctcctttctcttttggagtgtgacattgagaatttctgtaagtttctatttgcaaatttacccatttgggccgttttgtacaggagttggatgaggaagagaggaaaatctggaatgacaaagctaaggaagctatggaagcataccagaaggaactagaggaatataacaagtctgctgcaaccatatctgacaagccacagcaatgaagaatgtgaagtgctgttgatgttcaagtggttatgttgaacataaagtagaacgtctggccttcaagttcactggcccttttttggttgatcagtattctgttatcttgcaatttcttaggatgtttttgttgccaactgatggaaaatctgcaaaaaattctcacatgttgcgagtcttgagattgactgattgttggaaaaagcaaattccaaaaattccaattctggattttccctttcattaatgaaatctaaatggcccttgcctacgtttccatt";
		String seqRevComp = (new DNASequence(seq)).getReverseComplement().getSequenceAsString();
		
		CurationSet cs = null;
		if(!file.exists()) {
			cs = save();
		}
		else {
			cs = load();
		}
		
		System.out.println("forward");
		
		FeatureSetI level0 = cs.getResults().getForwardSet();
		for(int i = 0; i < level0.size(); i++) {
			SeqFeatureI level1 = level0.deleteFeatureAt(i);
			
			//For each plus strand primer couple
			for(int j = 0; j < level1.size(); j++) {
				SeqFeatureI level2 = level1.getFeatureAt(j);

//				System.out.print("level2 : \t");
//				System.out.println(level2.getStart()+"-"+level2.getEnd());
				
				//For each plus strand primer
				for(int k = 0; k < level2.size(); k++) {
					SeqFeatureI level3 = level2.getFeatureAt(k);
						
					String name = level3.getName();
					String strand = "";
					if(name.contains("forward")) {
						strand = seq;
					}
					else {
						strand = seqRevComp;
					}
					
					System.out.print(level3.getName()+"\t");
					System.out.print(level3.getStart()+"-"+level3.getEnd()+"\t");
					String hybridSite = strand.substring(level3.getStart(), level3.getEnd());
					String primerSeq = (new DNASequence(hybridSite)).getComplement().getSequenceAsString();
					
					System.out.print(primerSeq+"\t");

					System.out.print(level3.getProperties().size());
					Iterator it = level3.getProperties().entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry entry = (Map.Entry) it.next();
						System.out.print(entry.getKey()+"-"+entry.getValue());
					}
					
					System.out.println();
				}
				System.out.println();
			}
		}
		
		/*
		System.out.println("\nreverse");
		level0 = cs.getResults().getReverseSet();
		for(int i = 0; i < level0.size(); i++) {
			SeqFeatureI level1 = level0.deleteFeatureAt(i);
			
			for(int j = 0; j < level1.size(); j++) {
				SeqFeatureI level2 = level1.getFeatureAt(j);

				for(int k = 0; k < level2.size(); k++) {
					SeqFeatureI level3 = level2.getFeatureAt(k);
						
					System.out.print(level3.getName()+"\t");
					System.out.print(level3.getStart()+"-"+level3.getEnd()+"\t");
					//System.out.println(seqRevComp.substring(level3.getEnd(), level3.getStart()));
				}
				System.out.println();
			}
		}
		*/
		
		System.out.println("toto");
	}

	
	public static CurationSet save() throws Exception {
		
		RemotePrimerBlastNCBI.PrimerBlastOptions opt = new RemotePrimerBlastNCBI.PrimerBlastOptions();
		opt.setPrimerOptTm(60.0);
		opt.setPrimerMaxDiffTm(3.0);
		opt.setPrimerProductMin(300);
		opt.setPrimerProductMax(400);
		opt.setSearchSpecificPrimer(true);
		opt.setPrimerSpecificityDatabase(RemotePrimerBlastNCBI.PrimerBlastOptions.Database.nt);
		opt.setOrganism("29760");
		
		RemotePrimerBlastNCBI rp = new RemotePrimerBlastNCBI(opt);

		Sequence seq = new Sequence("GSVIVT01019504001", "atacagccatctttctggtttttccctctctctctctctctctcatttaaccatggctgagactgttattgaaatccccatggctgttccgataaagaaggcaaggaccagcaggaaggcgctgaaggagaagagctcttcaacgaataaggcaaacatcttggccggacagatctcagagtcctctccggcgccagttccgacgccgtctgaggacgccggaaaggagaaccacgagagcctgtcacagcctctgtccgggaagaagaagagtaaaggggctcagaaagggaagaaatcgaaggagtcccagtcgtttgagagggacttgcaagaaatgcaggaaaagcttgagcaattgcggcttgagaaggagaagacggaggagttattgaaggctagagacgagatgttgaagatcaaggaggaagagctcgagacaaggggtcgagagcaggagaagcttcagatggagttgaagaaattgcagaagttgaaggagttcaaacccaccgtggttcgttttcttgatttctttctccattcccccccccccccccctttttttgtgtttggtgtccaagaatattgaagtgtggaaaaagtaattttgaattttatggtttaatggatatttctttattggaatctttcttgatattctttttaatttccttttctttccagcgtttttactagcaaacaaccagactattaattcctttgcatatctgggttttaagtattagatttcattttcttgaccttcttcagattctttcttaagtttgttagttttttttttcttccattttattagcaaccaaacagaccattgtgattcttgatttctgctttcaagggtttgatttgatttttatcggtctttctttggatattcccttttaatttcactttcctgatctttctttcagacatttccacttcactctctgagagataaagaacaagaaaagaaggagaagaacaaaaagggctgcccagaaacgaaaaggccatctccgtcatatgtactctggtgcaaggatcagtggaatgaggtaaatgagagaaaataggttccgaagaagaaattgaatttgactccaaaacctagaaattcatctgtatttttgaacgtaggccaagaaagcaaaccccgatgcagacttcaaggagatctcaaatattttgggggctaaatggaagacaatcagcgcagaagagaagaagccatatgaggagaagtatcaggctgagaaggaagcctatttgcagatagtggggaaggagaagcgcgaaaatgaagccatgaggctgttggaagaggagcagaagcagaagacagctatggagttgcttgagcaatatctccagttcaaacagggagcagaaaaggagaacaagaagaaaaagtaaagccccattaatctccccccttcctctgttccattctgaaaattttctctttttttttagaaaaggacccgctaatctccctcgcttcctctctgcttctctctaatttttttctcaactcttttacaggaaagagaaggacccattgaaaccgaagcatcctgtatcagcattttttttgttctcaaaggaaaggcgagcagctctgcttggagaggataagaatgttttagaggtagctgatgattagaatctgggtggttttttttttttttttttctctcttggaattcgtttctactgatttgagctaatttgaaggttgggtattttgatagattgccaagattgctggtgaagagtggaagaacatgacggagaagcagaaacgcccttacgaagaggttcatctcatgacctctctgtttctgtctttgtttctctctgcagcttggtctcttacaatttccatcgaaaacttgcagattgcaaagaaaaataaggcaaagtatcaggaagaaatggagttgtacaaacagcagaaggatgaagaagctgaagatctcaagaagggagaggaggagcagatgaaaatccagaaacatgaagcattacaactgctcaagaagaaagaaaaaaccgaaaatataatcaaggttcttcaacgttgtcaccataaatttgtgggtattgttggtttcaaaacttattcagcagctaatctgtggtttgaatatagaaaaccaaggagaatcgccagaagaagaagaagcagaaggaaaaggccaactctgatccaaacaagcctaagaagccggcatcctcattcctcctattcaggtatatatcaatcagggatatttttcgggattttctggctttttagattatgattcaataaggaaattgttcttgttctctgcagcaaagaagcaaggaatagtttcctgcaagagcgaccaggaataaacaattccaccctcaatgctctgatttcagtgaaatggaaggtattatcatttctcctttctcttttggagtgtgacattgagaatttctgtaagtttctatttgcaaatttacccatttgggccgttttgtacaggagttggatgaggaagagaggaaaatctggaatgacaaagctaaggaagctatggaagcataccagaaggaactagaggaatataacaagtctgctgcaaccatatctgacaagccacagcaatgaagaatgtgaagtgctgttgatgttcaagtggttatgttgaacataaagtagaacgtctggccttcaagttcactggcccttttttggttgatcagtattctgttatcttgcaatttcttaggatgtttttgttgccaactgatggaaaatctgcaaaaaattctcacatgttgcgagtcttgagattgactgattgttggaaaaagcaaattccaaaaattccaattctggattttccctttcattaatgaaatctaaatggcccttgcctacgtttccatt");

		CurationSet cs = new CurationSet();
		cs.setResults(new StrandedFeatureSet());
		cs.setRefSequence(seq);
		
		rp.runAnalysis(cs, seq, 1);
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(cs);
		out.close();
		
		return cs;
	}
	
	public static CurationSet load() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		CurationSet cs = (CurationSet)in.readObject();
		return cs;
	}
}
