package observer.swing;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(3, 1));
		
		//slider is the concrete subject, which extends javax.swing.JComponent
	    //JSlider:addChangeListener(ChangeListener l) registers a ChangeListener (i.e., an Observer)
		//JSlider:fireStateChanged() notifies all registered observers
		//fireStateChanged() is automatically called whenever the user moves the slider
		
		final JSlider slider = new JSlider(0, 100, 0);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    slider.setPreferredSize(new Dimension(300, 50));
	    
	    final JProgressBar progressBar = new JProgressBar(0, 100);
	    
	    //ProgressListener is the concrete observer that implements the ChangeListener interface (i.e., Observer)
	    slider.addChangeListener(new ProgressListener(progressBar));
	    /*
		public void addChangeListener(ChangeListener l) {
			listenerList.add(ChangeListener.class, l);
		}
		*** listenerList is inherited from javax.swing.JComponent
			
		protected void fireStateChanged() {
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length - 2; i >= 0; i -= 2) {
				if (listeners[i]==ChangeListener.class) {
					if (changeEvent == null) {
						changeEvent = new ChangeEvent(this);
					}
					((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
				}
			}
		}
		*** fireStateChanged() is called when the user moves the slider
	     */
	    
	    JTextField textField = new JTextField();
	    slider.addChangeListener(new TextFieldProgressListener(textField));
	    
	    frame.add(slider);
	    frame.add(progressBar);
	    frame.add(textField);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

}
