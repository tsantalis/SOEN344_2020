package observer.swing;

import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProgressListener implements ChangeListener {

	private JProgressBar progressBar;
	public ProgressListener(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		//In Java we do not need to couple the ConcreteObserver with the ConcreteSubject,
		//because we can get a reference of the ConcreteSubject through ChangeEvent, by calling getSource()
		Object source = e.getSource();
		if(source.getClass() == JSlider.class) {
			JSlider slider = (JSlider)source;
			progressBar.setValue(slider.getValue());
		}
	}

}
