package composite;

import composite.visitor.Visitor;

public class LeafFile extends AbstractFile {
	private long size;
	
	protected LeafFile(String name, int depth, long size) {
		super(name, depth);
		this.size = size;
	}

	public long getSize() {
		return size;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}
