package core.sequence;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAnnotatedSequence {

	@Test
	public void test() {
		AnnotatedSequence as = new AnnotatedSequence();

		as.add(SequencePartType.UTR5, "atcgatcga", 1, 9);
		as.add(SequencePartType.EXON, "AAAGGGCCCTTTAAACCCGGGTTTT", 10, 34);
		as.add(SequencePartType.INTRON, "tagctagctagctagctagctagat", 35, 59);
		as.add(SequencePartType.EXON, "AAAGGGCCCTTTAAACCCGGGTTTG", 60, 84);
		
		System.out.println("taille amplicon: " + (as.getSequence().length() + 1));

		//String strAmplicon = "cgatcgaaaagggccct";
                String strAmplicon = "GGGTTTTtagctagctagctagctagctagatAAAGGGCCC";

		AnnotatedSequence annotAmplicon = new AnnotatedSequence();
		annotAmplicon = as.annotateAmplicon(strAmplicon);
                
                System.out.println("ici ça balance");
                System.out.println(annotAmplicon.toString());
                System.out.println("fini");
                
                System.out.println(as.getLocType(59));
            

	}

}
