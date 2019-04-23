

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class InitializedDeclaration extends Declaration {

	public InitializedDeclaration(Identifier iAST, Expression eAST, SourcePosition thePosition){
		super(thePosition);
		I = iAST;
		E = eAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitInitializedDeclaration(this, o); }

	public Identifier I;
	public Expression E;
}
