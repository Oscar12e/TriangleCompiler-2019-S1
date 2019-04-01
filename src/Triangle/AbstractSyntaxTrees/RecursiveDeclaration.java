package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveDeclaration extends Declaration {
	public RecursiveDeclaration (ProcFuncs pAST, SourcePosition thePosition) {
		super (thePosition);
		P = pAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitRecursiveDeclaration(this, o); }

	public ProcFuncs P;
}

