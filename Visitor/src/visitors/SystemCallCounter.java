package visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;

public class SystemCallCounter extends ASTVisitor {
	private int systemCalls = 0;
	
	public boolean visit(SimpleName node) { 
		IBinding binding = node.resolveBinding(); 
		if (binding instanceof IVariableBinding) { 
			IVariableBinding varBinding = (IVariableBinding) binding; 
			ITypeBinding declaringType = varBinding.getDeclaringClass(); 
			if (varBinding.isField() && declaringType != null &&
					"java.lang.System".equals(declaringType.getQualifiedName())) {
				systemCalls++; 
			}
		}
		else if (binding instanceof IMethodBinding) {
			IMethodBinding methodBinding = (IMethodBinding) binding;
			ITypeBinding declaringType = methodBinding.getDeclaringClass(); 
			if (declaringType != null && "java.lang.System".equals(declaringType.getQualifiedName())) {
				systemCalls++;
			}
		}
		return true; 
	}

	public int getfAccessesToSystemFields() {
		return systemCalls;
	}
	
}

