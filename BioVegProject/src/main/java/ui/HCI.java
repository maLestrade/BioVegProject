package ui;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;

public class HCI extends JFrame implements ActionListener {

	/**
	 * Pamameters
	 */
	private static final long serialVersionUID = 1L;
	private JLabel Process1Title = new JLabel("Recherche Amorces Sur le Génome du RB1",
			JLabel.CENTER);
	private JFrame f = new JFrame("PrimerDesigner");
	private JMenuItem exit = new JMenuItem("Exit");


	/**
	 * create the design of the application
	 */
	public HCI() {

		f.setLayout(new GridLayout(5, 1));
		JMenuBar menuBar = new JMenuBar(); // create the object MenuBar
		f.setJMenuBar(menuBar);

		JMenu menu1 = new JMenu("Files");// create a menu
		exit.addActionListener(this);
		menu1.add(exit);

		JMenu menu2 = new JMenu("Process Choice");
		JMenuItem process1 = new JMenuItem("Process 1");
		menu2.add(process1);
		JMenuItem process2 = new JMenuItem("Process 2");
		menu2.add(process2);

		menuBar.add(menu1);
		menuBar.add(menu2);

		f.add(menuBar);

		JPanel title = new JPanel();
		JLabel titleName = new JLabel("Recherche d'amorces", JLabel.CENTER);
		title.add(titleName);
		f.add(title);

		JPanel recherche = new JPanel();
		recherche.setLayout(new GridLayout(1, 4));
		JLabel numAccess = new JLabel("Num Accession : ");
		JComboBox<String> listeNumAccess = new JComboBox<>();
		recherche.add(new JPanel());
		recherche.add(numAccess);
		recherche.add(listeNumAccess);
		recherche.add(new JPanel());
		f.add(recherche);

//		final JFileChooser fc = new JFileChooser();
//		f.add(fc);

		
		
	
		JLabel paramTitle = new JLabel("PARAMETRES", JLabel.CENTER);
		f.add(paramTitle);

		JPanel param = new JPanel();
		param.setLayout(new GridLayout(2,1));
		param.add(paramTitle);
		param.add(new JPanel());
		JPanel paramOpt = new JPanel();
		paramOpt.setLayout(new GridLayout(5,4));
		paramOpt.add(new JPanel());
		paramOpt.add(new JLabel("min", JLabel.CENTER));
		paramOpt.add(new JLabel("opt", JLabel.CENTER));
		paramOpt.add(new JLabel("max", JLabel.CENTER));
		paramOpt.add(new JLabel("Length"));
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JLabel("Tm"));
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JLabel("GC%"));
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JLabel("SelfComplement"));
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		paramOpt.add(new JTextField());
		param.add(paramOpt);
		f.add(param);
		
		
		
		JButton lancer = new JButton("LANCER");
		f.add(lancer);


		f.setSize(150, 150);
		f.pack();
		f.setVisible(true);

	}

	/**
	 * give the action which corresponds to the good event e is the entrance
	 * action Event
	 */
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == exit){
			 System.exit(0);
		 }
	}

}
