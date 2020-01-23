package composite;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class Directory extends AbstractFile {
	private List<AbstractFile> contents = new ArrayList<AbstractFile>();
	
	public Directory(java.io.File directory) {
		this(directory, 0);
	}

	private Directory(java.io.File directory, int depth) {
		super(directory.getName(), depth);
		java.io.File[] files = directory.listFiles();
		for (java.io.File file : files) {
			if (file.isDirectory()) {
				contents.add(new Directory(file, depth + 1));
			} else {
				contents.add(new LeafFile(file.getName(), depth + 1, file.length()));
			}
		}
	}
	
	private Directory(String name, int depth) {
		super(name, depth);
	}

	@Override
	public String ls() {
		StringBuilder sb = new StringBuilder();
		sb.append(printTabs()).append(getName()).append("\n");
		for(AbstractFile file : contents) {
			sb.append(file.ls());
		}
		return sb.toString();
	}

	@Override
	public long size() {
		long size = 0;
		for(AbstractFile file : contents) {
			size += file.size();
		}
		return size;
	}

	@Override
	public int countFiles() {
		int count = 0;
		for(AbstractFile file : contents) {
			count += file.countFiles();
		}
		return count;
	}

	@Override
	public DefaultMutableTreeNode createNode() {
		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(getName());
		for(AbstractFile file : contents) {
			parentNode.add(file.createNode());
		}
		return parentNode;
	}
}
