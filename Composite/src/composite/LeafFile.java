package composite;

import javax.swing.tree.DefaultMutableTreeNode;

public class LeafFile extends AbstractFile {
	private long size;
	
	protected LeafFile(String name, int depth, long size) {
		super(name, depth);
		this.size = size;
	}

	@Override
	public String ls() {
		StringBuilder sb = new StringBuilder();
		sb.append(printTabs()).append(getName()).append("\n");
		return sb.toString();
	}

	@Override
	public long size() {
		return size;
	}

	@Override
	public int countFiles() {
		return 1;
	}

	@Override
	public DefaultMutableTreeNode createNode() {
		return new DefaultMutableTreeNode(getName());
	}
}
