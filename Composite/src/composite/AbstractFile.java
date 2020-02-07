package composite;

import composite.visitor.Visitor;

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

	public int getDepth() {
		return depth;
	}

	public String printTabs() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<depth; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	public abstract void accept(Visitor v);
}
