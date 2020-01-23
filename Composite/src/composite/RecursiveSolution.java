package composite;

import java.io.File;

import javax.swing.JFileChooser;

public class RecursiveSolution {

	public static void main(String args[]) {
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("."));
	    chooser.setDialogTitle("Select Directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	File selectedDirectory = chooser.getSelectedFile();
	    	System.out.println(ls(selectedDirectory, 0));
	    	System.out.println(size(selectedDirectory));
	    	System.out.println(countFiles(selectedDirectory));
	    }
	}
	
	private static long size(File parentFile) {
		long size = 0;
		if(parentFile.isDirectory()) {
			File[] files = parentFile.listFiles();
			for(File file : files) {
				size += size(file);
			}
			return size;
		}
		else {
			return parentFile.length();
		}
	}

	private static String ls(File parentFile, int depth) {
		StringBuilder sb = new StringBuilder();
		if(parentFile.isDirectory()) {
			File[] files = parentFile.listFiles();
			sb.append(printTabs(depth)).append(parentFile.getName()).append("\n");
			for(File file : files) {
				sb.append(ls(file, depth+1));
			}
			return sb.toString();
		}
		else {
			sb.append(printTabs(depth)).append(parentFile.getName()).append("\n");
			return sb.toString();
		}
	}
	
	private static int countFiles(File parentFile) {
		int count = 0;
		if(parentFile.isDirectory()) {
			File[] files = parentFile.listFiles();
			for(File file : files) {
				count += countFiles(file);
			}
			return count;
		}
		else {
			return 1;
		}
	}

	private static String printTabs(int depth) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<depth; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

}
