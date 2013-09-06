package ui.filechooser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Custom file chooser used to select biology sequence files
 * @author gcornut
 */
public class SequenceFileChooser extends JFileChooser {
	
	private static final long serialVersionUID = 1L;

	public SequenceFileChooser(String title) {
		super(new File(".")); // set base directory to the program directory
		
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);

		setFileFilter(new FileNameExtensionFilter(
			"Sequence files (*.fasta, *.gb, ...)",
			"fasta", "gb"
		));
	}
	
}
