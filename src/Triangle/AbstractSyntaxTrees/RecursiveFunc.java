package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveFunc extends ProcFuncs {

	public RecursiveFunc(Identifier iAST, FormalParameterSequence fAST, TypeDenoter tAST, Expression eAST, SourcePosition thePosition) {
		super(thePosition);
		I = iAST;
		F = fAST;
		T = tAST;
		E = eAST;

	}

	public Object visit(Visitor v, Object o) { return v.visitRecursiveFunc(this, o); }

	Identifier I;
	FormalParameterSequence F;
	TypeDenoter T;
	Expression E;
}
