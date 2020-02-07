package composite;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import composite.visitor.FileSizeVisitor;
import composite.visitor.FileStructureVisitor;
import composite.visitor.LeafFileCountVisitor;
import composite.visitor.NodeStructureVisitor;

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
			
			FileStructureVisitor structureVisitor = new FileStructureVisitor();
			topDir.accept(structureVisitor);
			System.out.println(structureVisitor.ls());
			
			FileSizeVisitor sizeVisitor = new FileSizeVisitor();
			topDir.accept(sizeVisitor);
			System.out.println(sizeVisitor.getSize());
			
			LeafFileCountVisitor countVisitor = new LeafFileCountVisitor();
			topDir.accept(countVisitor);
			System.out.println(countVisitor.getCount());
			
			JFrame frame = new JFrame();
			NodeStructureVisitor visitor = new NodeStructureVisitor();
			topDir.accept(visitor);
			JTree tree = new JTree(visitor.getRoot());
			JScrollPane scrollPane = new JScrollPane(tree);
			frame.getContentPane().add(scrollPane);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
