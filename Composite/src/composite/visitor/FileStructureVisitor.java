package composite.visitor;

import composite.Directory;
import composite.LeafFile;

public class FileStructureVisitor extends Visitor {
	private StringBuilder sb = new StringBuilder();
	
	public String ls() {
		return sb.toString();
	}

	@Override
	public void visit(Directory dir) {
		sb.append(dir.printTabs()).append(dir.getName()).append("\n");
	}

	@Override
	public void visit(LeafFile leaf) {
		sb.append(leaf.printTabs()).append(leaf.getName()).append("\n");
	}
}
