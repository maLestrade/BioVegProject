package ui.worker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker.StateValue;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import ui.OnlyVerticalScrollPanel;

public class ResultWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JLabel lblProgressMessage;
	private final JProgressBar progressBar;
	private final JTextArea txtAResult;	

	public ResultWindow(JFrame parent) {
		setLayout(new MigLayout("ins 5, hidemode 2", "[grow]", "[][][grow]"));

		{ // Progress status
			this.lblProgressMessage = new JLabel("");
			lblProgressMessage.setBorder(new EmptyBorder(5,5,5,5));
			lblProgressMessage.setVisible(false);
			add(lblProgressMessage, "grow, wrap");

			this.progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
			progressBar.setVisible(false);
			add(progressBar, "grow, wrap");
		}

		{ // Text result
			txtAResult = new JTextArea();
			txtAResult.setLineWrap(true);
			txtAResult.setEditable(false);

			JScrollPane scroll = new JScrollPane(new OnlyVerticalScrollPanel(txtAResult));
			scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			add(scroll, "grow");
		}

		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(parent);
	}

	public void setProgessMessage(String text) {
		lblProgressMessage.setText(text);
	}

	public void appendResult(String line) {
		txtAResult.append(line);
	}

	public void progressDone() {
		lblProgressMessage.setVisible(false);
		progressBar.setVisible(false);
		repaint();
	}

	public void progressStart() {
		lblProgressMessage.setVisible(true);
		progressBar.setVisible(true);
		repaint();
	}

	public void run(ResultWorker worker, final String[] stepNames) {
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				//System.out.println(event);
				switch (event.getPropertyName()) {
					case "progress":
						if(stepNames != null) {
							final int step = (int) event.getNewValue();

							if(step<stepNames.length) {
								setProgessMessage(stepNames[step]);
							}
						}
						break;
					case "state":
						switch ((StateValue) event.getNewValue()) {
							case STARTED:
								setProgessMessage(stepNames[0]);
								progressStart();
							case DONE:
								progressDone();
							default: break;
						}
						break;
					default: break;
				}
				repaint();
			}
		});
	}
}
