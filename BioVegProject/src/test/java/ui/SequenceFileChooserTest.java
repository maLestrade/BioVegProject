package ui;

import java.io.File;

import javax.swing.JFrame;

import org.junit.Test;

import ui.filechooser.FileChooserField;
import ui.filechooser.SequenceFileChooser;

public class SequenceFileChooserTest {

	@Test
	public void testSequenceFileChooserField() {
		JFrame testWindow = new JFrame();
		
		testWindow.add(
			new FileChooserField(
				new File("."), 
				new SequenceFileChooser("select a sequence file"), 
				"select a sequence file",
				"no file selected"
			)
		);
		testWindow.pack();
		testWindow.setLocationRelativeTo(null);
		testWindow.setVisible(true);
	}
	
}
