package ui.process;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class PanelProcess extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelProcess(String title) {
		setName(title);
		setLayout(new BorderLayout());
		setOpaque(false);

		JScrollPane scroll = new JScrollPane(initPanel());
		scroll.setViewportBorder(BorderFactory.createEmptyBorder());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		add(scroll, BorderLayout.CENTER);

		JButton btnSubmit = new JButton("Search Primers");
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		add(btnSubmit, BorderLayout.SOUTH);

		initValues();
	}

	protected abstract JPanel initPanel();
	protected abstract void initValues();
	protected abstract void run();
}
