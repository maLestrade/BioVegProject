package core.genoscope;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import core.sequence.Sequence;
import core.sequence.SequencePartType;

public class GenoscopeVitisSequenceQuery {

	private final String protAccNum; 

	public GenoscopeVitisSequenceQuery(String protAccNum) {
		this.protAccNum = protAccNum;
	}

	public Sequence getSequence() {
		try {
			String url = "http://www.genoscope.cns.fr/cgi-bin/ggb/vitis/12X/geneView?src=vitis&name=";
			//System.out.println("Numéro d'accession : "+protAccNum+"\n");

			Document doc = Jsoup.connect(url+protAccNum).get();

			Sequence seq = new Sequence();

			Elements pre = doc.select("span.sequence pre");

			List<Node> seqParts = pre.get(0).childNodes();
			if(seqParts.size() > 1) {
				int i = 1, pos = 1;
				String sequence = null;
				SequencePartType type = null;
				Node part = null;
				
				part = seqParts.get(i);
				if(part.childNodeSize() >= 1) {
					if(part.toString().contains("#A9A9A9")) {
						sequence = part.childNode(0).toString().replace("\n", "");
						type = SequencePartType.UTR5;
						pos = addSequencePart(seq, sequence, pos, type);
						i++;
					}
				}
				
				while(i < seqParts.size()-1) {
					part = seqParts.get(i);

					if (part.childNodeSize() >= 1 && part.toString().contains("#FF0000")) {
						sequence = part.childNode(0).toString().replace("\n", "");;
						type = SequencePartType.EXON;
					}
					else {
						sequence = part.toString().replace("\n", "");;
						type = SequencePartType.INTRON;
					}

					if(sequence != null) 
						pos = addSequencePart(seq, sequence, pos, type);
					i++;
				}
				
				part = seqParts.get(i);
				if(part.childNodeSize() >= 1) {
					if(part.toString().contains("#A9A9A9")) {
						sequence = part.childNode(0).toString().replace("\n", "");
						type = SequencePartType.UTR3;
						pos = addSequencePart(seq, sequence, pos, type);
						i++;
					}
				}
			}

			return seq;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}

	private int addSequencePart(Sequence seq, String sequence, int pos, SequencePartType type) {
		int end = pos + sequence.length() - 1;
		seq.add(type, sequence, pos, end);
		return end + 1;
	}
}
