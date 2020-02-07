package composite.visitor;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;

import composite.Directory;
import composite.LeafFile;

public class NodeStructureVisitor extends Visitor {
	private Stack<DefaultMutableTreeNode> stack = new Stack<DefaultMutableTreeNode>();

	public DefaultMutableTreeNode getRoot() {
		return stack.firstElement();
	}

	@Override
	public void visit(Directory dir) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(dir.getName());
		if(!stack.isEmpty()) {
			stack.peek().add(node);
		}
		stack.add(node);
	}

	@Override
	public void endVisit(Directory dir) {
		if(stack.size() > 1) {
			stack.pop();
		}
	}

	@Override
	public void visit(LeafFile leaf) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(leaf.getName());
		stack.peek().add(node);
	}
}
