package composite;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

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
			System.out.println(topDir.ls());
			System.out.println(topDir.size());
			System.out.println(topDir.countFiles());
			
			JFrame frame = new JFrame();
			JTree tree = new JTree(topDir.createNode());
			JScrollPane scrollPane = new JScrollPane(tree);
			frame.getContentPane().add(scrollPane);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
