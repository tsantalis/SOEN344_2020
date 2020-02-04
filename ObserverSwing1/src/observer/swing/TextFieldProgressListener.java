package observer.swing;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextFieldProgressListener implements ChangeListener {
	private JTextField textField;
	
	public TextFieldProgressListener(JTextField textField) {
		this.textField = textField;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		if(source.getClass() == JSlider.class) {
			JSlider slider = (JSlider)source;
			textField.setText(String.valueOf(slider.getValue()));
		}

	}

}
