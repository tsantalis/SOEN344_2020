package visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;

public class NestedIfStatementCollector extends ASTVisitor {

	private Set<IfStatement> nestedIfStatements = new LinkedHashSet<IfStatement>();
	
	public Set<IfStatement> getNestedIfStatements() {
		return nestedIfStatements;
	}

	public boolean visit(IfStatement node) {
		if(isNestedUnderIf(node)) {
			nestedIfStatements.add(node);
		}
		return super.visit(node);
	}

	private boolean isNestedUnderIf(ASTNode node) {
		ASTNode parent = node.getParent();
		while(parent != null) {
			if(parent instanceof IfStatement) {
				return true;
			}
			parent = parent.getParent();
		}
		return false;
	}
}
