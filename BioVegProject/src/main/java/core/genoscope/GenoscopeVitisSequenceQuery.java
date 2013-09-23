package core.genoscope;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import core.sequence.AnnotatedSequence;
import core.sequence.SequencePartType;

/**
 * Class which retrieve a gene sequence from the genoscope website for a protein of interest
 * @author pidupuis, gcornut
 *
 */
public class GenoscopeVitisSequenceQuery {

	/**
	 * Official accession number for the protein of interest
	 */
	private final String protAccNum; 

	/**
	 * Constructor
	 * @param protAccNum : Official accession number for the protein of interest
	 */
	public GenoscopeVitisSequenceQuery(String protAccNum) {
		this.protAccNum = protAccNum;
	}

	/**
	 * Method which return the annotated sequence for the protein of interest
	 * @return AnnotatedSequence
	 * @throws Exception
	 */
	public AnnotatedSequence getAnnotatedSequence() throws Exception {
		String url = "http://www.genoscope.cns.fr/cgi-bin/ggb/vitis/12X/geneView?src=vitis&name="; // Genoscope website url

		Document doc = Jsoup.connect(url+protAccNum).get(); // Retrieve the html page

		AnnotatedSequence seq = new AnnotatedSequence(protAccNum); // Instantiate an annotated sequence, which is a list of sequence part

		Elements pre = doc.select("span.sequence pre"); // Retrieve the tags <span class="sequence"><pre></pre></span> which contains the sequence
		List<Node> seqParts = pre.get(0).childNodes(); // Retrieve the first one, corresponding to the gene sequence
		
		if(seqParts.size() > 1) { // If there are several parts, the sequence is annotated so we can retrieve the different part
			int i = 1, pos = 1;
			String sequence = null; // Full sequence
			SequencePartType type = null; // Part type which can be 3'UTR, 5'UTR, EXON or INTRON
			Node part = null;

			// FIRST Sequence part (normally 5'UTR)
			part = seqParts.get(i);
			if(part.childNodeSize() >= 1) {
				if(part.toString().contains("#A9A9A9")) { // Compare the string color with the one corresponding to the 5'UTR to be sure that that's the 5'UTR part
					sequence = part.childNode(0).toString().replace("\n", ""); // Retrieve the sequence corresponding to this part
					type = SequencePartType.UTR5; // Indicates that this is a 5'UTR part
					pos = addSequencePart(seq, sequence, pos, type); // Save the sequence part and return the position of the last nucleotide of this sequence
					i++;
				}
			}

			// INTERMEDIATE Sequence parts (normally EXONS and INTRONS)
			while(i < seqParts.size()-1) {
				part = seqParts.get(i);

				// The EXONS are colored in the file, so they are tagged by a <span color=""></span>
				// Therefore, the EXONS have several child nodes and a color
				
				if (part.childNodeSize() >= 1 && part.toString().contains("#FF0000")) { // If there are several child node and a color, this is an EXON
					sequence = part.childNode(0).toString().replace("\n", ""); // Retrieve the sequence corresponding to this part
					type = SequencePartType.EXON; // Indicates that this is an EXON
				}
				else {
					sequence = part.toString().replace("\n", "");// Retrieve the sequence corresponding to this part
					type = SequencePartType.INTRON; // Indicates that this is an INTRON
				}

				if(sequence != null) 
					pos = addSequencePart(seq, sequence, pos, type); // Save the sequence part and return the position of the last nucleotide of this sequence
				
				i++;
			}

			// LAST Sequence part (normally 3'UTR)
			part = seqParts.get(i);
			if(part.childNodeSize() >= 1) {
				if(part.toString().contains("#A9A9A9")) { // Compare the string color with the one corresponding to the 3'UTR to be sure that that's the 3'UTR part
					sequence = part.childNode(0).toString().replace("\n", ""); // Retrieve the sequence corresponding to this part
					type = SequencePartType.UTR3;// Indicates that this is a 3'UTR part
					pos = addSequencePart(seq, sequence, pos, type); // Save the sequence part and return the position of the last nucleotide of this sequence
					i++;
				}
			}
		}

		return seq; // Return the annotated sequence
	}

	/**
	 * Method which add a sequence part to the list of sequence parts
	 * @param seq : AnnotatedSequence which is the list of sequence parts
	 * @param sequence : String
	 * @param pos : int
	 * @param type : SequencePartType which indicates if this is a 3UTR, 5UTR, EXON or INTRON
	 * @return int : the position of the last nucleotide of the sequence
	 */
	private int addSequencePart(AnnotatedSequence seq, String sequence, int pos, SequencePartType type) {
		int end = pos + sequence.length() - 1;
		seq.add(type, sequence, pos, end);
		return end + 1;
	}
}
