package core.primerquery;

import org.junit.BeforeClass;
import org.junit.Test;

import core.primer.Primer;
import core.primer.PrimerCouple;

public class VitisPrimerQueryTest {

	private static VitisPrimerQuery primerQuery;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		primerQuery = new VitisPrimerQuery("GSVIVT01019504001", "atacagccatctttctggtttttccctctctctctctctctctcatttaaccatggctgagactgttattgaaatccccatggctgttccgataaagaaggcaaggaccagcaggaaggcgctgaaggagaagagctcttcaacgaataaggcaaacatcttggccggacagatctcagagtcctctccggcgccagttccgacgccgtctgaggacgccggaaaggagaaccacgagagcctgtcacagcctctgtccgggaagaagaagagtaaaggggctcagaaagggaagaaatcgaaggagtcccagtcgtttgagagggacttgcaagaaatgcaggaaaagcttgagcaattgcggcttgagaaggagaagacggaggagttattgaaggctagagacgagatgttgaagatcaaggaggaagagctcgagacaaggggtcgagagcaggagaagcttcagatggagttgaagaaattgcagaagttgaaggagttcaaacccaccgtggttcgttttcttgatttctttctccattcccccccccccccccctttttttgtgtttggtgtccaagaatattgaagtgtggaaaaagtaattttgaattttatggtttaatggatatttctttattggaatctttcttgatattctttttaatttccttttctttccagcgtttttactagcaaacaaccagactattaattcctttgcatatctgggttttaagtattagatttcattttcttgaccttcttcagattctttcttaagtttgttagttttttttttcttccattttattagcaaccaaacagaccattgtgattcttgatttctgctttcaagggtttgatttgatttttatcggtctttctttggatattcccttttaatttcactttcctgatctttctttcagacatttccacttcactctctgagagataaagaacaagaaaagaaggagaagaacaaaaagggctgcccagaaacgaaaaggccatctccgtcatatgtactctggtgcaaggatcagtggaatgaggtaaatgagagaaaataggttccgaagaagaaattgaatttgactccaaaacctagaaattcatctgtatttttgaacgtaggccaagaaagcaaaccccgatgcagacttcaaggagatctcaaatattttgggggctaaatggaagacaatcagcgcagaagagaagaagccatatgaggagaagtatcaggctgagaaggaagcctatttgcagatagtggggaaggagaagcgcgaaaatgaagccatgaggctgttggaagaggagcagaagcagaagacagctatggagttgcttgagcaatatctccagttcaaacagggagcagaaaaggagaacaagaagaaaaagtaaagccccattaatctccccccttcctctgttccattctgaaaattttctctttttttttagaaaaggacccgctaatctccctcgcttcctctctgcttctctctaatttttttctcaactcttttacaggaaagagaaggacccattgaaaccgaagcatcctgtatcagcattttttttgttctcaaaggaaaggcgagcagctctgcttggagaggataagaatgttttagaggtagctgatgattagaatctgggtggttttttttttttttttttctctcttggaattcgtttctactgatttgagctaatttgaaggttgggtattttgatagattgccaagattgctggtgaagagtggaagaacatgacggagaagcagaaacgcccttacgaagaggttcatctcatgacctctctgtttctgtctttgtttctctctgcagcttggtctcttacaatttccatcgaaaacttgcagattgcaaagaaaaataaggcaaagtatcaggaagaaatggagttgtacaaacagcagaaggatgaagaagctgaagatctcaagaagggagaggaggagcagatgaaaatccagaaacatgaagcattacaactgctcaagaagaaagaaaaaaccgaaaatataatcaaggttcttcaacgttgtcaccataaatttgtgggtattgttggtttcaaaacttattcagcagctaatctgtggtttgaatatagaaaaccaaggagaatcgccagaagaagaagaagcagaaggaaaaggccaactctgatccaaacaagcctaagaagccggcatcctcattcctcctattcaggtatatatcaatcagggatatttttcgggattttctggctttttagattatgattcaataaggaaattgttcttgttctctgcagcaaagaagcaaggaatagtttcctgcaagagcgaccaggaataaacaattccaccctcaatgctctgatttcagtgaaatggaaggtattatcatttctcctttctcttttggagtgtgacattgagaatttctgtaagtttctatttgcaaatttacccatttgggccgttttgtacaggagttggatgaggaagagaggaaaatctggaatgacaaagctaaggaagctatggaagcataccagaaggaactagaggaatataacaagtctgctgcaaccatatctgacaagccacagcaatgaagaatgtgaagtgctgttgatgttcaagtggttatgttgaacataaagtagaacgtctggccttcaagttcactggcccttttttggttgatcagtattctgttatcttgcaatttcttaggatgtttttgttgccaactgatggaaaatctgcaaaaaattctcacatgttgcgagtcttgagattgactgattgttggaaaaagcaaattccaaaaattccaattctggattttccctttcattaatgaaatctaaatggcccttgcctacgtttccatt");
//		primerQuery = new VitisPrimerQuery("GSVIVT01019413001", "tcatacaaggaacaggcactaccatcgccttcctctgcttcacatatatatatactctccctctcacactctgctaaattatatttctttttccgtggtagagggttcaagtcctgatcatacctttgaacccttctgaaaATGAAGgtgagagctttttttcctggaaaatccaagctcagtttcttagcaaaagcttggatctttctctccctgtttctgttttattgaatatgctttatattatatcttttcgctaatgggttttaaaattcatgaccccatgtgggctgtgttttttgattcatttcatgaagctgaagcatgatccttgatgggttttatatttaatcaccttttggaatctcaacttacttggattttgtttttcgtttttctttgatcctcttcatccccatgacttggatgtttgaagctacaaaatcagatctcttctggcttttctttcttgcttcaatcatttcagaggttattattatgttattcaccattcctgttcttttcatcgcagAAAGTGGTGTTGAAATTGGACTTACACGATGACAAAGCCAAGCAAAAGGCCATGAAAGCAGTGTCAAGTCTATCAGgtattgaattttctcatcacaacccctgtttgtttcaccacaaaacagaggaaaaccaaagcaggcctcggttttttattttttgggtttcttcaagcaatcacaccttcctgtcttgtgtaaaaaatttgcagGAGTCAACTCTATAGCCATGGACATGAAGGATAAGAAATTAACAGTGGTGGGAGATGTAGACCCAGTAGATATCGTGAGCAAATTAAGAAAAGGGTGGCACACCGATATTCTAACTGTTGGGCCTGCAAAGGAGGAGAAGAAGGAAGATGGAAAGAAAGACGAAGGGAAGAAAGATGAGAAGAAGGATGGAGACAAGAAGAAAGATACAGAAAAGCAGATTCAAGAGCTTGTGGATGCCTACAAAGCATACAATCCTCATCTCACCAGATATTACCATGTCCAAAGTGCAGAGGAAAATCCAAACGCTTGTGTTATATGCTGAattatcactgtatttgattttcaaaactttggagaaaatgttatagtttttatttcatggacggcacatgattccaccggaactcgcagactcgcagtgtggtgtatagtttaagaaaaatctttttaatttttgtcatgatattcaattcaagaaagtcacttttattaatatttttttcccttttttttctttttaaattttaagaatcaaactatgtctcatggttaaaatttttaaagaaataaaaaataaaaaataaatttaaagcccataaatattttataca");
	}

	@Test
	public void test() throws Exception {
		primerQuery.runAnalysis();
		
		for (PrimerCouple couple : primerQuery.getPrimerSet().getPrimerCouples()) {
			System.out.println("#############");
			
			Primer forward = couple.getForward();
			Primer reverse = couple.getReverse();
			
			System.out.println("Forward : "+forward.getName());
			System.out.println("Seq : "+forward.getHybridSite());
			System.out.println("Start : "+forward.getStart());
			System.out.println("End : "+forward.getEnd());
			System.out.println("-");
			System.out.println("Reverse : "+reverse.getName());
			System.out.println("Seq : "+reverse.getHybridSite());
			System.out.println("Start : "+reverse.getStart());
			System.out.println("End : "+reverse.getEnd());
			
		}
		System.out.println("#############");		
	}

}