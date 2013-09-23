package ui.worker;

import javax.swing.JFrame;

public class TestResult extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final String[] steps = {"Searching sequence", "Searching primers"};

		ResultWindow window = new ResultWindow(null);
		window.setVisible(true);
		ResultWorker worker = new ResultWorker(window) {
			@Override
			protected String doInBackground() throws Exception {
				setProgress(0);
				publish("searching sequence...");
				Thread.sleep(2000);
				publish("done!");
				setProgress(1);
				publish("searching primers...");
				Thread.sleep(5000);
				publish("done!");
				return "";
			}
		};
		
		window.run(worker, steps);

		worker.execute();
	}

}
