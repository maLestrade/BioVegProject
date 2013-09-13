package ui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Result extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JTextPane txtAResult;

	public Result(String result, JFrame parent) {
		this(parent);
		printResult(result);
	}
	
	public Result(JFrame parent) {
		setTitle("Results");
		setBounds(100, 100, 500, 500);
		
		txtAResult = new JTextPane();
		txtAResult.setContentType("text/html");
		txtAResult.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(txtAResult);
		scroll.setViewportBorder(BorderFactory.createEmptyBorder());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		getContentPane().add(scroll);
		
		setLocationRelativeTo(parent);
	}

	public void printResult(String result) {
		txtAResult.setText("<html><pre>"+result);
	}
}
