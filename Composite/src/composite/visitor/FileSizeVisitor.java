package composite.visitor;

import composite.LeafFile;

public class FileSizeVisitor extends Visitor {
	private long size = 0;
	public long getSize() {
		return size;
	}

	@Override
	public void visit(LeafFile leaf) {
		size += leaf.getSize();
	}
}
