package composite.visitor;

import composite.Directory;
import composite.LeafFile;

public abstract class Visitor {
	public void visit(Directory dir) {}
	public void endVisit(Directory dir) {}
	public void visit(LeafFile leaf) {}
}
