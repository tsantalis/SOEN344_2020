package composite;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class AbstractFile {
	private String name;
	private int depth;
	
	protected AbstractFile(String name, int depth) {
		this.name = name;
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	protected String printTabs() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<depth; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	public abstract String ls();
	public abstract long size();
	public abstract int countFiles();
	public abstract DefaultMutableTreeNode createNode();
}
