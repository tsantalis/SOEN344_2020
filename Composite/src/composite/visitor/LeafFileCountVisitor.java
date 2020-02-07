package composite.visitor;

import composite.LeafFile;

public class LeafFileCountVisitor extends Visitor {
	private int count = 0;
	public int getCount() {
		return count;
	}

	@Override
	public void visit(LeafFile leaf) {
		count++;
	}
}
