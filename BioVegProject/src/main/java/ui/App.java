package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import ui.process.PanelProcess1;
import ui.process.PanelProcess2;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		super("Primer Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 422);
		setMinimumSize(getBounds().getSize());
		
		JLabel lblRechercheDamorces = new JLabel("PRIMER DESIGNER - VITIS VINIFERA", JLabel.CENTER);
		getContentPane().add(lblRechercheDamorces, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		PanelProcess1 process1 = new PanelProcess1(); 
		tabbedPane.add(process1);
		
		PanelProcess2 process2 = new PanelProcess2(); 
		tabbedPane.add(process2);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}

}
