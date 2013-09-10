package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JTextArea;


import ui.filechooser.FileChooserField;
import ui.filechooser.SequenceFileChooser;
import java.awt.Font;


public class Process1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public Process1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRechercheDamorces = new JLabel("PRIMER DESIGNER - VITIS VINIFERA", JLabel.CENTER);
		lblRechercheDamorces.setBounds(0, 5, 674, 14);
		lblRechercheDamorces.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRechercheDamorces);
		
		JLabel lblAccessNumber = new JLabel("Access Number : ");
		lblAccessNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccessNumber.setBounds(10, 62, 95, 20);
		contentPane.add(lblAccessNumber);
		
		FileChooserField chooseFileAccessNumber = 
				new FileChooserField(
						new File("."), 
						new SequenceFileChooser("select a sequence file"), 
						"select a sequence file",
						"no file selected"
					);
		chooseFileAccessNumber.setBounds(182, 45, 167, 50);
		contentPane.add(chooseFileAccessNumber);
		
		JPanel panel = new JPanel();
		panel.setBounds(230, 122, 350, 84);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 4, 2, 0));
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMin);
		
		JLabel lblOpt = new JLabel("Opt");
		lblOpt.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblOpt);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMax);
		
		JLabel lblLength = new JLabel("Length ");
		panel.add(lblLength);
		
		JTextField label_1 = new JTextField("");
		panel.add(label_1);
		
		JTextField label_2 = new JTextField("");
		panel.add(label_2);
		
		JTextField label_3 = new JTextField("");
		panel.add(label_3);
		
		JLabel lblTm = new JLabel("Tm");
		panel.add(lblTm);
		
		JTextField label_4 = new JTextField("");
		panel.add(label_4);
		
		JTextField label_5 = new JTextField("");
		panel.add(label_5);
		
		JTextField label_6 = new JTextField("");
		panel.add(label_6);
		
		JLabel lblGc = new JLabel("GC%");
		panel.add(lblGc);
		
		JTextField label_7 = new JTextField("");
		panel.add(label_7);
		
		JTextField label_8 = new JTextField("");
		panel.add(label_8);
		
		JTextField label_9 = new JTextField("");
		panel.add(label_9);
		
		JLabel Complement = new JLabel("SelfComplement");
		panel.add(Complement);
		
		JLabel label_10 = new JLabel("");
		panel.add(label_10);
		
		JTextField label_11 = new JTextField("");
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		panel.add(label_12);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 250, 664, 257);
		contentPane.add(textArea);
		
		JLabel lblResults = new JLabel("Results : ");
		lblResults.setBounds(10, 225, 100, 20);
		contentPane.add(lblResults);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(401, 65, 33, 14);
		contentPane.add(lblOr);
		
		textField = new JTextField();
		textField.setBounds(479, 65, 142, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblParameters = new JLabel("PARAMETERS");
		lblParameters.setBounds(10, 122, 135, 14);
		contentPane.add(lblParameters);
	}
}
