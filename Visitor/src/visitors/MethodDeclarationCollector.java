package visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodDeclarationCollector extends ASTVisitor {
	private Set<MethodDeclaration> methodDeclarations = new LinkedHashSet<MethodDeclaration>();
	public boolean visit(MethodDeclaration node) {
		methodDeclarations.add(node);
		return true;
	}

	public Set<MethodDeclaration> getMethodDeclarations() {
		return methodDeclarations;
	}
}
