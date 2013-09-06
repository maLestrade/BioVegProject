package genoscope;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		try {
			String url = "http://www.genoscope.cns.fr/cgi-bin/ggb/vitis/12X/geneView?src=vitis&name=";
			String acc = "GSVIVT01019504001";
			
			System.out.println("Numéro d'accession : "+acc+"\n");
			
			Document doc = Jsoup.connect(url+acc).get();
			
			Elements seq = doc.select("span.sequence pre");
			for (Node s : seq.get(0).childNodes()) {
				if (s.childNodeSize() >= 1) {
					System.out.println(s.childNode(0));
				}
				else {
					if (!(s.toString().startsWith("&gt;")))
						System.out.println(s);
				}
				System.out.println();
			}
			
			System.out.println("\nFIN");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
