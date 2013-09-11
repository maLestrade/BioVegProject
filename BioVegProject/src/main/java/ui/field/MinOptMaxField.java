package ui.field;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MinOptMaxField {

	private final JSpinner min;
	private final JSpinner opt;
	private final JSpinner max;
	
	public MinOptMaxField(JSpinner min, JSpinner opt, JSpinner max) {
		this.min = min;
		this.opt = opt;
		this.max = max;
		
		initControl();
	}
	
	private void initControl() {
		this.min.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					if(opt != null) {
						if((Integer)min.getValue() > (Integer)opt.getValue())
							min.setValue(opt.getValue());
					}
					else if((Integer)min.getValue() > (Integer)max.getValue())
						min.setValue(max.getValue());
				} catch(ClassCastException e1) {
					if(opt != null) {
						if((Double)min.getValue() > (Double)opt.getValue())
							min.setValue(opt.getValue());
					}
					else if((Double)min.getValue() > (Double)max.getValue())
						min.setValue(max.getValue());
				}	
			}
		});
		
		this.max.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					if(opt != null) {
						if((Integer)max.getValue() < (Integer)opt.getValue())
							max.setValue(opt.getValue());
					}
					else if((Integer)max.getValue() < (Integer)min.getValue())
						max.setValue(min.getValue());
				} catch(ClassCastException e1) {
					if(opt != null) {
						if((Double)max.getValue() < (Double)opt.getValue())
							max.setValue(opt.getValue());
					}
					else if((Double)max.getValue() < (Double)min.getValue())
						max.setValue(min.getValue());
				}
			}
		});
		
		if(opt != null) {
			this.opt.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					try {
						if((Integer)opt.getValue() > (Integer)max.getValue())
							opt.setValue(max.getValue());
						if((Integer)opt.getValue() < (Integer)min.getValue())
							opt.setValue(min.getValue());
					} catch(ClassCastException e1) {
						if((Double)opt.getValue() > (Double)max.getValue())
							opt.setValue(max.getValue());
						if((Double)opt.getValue() < (Double)min.getValue())
							opt.setValue(min.getValue());
					}
				}
			});
		}
	}
	
	public MinOptMaxField(JSpinner min, JSpinner max) {
		this(min, null, max);
	}
}
