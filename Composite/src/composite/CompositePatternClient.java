package composite;

import java.io.File;

import javax.swing.JFileChooser;

public class CompositePatternClient {

	public static void main(String args[]) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setDialogTitle("Select Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedDirectory = chooser.getSelectedFile();

			AbstractFile topDir = new Directory(selectedDirectory);
			//System.out.println(topDir.ls());
			//System.out.println(topDir.size());
			//System.out.println(topDir.countFiles());
		}
	}
}
