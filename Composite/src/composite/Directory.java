package composite;

import java.util.ArrayList;
import java.util.List;

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
	
	public Directory(String name, int depth) {
		super(name, depth);
	}

}
